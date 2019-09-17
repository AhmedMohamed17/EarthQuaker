package com.example.android.earthquaker;

public class earth { // class name camelcase !!

    private String magnitute;
    private String location;
    private String second;
    private String Date;
    private String time;
    private String url;

    public earth(String magnitute, String location, String second, String date, String time, String url) {
        this.magnitute = magnitute;
        this.location = location;
        this.second = second;
        Date = date;
        this.time = time;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public earth(String magnitute, String location, String second, String date, String time) {
        this.magnitute = magnitute;
        this.location = location;
        this.second = second;
        Date = date;
        this.time = time;
    }

    public double getMagnitute() {
        return Double.parseDouble(magnitute);
    }

    public void setMagnitute(String magnitute) {
        this.magnitute = magnitute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
