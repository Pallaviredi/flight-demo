package com.flight.tracker.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Flight {

    private String flightNumber;
    private BigDecimal price;

}
