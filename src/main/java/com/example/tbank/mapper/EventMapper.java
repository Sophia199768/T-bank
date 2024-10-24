package com.example.tbank.mapper;

import com.example.tbank.dto.CreateEventDto;
import com.example.tbank.dto.CreatePlaceDto;
import com.example.tbank.dto.EventDto;
import com.example.tbank.models.Event;
import com.example.tbank.models.Place;
import org.springframework.stereotype.Component;


@Component
public class EventMapper {

    public EventDto toGetEventDto(Event event) {
        return new EventDto(event.getName(), event.getPrice(), event.getDate(), event.getPlace().getId());
    }

    public Event toNewEvent(CreateEventDto createEventDto) {
        return new Event(createEventDto.getName(), createEventDto.getPrice(), createEventDto.getDate());
    }
}
