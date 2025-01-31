package entity;

import java.awt.*;

public class FiveDaysForecast {
    private String relative_humidity;
    private Weather weather;
    private String app_temp;
    private String temp;
    private String wind_cdir_full;
    private Image iconImage;

    public String getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(String relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getApp_temp() {
        return app_temp;
    }

    public void setApp_temp(String app_temp) {
        this.app_temp = app_temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWind_cdir_full() {
        return wind_cdir_full;
    }

    public void setWind_cdir_full(String wind_cdir_full) {
        this.wind_cdir_full = wind_cdir_full;
    }

    public Image getIconImage() {
        return iconImage;
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }
}
