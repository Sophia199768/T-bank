package com.example.tbank.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;


    public Event(String name, Double price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }
}
