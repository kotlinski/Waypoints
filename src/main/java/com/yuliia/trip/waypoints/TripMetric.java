package com.yuliia.trip.waypoints;

public class TripMetric {
    private double distanceSpeeding;
    private double durationSpeeding;
    private double totalDistance;
    private double totalDuration;

    public TripMetric() {
    }

    public TripMetric(double totalDuration, double totalDistance, double durationSpeeding, double distanceSpeeding) {
        this.distanceSpeeding = distanceSpeeding;
        this.durationSpeeding = durationSpeeding;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    public double getDistanceSpeeding() {
        return distanceSpeeding;
    }

    public void setDistanceSpeeding(double distanceSpeeding) {
        this.distanceSpeeding = distanceSpeeding;
    }

    public double getDurationSpeeding() {
        return durationSpeeding;
    }

    public void setDurationSpeeding(double durationSpeeding) {
        this.durationSpeeding = durationSpeeding;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

    @Override
    public String toString() {
        return "TripMetric{\n" +
                "distanceSpeeding=" + distanceSpeeding + " meters,\n" +
                "durationSpeeding=" + durationSpeeding + " seconds,\n" +
                "totalDistance=" + totalDistance + " meters,\n" +
                "totalDuration=" + totalDuration + " seconds\n" +
                '}';
    }
}
