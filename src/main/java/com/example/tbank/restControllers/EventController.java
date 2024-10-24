package com.example.tbank.restControllers;

import com.example.tbank.dto.CreateEventDto;
import com.example.tbank.dto.EventDto;
import com.example.tbank.dto.FindEventDto;
import com.example.tbank.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvent() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public void postEvent(@Valid @RequestBody CreateEventDto eventDto) {
        eventService.postEvent(eventDto);
    }

    @PutMapping
    public void putEvent(@Valid @RequestBody CreateEventDto eventDto) {
        eventService.postEvent(eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventDto>> getEventByName(@RequestBody FindEventDto findEventDto) {
        return ResponseEntity.ok(eventService.getEvent(findEventDto));
    }
}
