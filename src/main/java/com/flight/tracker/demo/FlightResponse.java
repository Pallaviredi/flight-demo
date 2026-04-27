package com.flight.tracker.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FlightResponse {

    private List<Flight> flights;

}
