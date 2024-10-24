package com.example.tbank;

import com.example.tbank.dto.CreateEventDto;
import com.example.tbank.dto.EventDto;
import com.example.tbank.models.Event;
import com.example.tbank.models.Place;
import com.example.tbank.repository.EventRepository;
import com.example.tbank.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.http.HttpStatus;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
public class EventIntegrationTest {

    @LocalServerPort
    private int port;

    @Container
    public static PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:15.1")
            .withDatabaseName("test")
            .withUsername("user")
            .withPassword("password");


    @DynamicPropertySource
    static void registerDatabaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDB::getUsername);
        registry.add("spring.datasource.password", postgresDB::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void testCreateAndGetEvent() {

        Place place = new Place();
        place.setName("test");
        place.setSlug("test");
        place = placeRepository.save(place);

        CreateEventDto createEventDto = new CreateEventDto("Test Event", 100.0, LocalDate.now(), place.getId());
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/events", createEventDto, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


        List<Event> events = eventRepository.findAll();
        assertThat(events).hasSize(1);
        Event savedEvent = events.get(0);
        assertThat(savedEvent.getName()).isEqualTo("Test Event");
        assertThat(savedEvent.getPlace().getId()).isEqualTo(place.getId());


        ResponseEntity<EventDto> getResponse = restTemplate.getForEntity("http://localhost:"+port+"/events/" + savedEvent.getId(), EventDto.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        EventDto eventDto = getResponse.getBody();
        assertThat(eventDto).isNotNull();
        assertThat(eventDto.getName()).isEqualTo("Test Event");
        assertThat(eventDto.getPlaceId()).isEqualTo(place.getId());
    }
}
