package com.example.work_2_android_2.models;

public class WeatherItem {
private double mtempture;
private String mweatherState;
private String mcity;
private String mImage;

    public WeatherItem(double mtempture, String mweatherState, String mcity, String mImage) {
        this.mtempture = mtempture;
        this.mweatherState = mweatherState;
        this.mcity = mcity;
        this.mImage=mImage;
    }

    public String getmImage() {
        return mImage;
    }

    public double getMtempture() {
        return mtempture;
    }

    public String getMweatherState() {
        return mweatherState;
    }

    public String getMcity() {
        return mcity;
    }

}
