package com.example.tbank.restControllers;

import com.example.tbank.aop.Loggable;
import com.example.tbank.dto.CityDto;
import com.example.tbank.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
@Loggable
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getCity() {
        return ResponseEntity.ok(locationService.getCity());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CityDto> getCityById(@PathVariable String slug) {
        return ResponseEntity.ok(locationService.getCityById(slug));
    }

    @PostMapping
    public void postCity(@RequestBody CityDto cityDto) {
        locationService.postCity(cityDto);
    }

    @PutMapping
    public void putCity(@RequestBody CityDto cityDto) {
        locationService.postCity(cityDto);
    }

    @DeleteMapping("/{slug}")
    public void deleteCity(@PathVariable String slug) {
        locationService.deleteCity(slug);
    }
}
