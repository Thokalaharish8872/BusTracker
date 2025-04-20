package com.example.bustracker;

import java.util.List;

public class DataModel{

    List<Route> routes;

    public static class Route{

        List<Legs> legs;
        OverviewPolyline overview_polyline;

        public static class Legs{
            Distance distance;
            Duration duration;
            String start_address;
            public static class Distance{
                String text;
            }
            public static class Duration{
                String text;
            }
        }

        public static class OverviewPolyline{
            String points;
        }
    }
}