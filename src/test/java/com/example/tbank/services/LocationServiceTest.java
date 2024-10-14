package com.example.tbank.services;

import com.example.tbank.dto.CityDto;
import com.example.tbank.mapper.LocationMapper;
import com.example.tbank.models.City;
import com.example.tbank.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @Mock
    private LocationRepository repository;

    @Mock
    private LocationMapper locationMapper;

    @InjectMocks
    private LocationService locationService;

    @Test
    void findAllCities_shouldReturnListOfCities_whenCitiesExist() {
        CityDto firstDto = new CityDto("spb", "City1");
        CityDto secondDto = new CityDto("moskow", "City2");

        List<CityDto> expectedLocationDtos = List.of(firstDto, secondDto);

        City first = new City("spb", "City1");
        City second = new City("moskow", "City2");

        when(repository.readAll()).thenReturn(List.of(first, second));
        when(locationMapper.toGetCityDto(first)).thenReturn(firstDto);
        when(locationMapper.toGetCityDto(second)).thenReturn(secondDto);

        List<CityDto> locationDtos = locationService.findAllCities();

        assertEquals(expectedLocationDtos, locationDtos);
    }

    @Test
    void findAllCities_shouldReturnEmptyList_whenNoCitiesExist() {
        when(repository.readAll()).thenReturn(new ArrayList<>());

        List<CityDto> locationDtos = locationService.findAllCities();

        assertTrue(locationDtos.isEmpty());
    }

    @Test
    void getCityById_shouldReturnCityDto_whenCityExists() {
        String cityId = "spb";
        City city = new City(cityId, "City1");
        CityDto expectedDto = new CityDto(cityId, "City1");

        when(repository.get(cityId)).thenReturn(city);
        when(locationMapper.toGetCityDto(city)).thenReturn(expectedDto);

        CityDto actualDto = locationService.getCityById(cityId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void getCityById_shouldThrowException_whenCityDoesNotExist() {
        String cityId = "spb";

        when(repository.get(cityId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            locationService.getCityById(cityId);
        });

        assertEquals("Not found", exception.getMessage());
    }

    @Test
    void postCity_shouldSaveAndReturnCityDto_whenValidInput() {
        String cityId = "spb";
        CityDto inputDto = new CityDto(null, "NewCity");
        City cityToSave = new City(null, "NewCity");
        City savedCity = new City(cityId, "NewCity");
        CityDto expectedDto = new CityDto( cityId, "NewCity");

        assertDoesNotThrow(() -> locationService.postCity(inputDto));
    }

    @Test
    void deleteCity_shouldDeleteCity_whenCityExists() {
        String cityId = "spb";

        City city = new City(cityId, "CityToDelete");
        when(repository.get(cityId)).thenReturn(city);

        locationService.deleteCity(cityId);

        assertDoesNotThrow(() -> repository.delete(cityId));
    }

    @Test
    void deleteCity_shouldThrowException_whenCityDoesNotExist() {
        String cityId = "spb";

        when(repository.get(cityId)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            locationService.deleteCity(cityId);
        });

        assertEquals("Not found", exception.getMessage());
    }
}
