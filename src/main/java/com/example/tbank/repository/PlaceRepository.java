package com.example.tbank.repository;

import com.example.tbank.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT p FROM Place p LEFT JOIN FETCH p.events WHERE p.id = :id")
    Optional<Place> findByIdWithEvents(Integer id);
}
