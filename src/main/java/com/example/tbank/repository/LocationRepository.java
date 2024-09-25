package com.example.tbank.repository;

import com.example.tbank.models.City;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public class LocationRepository {
    private final HashMap<String, City> hashMap = new HashMap<>();

    public void create(City city) {
        hashMap.put(city.getSlug(), city);
    }

    public City get(String slug) {
        return hashMap.get(slug);
    }

    public List<City> read() {
        return hashMap.values().stream().toList();
    }
    public void delete(String slug) {
        hashMap.remove(slug);
    }
}
