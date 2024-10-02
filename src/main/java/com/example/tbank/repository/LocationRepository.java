package com.example.tbank.repository;

import com.example.tbank.models.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class LocationRepository implements RepositoryInterface<City, String> {
    private final ConcurrentHashMap<String, City> hashMap = new ConcurrentHashMap<>();

    public void create(City city) {
        hashMap.put(city.getSlug(), city);
    }

    public City get(String slug) {
        return hashMap.get(slug);
    }

    public List<City> readAll() {
        return Collections.unmodifiableList(new ArrayList<>(hashMap.values()));
    }
    public void delete(String slug) {
        hashMap.remove(slug);
    }
}
