package com.flight.tracker.demo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public ResponseEntity<FlightResponse> search(@RequestParam String origin,
                                                 @RequestParam String destination,
                                                 @RequestParam(required = false) TimeWindow timeWindow) {
        return ResponseEntity.ok(flightService.getFlights(origin, destination, Optional.ofNullable(timeWindow)));
    }

}
