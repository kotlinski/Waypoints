package com.yuliia.trip.waypoints;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TripCalculatorTest {
    private Date now = new Date();

    @Test
    public void speedUnderLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 3, 6),
                createWaypoint(5, 5, 6)
        );

        assertMetric(metric, 5, 20, 0, 0);
    }

    @Test
    public void speedOverLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 5, 2),
                createWaypoint(5, 3, 6)
        );

        assertMetric(metric, 5, 20, 5, 20);
    }

    @Test
    public void increaseSpeedOverLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 3, 4),
                createWaypoint(10, 5, 6)
        );

        assertMetric(metric, 10, 40, 5, 22.5);
    }

    @Test
    public void decreaseSpeedUnderLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 5, 4),
                createWaypoint(10, 3, 6)
        );

        assertMetric(metric, 10, 40, 5, 22.5);
    }

    @Test
    public void speedOnLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 5, 5),
                createWaypoint(5, 5, 5)
        );

        assertMetric(metric, 5, 25, 0, 0);
    }

    @Test
    public void startSpeedOnLimitAndEndSpeedOverLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 3, 3),
                createWaypoint(10, 5, 5)
        );

        assertMetric(metric, 10, 40, 10, 40);
    }

    @Test
    public void startSpeedOnLimitAndEndSpeedUnderLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 3, 3),
                createWaypoint(10, 2, 5)
        );

        assertMetric(metric, 10, 25, 0, 0);
    }

    @Test
    public void endSpeedOnLimitAndStartSpeedOverLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 5, 3),
                createWaypoint(10, 3, 5)
        );

        assertMetric(metric, 10, 40, 10, 40);
    }

    @Test
    public void endSpeedOnLimitAndStartSpeedUnderLimit() {
        TripMetric metric = new TripCalculator().calculateMetric(
                createWaypoint(0, 2, 5),
                createWaypoint(10, 5, 5)
        );

        assertMetric(metric, 10, 35, 0, 0);
    }

    @Test
    public void sumWaypointsList() {
        List<Waypoint> waypoints = new ArrayList<Waypoint>();
        waypoints.add(createWaypoint(0, 5, 5));
        waypoints.add(createWaypoint(5, 5, 4));
        waypoints.add(createWaypoint(10, 5, 3));

        TripMetric metric = new TripCalculator().calculateMetric(waypoints);

        assertMetric(metric, 10, 50, 5, 25);
    }

    private Waypoint createWaypoint(long secondsAfterStarting, double speed, double speedLimit) {
        return new Waypoint(new Date(now.getTime() + TimeUnit.SECONDS.toMillis(secondsAfterStarting)), speed, speedLimit);
    }

    private void assertMetric(TripMetric tripMetric, double expectedDuration, double expectedDistance,
                              double expectedSpeedingDuration, double expectedSpeedingDistance) {
        assertEquals(expectedDuration, tripMetric.getTotalDuration(), 0.001);
        assertEquals(expectedDistance, tripMetric.getTotalDistance(), 0.001);
        assertEquals(expectedSpeedingDuration, tripMetric.getDurationSpeeding(), 0.001);
        assertEquals(expectedSpeedingDistance, tripMetric.getDistanceSpeeding(), 0.001);
    }
}