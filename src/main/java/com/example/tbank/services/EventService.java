package com.example.tbank.services;


import com.example.tbank.dto.*;
import com.example.tbank.exception.NotFoundException;
import com.example.tbank.mapper.EventMapper;
import com.example.tbank.models.Event;
import com.example.tbank.models.Place;
import com.example.tbank.repository.EventRepository;
import com.example.tbank.repository.EventSpecification;
import com.example.tbank.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final EventMapper eventMapper;


    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toGetEventDto).toList();
    }

    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event with id: " + id + " not found"));
        return eventMapper.toGetEventDto(event);
    }

    public void postEvent(CreateEventDto eventDto) {
        Event event = eventMapper.toNewEvent(eventDto);
        Place place = placeRepository.findById(eventDto.getPlaceId()).orElseThrow(() -> new NotFoundException("Place with id: " + eventDto.getPlaceId() + " not found"));
        event.setPlace(place);
        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event with id: " + id + " not found"));
        eventRepository.delete(event);
    }

    public List<EventDto> getEvent(FindEventDto findEventDto) {

        Specification<Event> spec = Specification.where(EventSpecification.hasName(findEventDto.getName()))
                .and(EventSpecification.hasPlace(placeRepository.findById(findEventDto.getPlace()).orElseThrow()))
                .and(EventSpecification.hasDateBetween(findEventDto.getFromDate(), findEventDto.getToDate()));
        return eventRepository.findAll(spec).stream().map(eventMapper::toGetEventDto).toList();
    }
}