package com.example.tbank.mapper;

import com.example.tbank.dto.CreatePlaceDto;
import com.example.tbank.dto.PlaceDto;
import com.example.tbank.models.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final EventMapper eventMapper;

    public PlaceDto toGetPlaceDto(Place place) {
        return new PlaceDto(place.getSlug(), place.getName(), place.getEvents().stream().map(eventMapper::toGetEventDto).toList());
    }

    public Place toNewPlace(CreatePlaceDto placeDto) {
        return Place.builder().name(placeDto.getName()).slug(placeDto.getSlug()).build();
    }
}
