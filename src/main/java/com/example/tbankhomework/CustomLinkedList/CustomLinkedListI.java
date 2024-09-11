package com.example.tbankhomework.CustomLinkedList;

import com.example.tbankhomework.Exception.CustomLinkedListException;

import java.util.List;

public interface CustomLinkedListI<T> {
    int size();
    void add(T size);
    void remove(Integer index) throws CustomLinkedListException;
    T get(Integer index) throws CustomLinkedListException;
    Boolean contains(T element);
    void addAll(List<T> list);
}
