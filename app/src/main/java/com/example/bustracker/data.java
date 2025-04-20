package com.example.bustracker;

import java.util.ArrayList;

public class data {
    String BusNumber="",CollegeName="",Start="",NumberPlate="",StartTime="",EndTime="",stops="",ArrivingTime="",stLat="",stLon="",endLat="",endLon="",points="";
    String[] waypoints =  {"via:17.35529372435894, 78.55642408980903"};

    public data(String CollegeName,String Start,String number){
        this.BusNumber = number;
        this.Start = Start;
        this.CollegeName = CollegeName;

    }
    public data(String Start,String BusNumber,String NumberPlate,String StartTime,String EndTime,String stops,String ArrivingTime,String stLat,String stLon,String endLat,String endLon,String points){
        this.Start = Start;
        this.BusNumber  = BusNumber;
        this.NumberPlate = NumberPlate;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.stops = stops;
        this.ArrivingTime =  ArrivingTime;
        this.stLat = stLat;
        this.stLon = stLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.points = points;
    }
}