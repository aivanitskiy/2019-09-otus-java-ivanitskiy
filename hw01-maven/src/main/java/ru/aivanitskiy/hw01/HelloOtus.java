package ru.aivanitskiy.hw01;

import com.google.common.collect.HashMultiset;

public class HelloOtus {
    public static void main(String[] args) {
        HashMultiset<String> storage = HashMultiset.create();
        storage.add("one");
        storage.add("one");
        storage.add("one");

        System.out.println(storage.count("one"));
        System.out.println(storage.count("two"));
    }
}
