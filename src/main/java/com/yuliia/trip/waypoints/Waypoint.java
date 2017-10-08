package com.yuliia.trip.waypoints;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Waypoint {
    private Date timestamp;
    private double speed;
    @SerializedName("speed_limit")
    private double speedLimit;

    public Waypoint() {
    }

    public Waypoint(Date timestamp, double speed, double speedLimit) {
        this.timestamp = timestamp;
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }
}
