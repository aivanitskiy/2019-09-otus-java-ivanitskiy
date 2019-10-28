package ru.aivanitskiy.hw02;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main <T> {
    public static void main(String[] args) {
        DIYarrayList<Integer> myList = new DIYarrayList<>();

        Random rand =  new Random();
        for (int i = 0; i < 25; i++) {
            myList.add(rand.nextInt(50));
        }

        printList(myList);

        Collections.sort(myList);
        printList(myList);

        Collections.addAll(myList, 3, 5, 2, 9, 11, 35, 63);
        printList(myList);

        Collections.sort(myList);
        printList(myList);

        List<Integer> src = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        List<Integer> dest = new DIYarrayList<>();
        for(int i = 0; i < src.size(); i++) {
            dest.add(i);
        }
        Collections.copy(dest, src);

        printList(dest);
        printList(src);

        List<Integer> list = new DIYarrayList<>();
        list.add(1);
        list.add(null);
        System.out.println(list.contains(null));
    }

    static void printList(List<?> list) {
        StringBuilder stringBuilder = new StringBuilder("list: \t");
        for(Object elem: list) {
            stringBuilder.append(elem);
            stringBuilder.append("\t");
        }
        System.out.println(stringBuilder.toString());
    }
}
