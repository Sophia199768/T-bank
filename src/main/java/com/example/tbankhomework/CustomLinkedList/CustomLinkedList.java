package com.example.tbankhomework.CustomLinkedList;


import com.example.tbankhomework.Exception.CustomLinkedListException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomLinkedList<T> implements CustomLinkedListI<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
        }
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size += 1;
    }

    public Node<T> getNode(Integer index) throws CustomLinkedListException {
        Node<T> answer = head;

        if (index >= size || index < 0) {
            throw new CustomLinkedListException("index out of bounds");
        }

        for (int i = 0; i < index; i++) {
            answer = answer.next;
        }

        return answer;
    }

    public T get(Integer index) throws CustomLinkedListException {
      Node<T> answer = getNode(index);
       return answer.data;
    }


    public void remove(Integer index) throws CustomLinkedListException {
        Node<T> nodeForDelete = head;

        if (index == 0) {
            head = nodeForDelete.next;
        }

        if (index == size - 1) {
            tail = nodeForDelete.previous;
        }

        nodeForDelete = getNode(index);

        nodeForDelete.previous.next = nodeForDelete.next;
        nodeForDelete.next.previous = nodeForDelete.previous;
        size--;
    }

    public Boolean contains(T element) {
        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void addAll(List<T> list) {
        for (T element : list) {
            add(element);
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

}
