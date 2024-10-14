package com.example.tbank.repository;


import com.example.tbank.models.Category;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CategoryRepository implements RepositoryInterface<Category, Integer> {
    private final ConcurrentHashMap<Integer, Category> hashMap = new ConcurrentHashMap<>();

    public void create(Category category) {
        hashMap.put(category.getId(), category);
    }

    public Category get(Integer id) {
        return hashMap.get(id);
    }

    public List<Category> readAll() {
        return Collections.unmodifiableList(new ArrayList<>(hashMap.values()));
    }
    public void delete(Integer id) {
        hashMap.remove(id);
    }
}
