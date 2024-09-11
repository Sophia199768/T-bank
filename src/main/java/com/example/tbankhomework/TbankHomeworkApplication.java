package com.example.tbankhomework;

import com.example.tbankhomework.CustomLinkedList.CustomLinkedList;
import com.example.tbankhomework.Exception.CustomLinkedListException;

import java.util.List;
import java.util.stream.Stream;


public class TbankHomeworkApplication {

    public static void main(String[] args) throws CustomLinkedListException {
        // Part 1
        System.out.println("First task");

        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.get(0));

        System.out.println(list.contains(2));

        list.remove(1);


        List<Integer> additional = List.of(4, 5, 6);
        list.addAll(additional);

        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(list.get(i) + " ");
        }


        // Part 2
        System.out.println();
        System.out.println("Third task");

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        CustomLinkedList<Integer> customList = stream.reduce(
                new CustomLinkedList<>(),
                (newList, element) -> {newList.add(element); return newList; },
                (list1, list2) -> {
                    list1.addAll(list2.toList());
                    return list1;
                }
        );


        for (int i = 0; i < customList.getSize(); i++) {
            System.out.print(customList.get(i) + " ");
        }
    }

}
