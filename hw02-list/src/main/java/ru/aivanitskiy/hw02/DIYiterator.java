package ru.aivanitskiy.hw02;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DIYiterator<T> implements ListIterator<T> {
    private int index = -1;
    private DIYarrayList<T> list;

    public DIYiterator(DIYarrayList<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return list.size() > index + 1;
    }

    @Override
    public T next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
        return list.get(index);
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        if(!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
        return list.get(index);
    }

    @Override
    public int nextIndex() {
        return index + 1;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        list.remove(index);
    }

    @Override
    public void set(T t) {
        list.set(index, t);
    }

    @Override
    public void add(T t) {
        list.add(index, t);
    }
}
