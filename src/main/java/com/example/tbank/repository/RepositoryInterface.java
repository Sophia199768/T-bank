package com.example.tbank.repository;

import java.util.List;

public interface RepositoryInterface<T, K> {
    void create(T t);

    T get(K id);

    List<T> readAll();

    void delete(K id);
}
