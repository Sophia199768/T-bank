package com.example.tbank.repository;

import com.example.tbank.models.Place;
import com.example.tbank.models.Event;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EventSpecification {
    public static Specification<Event> hasName(String name) {
        return (root, query, builder) -> {
            if (name == null || name.isEmpty()) {
                return builder.conjunction();
            }
            return builder.equal(root.get("name"), name);
        };
    }

    public static Specification<Event> hasPlace(Place place) {
        return (root, query, builder) -> {
            if (place == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("place"), place);
        };
    }

    public static Specification<Event> hasDateBetween(LocalDate fromDate, LocalDate toDate) {
        return (root, query, builder) -> {
            if (fromDate != null && toDate != null) {
                return builder.between(root.get("date"), fromDate, toDate);
            } else if (fromDate != null) {
                return builder.greaterThanOrEqualTo(root.get("date"), fromDate);
            } else if (toDate != null) {
                return builder.lessThanOrEqualTo(root.get("date"), toDate);
            }
            return builder.conjunction();
        };
    }
}