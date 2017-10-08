package com.yuliia.trip.waypoints;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TripCalculator {

    public TripMetric calculateMetric(List<Waypoint> waypoints) {
        TripMetric metric = new TripMetric();
        for (int i = 0; i < waypoints.size() - 1; i++) {
            TripMetric interimMetric = calculateMetric(waypoints.get(i), waypoints.get(i + 1));
            metric = sumMetrics(metric, interimMetric);
        }

        return metric;
    }

    public TripMetric calculateMetric(Waypoint waypoint1, Waypoint waypoint2) {
        return calculateMetric(TimeUnit.MILLISECONDS.toSeconds(waypoint2.getTimestamp().getTime() - waypoint1.getTimestamp().getTime()),
                waypoint1.getSpeed(), waypoint2.getSpeed(), waypoint1.getSpeedLimit());
    }

    private TripMetric calculateMetric(double time, double startSpeed, double endSpeed, double speedLimit) {
        TripMetric metric = new TripMetric();
        metric.setTotalDuration(time);
        metric.setTotalDistance(calculateDistance(startSpeed, endSpeed, time));

        if (isSpeeding(startSpeed, speedLimit)) {
            if (isSpeeding(endSpeed, speedLimit)) {
                metric.setDurationSpeeding(metric.getTotalDuration());
                metric.setDistanceSpeeding(metric.getTotalDistance());
            } else {
                metric.setDurationSpeeding(getTimeOfSpeed(speedLimit, startSpeed, endSpeed, time));
                metric.setDistanceSpeeding(calculateDistance(startSpeed, speedLimit, metric.getDurationSpeeding()));
            }
        } else {
            if (isSpeeding(endSpeed, speedLimit)) {
                metric.setDurationSpeeding(time - getTimeOfSpeed(speedLimit, startSpeed, endSpeed, time));
                metric.setDistanceSpeeding(calculateDistance(speedLimit, endSpeed, metric.getDurationSpeeding()));
            }
        }

        return metric;
    }

    private TripMetric sumMetrics(TripMetric metric1, TripMetric metric2) {
        return new TripMetric(metric1.getTotalDuration() + metric2.getTotalDuration(),
                metric1.getTotalDistance() + metric2.getTotalDistance(),
                metric1.getDurationSpeeding() + metric2.getDurationSpeeding(),
                metric1.getDistanceSpeeding() + metric2.getDistanceSpeeding());
    }

    private double calculateDistance(double startSpeed, double endSpeed, double timeBetween) {
        return (startSpeed + endSpeed) / 2 * timeBetween;
    }

    private boolean isSpeeding(double speed, double speedLimit) {
        return speed > speedLimit;
    }

    private double getTimeOfSpeed(double expectedSpeed, double startSpeed, double endSpeed, double timeBetween) {
        if (endSpeed == expectedSpeed) {
            return timeBetween;
        }
        return timeBetween * (expectedSpeed - startSpeed) / (endSpeed - startSpeed);
    }
}
