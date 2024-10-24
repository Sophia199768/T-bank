package com.example.tbank.services;

import com.example.tbank.dto.CreatePlaceDto;
import com.example.tbank.dto.PlaceDto;
import com.example.tbank.exception.NotFoundException;
import com.example.tbank.mapper.PlaceMapper;
import com.example.tbank.models.Place;
import com.example.tbank.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper placeMapper;


    public List<PlaceDto> findAllPlaces() {
        return repository.findAll().stream().map(placeMapper::toGetPlaceDto).toList();
    }

    public PlaceDto getPlaceById(Long id) {
        Place place = repository.findById(id).orElseThrow(() -> new NotFoundException("Place with id: " + id + " not found"));
        return placeMapper.toGetPlaceDto(place);
    }

    public void postPlace(CreatePlaceDto placeDto) {
        repository.save(placeMapper.toNewPlace(placeDto));
    }

    public void deletePlace(Long id) {
        Place place = repository.findById(id).orElseThrow(() -> new NotFoundException("Place with id: " + id + " not found"));
        repository.delete(place);
    }
}
