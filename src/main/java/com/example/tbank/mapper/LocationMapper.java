package com.example.tbank.mapper;

import com.example.tbank.dto.CityDto;
import com.example.tbank.models.City;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public CityDto toGetCityDto(City city) {
        return new CityDto(city.getSlug(), city.getName());
    }


    public City toNewCity(CityDto cityDto) {
        return new City(cityDto.getSlug(), cityDto.getName());
    }
}
