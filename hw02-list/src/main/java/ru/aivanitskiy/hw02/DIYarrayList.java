package ru.aivanitskiy.hw02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DIYarrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 2;
    private int capacity;
    private int size = 0;
    private Object[] storage;

    public DIYarrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DIYarrayList(int capacity) {
        this.capacity = capacity;
        storage = new Object[capacity];
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYiterator<T>(this);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        System.arraycopy(storage, 0, array, 0, size());
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[]  a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if(size >= capacity) {
            increaseCapacity();
        }
        storage[size] = t;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        int i = 0;
        while (!result && i < size()) {
            if(o.equals(get(i))) {
                remove(i);
                result = true;
            }
            i++;
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;

        for(Object item: c) {
            if(!contains(item)) {
                result = false;
            }
        }

        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean result = false;
        for(T item: c) {
            add(item);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndexNotOutOfBounds(index);
        return (T)storage[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndexNotOutOfBounds(index);
        storage[index] = element;
        return (T)storage[index];
    }

    @Override
    public void add(int index, T element) {
        checkIndexNotOutOfBounds(index);
        if(size >= capacity) {
            increaseCapacity();
        }

        for (int i = (size() - 1); i >= index; i--) {
            storage[i + 1] = storage[i];
        }

        storage[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndexNotOutOfBounds(index);

        for (int i = index; i < (size() - 1); i++) {
            storage[i] = storage[i + 1];
        }
        storage[size() - 1] = null;
        size--;
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int result = -1;

        int i = 0;
        while (i < size() && result < 0) {
            if(o.equals(storage[i])) {
                result = i;
            } else {
                i++;
            }
        }

        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;

        int i = size() - 1;
        while (i >= 0 && result < 0) {
            if(o.equals(storage[i])) {
                result = i;
            } else {
                i--;
            }
        }

        return result;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYiterator<T>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        Object[] newStorage = new Object[newCapacity];

        System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
        capacity = newCapacity;
    }

    private void checkIndexNotOutOfBounds(int index)
    {
        if(index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, size()));
        }
    }
}