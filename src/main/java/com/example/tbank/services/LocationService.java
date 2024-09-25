package com.example.tbank.services;

import com.example.tbank.dto.CityDto;
import com.example.tbank.mapper.LocationMapper;
import com.example.tbank.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper locationMapper;


    public List<CityDto> getCity() {
        return repository.read().stream().map(locationMapper::toGetCityDto).toList();
    }

    public CityDto getCityById(String slug) {
        return locationMapper.toGetCityDto(repository.get(slug));
    }

    public void postCity(CityDto cityDto) {
        repository.create(locationMapper.toNewCity(cityDto));
    }

    public void deleteCity(String slug) {
        repository.delete(slug);
    }
}
