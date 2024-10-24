package com.example.tbank.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<Event> events = new ArrayList<>();
}
