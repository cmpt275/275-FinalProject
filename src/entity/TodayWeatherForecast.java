package entity;

import java.awt.*;

public class TodayWeatherForecast {
    public String relative_humidity;
    public double pressure;
    public long visibility;
    public String solar_rad;
    public String city_name;
    public double wind_speed;
    public String wind_cdir;
    public String sunset;
    public String sunrise;
    public Weather weather;
    public String datetime;
    public String temp;
    public String app_temp;
    public Image iconImage;

    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    public String getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(String relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public String getSolar_rad() {
        return solar_rad;
    }

    public void setSolar_rad(String solar_rad) {
        this.solar_rad = solar_rad;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_cdir() {
        return wind_cdir;
    }

    public void setWind_cdir(String wind_cdir) {
        this.wind_cdir = wind_cdir;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getApp_temp() {
        return app_temp;
    }

    public void setApp_temp(String app_temp) {
        this.app_temp = app_temp;
    }
}
