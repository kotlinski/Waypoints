package com.yuliia.trip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuliia.trip.waypoints.TripCalculator;
import com.yuliia.trip.waypoints.TripMetric;
import com.yuliia.trip.waypoints.Waypoint;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Waypoint> waypoints = readWaypoints("waypoints.json");

        TripCalculator tripCalculator = new TripCalculator();
        TripMetric metric = tripCalculator.calculateMetric(waypoints);
        System.out.println(metric.toString());
    }

    private static List<Waypoint> readWaypoints(String filename) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(filename), new TypeToken<List<Waypoint>>() {
        }.getType());
    }

}
