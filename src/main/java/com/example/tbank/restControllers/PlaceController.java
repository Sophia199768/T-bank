package com.example.tbank.restControllers;

import com.example.tbank.aop.Loggable;
import com.example.tbank.dto.CreatePlaceDto;
import com.example.tbank.dto.PlaceDto;
import com.example.tbank.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@Loggable
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceDto>> getPlace() {
        return ResponseEntity.ok(placeService.findAllPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.getPlaceById(id));
    }

    @PostMapping
    public void postPlace(@RequestBody CreatePlaceDto placeDto) {
        placeService.postPlace(placeDto);
    }

    @PutMapping
    public void putPlace(@RequestBody CreatePlaceDto placeDto) {
        placeService.postPlace(placeDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
    }
}
