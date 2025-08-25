package com.example.WeatherApp.model;

import java.io.Serializable;

public class City implements Serializable {
    private String Name;
    private Double CurrentTemparature;
    private Double CurrentHumidity;
    private String Description;

    public City() {}

    public City (String Name, Double CurrentTemparature, Double CurrnetHumidity,
                 String Description) {
                    this.Name = Name;
                    this.CurrentTemparature = CurrentTemparature;
                    this. CurrentHumidity = CurrnetHumidity;
                    this.Description = Description;
                 }
        public String getName() {
            return Name;
        }
        public Double getCurrTemp() {
            return CurrentTemparature;
        }
        public void setCurrTemp(Double temp) {
            CurrentTemparature = temp;
        }
        public Double getCurrHum() {
            return CurrentHumidity;
        }
        public void setCurrHum(Double hum) {
            CurrentHumidity = hum;
        }
        public String getDescription() {
            return Description;
        }
        public void setDescription(String description) {
            Description = description;
        }
}
