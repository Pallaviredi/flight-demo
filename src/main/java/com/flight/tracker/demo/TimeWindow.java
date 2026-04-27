package com.flight.tracker.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public enum TimeWindow {

    MORNING(LocalTime.of(5,0), LocalTime.of(11, 59)),
    AFTERNOON(LocalTime.of(12,0), LocalTime.of(16, 59)),
    EVENING(LocalTime.of(17,0), LocalTime.of(19, 59)),
    NIGHT(LocalTime.of(20,0), LocalTime.of(23, 59));

    private final LocalTime start;
    private final LocalTime end;

    public boolean contains(LocalTime time) {
        return !time.isBefore(start) && !time.isAfter(end);
    }

}
