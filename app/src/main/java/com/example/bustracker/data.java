package com.example.bustracker;

public class data {
    String BusNumber="",CollegeName="",Start="",NumberPlate="",StartTime="",EndTime="",stops="",ArrivingTime="";
    public data(String CollegeName,String Start,String number){
        this.BusNumber = number;
        this.Start = Start;
        this.CollegeName = CollegeName;
    }
    public data(String Start,String BusNumber,String NumberPlate,String StartTime,String EndTime,String stops,String ArrivingTime){
        this.Start = Start;
        this.BusNumber  = BusNumber;
        this.NumberPlate = NumberPlate;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.stops = stops;
        this.ArrivingTime =  ArrivingTime;
    }
}