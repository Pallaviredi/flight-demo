package com.flight.tracker.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findByOrigin(String origin);
    List<FlightEntity> findByOriginAndDestinationOrderByPrice(String origin, String destination);

}
