package com.example.WeatherApp.model;

import java.util.ArrayList;
import java.util.List;
public class Cities {
    private List<City> cityList;

    public List<City> getCityList() {
        if (cityList == null) {
            cityList = new ArrayList<>();
        }
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
