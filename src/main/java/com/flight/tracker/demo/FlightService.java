package com.flight.tracker.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightResponse getFlights(String origin, String destination, Optional<TimeWindow> optionalTimeWindow) {
        return new FlightResponse(optionalTimeWindow.map(timeWindow -> cheapestWithWindow(origin, destination, timeWindow)).orElseGet(() -> directOrConnecting(origin, destination)));
    }

    private List<Flight> directOrConnecting(String origin, String destination) {
        // direct
        List<FlightEntity> direct = flightRepository.findByOriginAndDestinationOrderByPrice(origin, destination);
        if (!direct.isEmpty()) {
            return List.of(new Flight(direct.get(0).getFlightNumber(), direct.get(0).getPrice()));
        }

        // connecting
        for (FlightEntity firstFlight : flightRepository.findByOrigin(origin)) {
            List<FlightEntity> secondFlight = flightRepository.findByOriginAndDestinationOrderByPrice(firstFlight.getDestination(), destination);
            if (secondFlight.isEmpty()) {
                return new ArrayList<>();
            } else {
                return List.of(
                        new Flight(firstFlight.getFlightNumber(), firstFlight.getPrice()),
                        new Flight(secondFlight.get(0).getFlightNumber(), secondFlight.get(0).getPrice())
                );
            }
        }
        return new ArrayList<>();
    }

    private List<Flight> cheapestWithWindow(String origin, String destination, TimeWindow timeWindow) {
        // direct
        for (FlightEntity flightEntity : flightRepository.findByOriginAndDestinationOrderByPrice(origin, destination)) {
            if (timeWindow.contains(toLocalTime(flightEntity.getDepartureTime()))) {
                return List.of(new Flight(flightEntity.getFlightNumber(), flightEntity.getPrice()));
            }
        }

        // connecting
        for (FlightEntity firstFlight : flightRepository.findByOrigin(origin)) {
            for (FlightEntity secondFlight : flightRepository.findByOriginAndDestinationOrderByPrice(firstFlight.getOrigin(), firstFlight.getDestination())) {
                if (timeWindow.contains(toLocalTime(firstFlight.getDepartureTime()))) {
                    return List.of(
                            new Flight(firstFlight.getFlightNumber(), firstFlight.getPrice()),
                            new Flight(secondFlight.getFlightNumber(), secondFlight.getPrice())
                    );
                }
            }
        }

        return new ArrayList<>();
    }


    private LocalTime toLocalTime(String time) {
        String[] parts = time.split(" ");
        String[] timeParts = parts[0].split("\\.");
        if (parts[1].equalsIgnoreCase("AM")) {
            return LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));
        } else {
            return LocalTime.of(Integer.parseInt(timeParts[0]) + 12, Integer.parseInt(timeParts[1]));
        }
    }

}
