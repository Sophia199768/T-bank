package com.example.tbank.repository;


import com.example.tbank.models.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CategoryRepository {
    private final HashMap<Integer, Category> hashMap = new HashMap<>();

    public void create(Category category) {
        hashMap.put(category.getId(), category);
    }

    public Category get(Integer id) {
        return hashMap.get(id);
    }

    public List<Category> read() {
        return hashMap.values().stream().toList();
    }
    public void delete(Integer id) {
        hashMap.remove(id);
    }
}
