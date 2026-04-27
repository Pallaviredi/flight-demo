package com.flight.tracker.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class FlightEntity {

    @Id
    private Long id;
    private String origin;
    private String destination;
    private String flightNumber;
    private BigDecimal price;
    private String departureTime;

}
