package com.example.learnersacolyte;

public class FetchFromFIrebase {

    String Title, Hour, Minute, AMorPM, Day, Month, Year, Event;

    FetchFromFIrebase(){}

    FetchFromFIrebase(String Hour, String Minute, String AMorPM, String Day, String Month, String Year, String Event)
    {
        this.Hour = Hour;
        this.Minute = Minute;
        this.AMorPM = AMorPM;
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Event = Event;
    }

    public FetchFromFIrebase(String title) {
        Title = title;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getMinute() {
        return Minute;
    }

    public void setMinute(String minute) {
        Minute = minute;
    }

    public String getAMorPM() {
        return AMorPM;
    }

    public void setAMorPM(String AMorPM) {
        this.AMorPM = AMorPM;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
