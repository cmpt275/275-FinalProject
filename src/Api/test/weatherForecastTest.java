package Api.test;

import Api.getIPAddress;
import Api.weatherForecast;
import entity.FiveDaysForecast;
import entity.Location;
import entity.TodayWeatherForecast;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class weatherForecastTest {
    private static String response1, repsonse2;
    private static String icon, ipAddress;

    @Test
    void TestGetCurrentWeatherByLatLong() throws IOException, InterruptedException {
        response1 = weatherForecast.getCurrentWeatherByLatLong("49.214599770271455","-122.98873646818907");
        assertNotNull(response1);
    }

    @Test
    void TestGetNextFiveDaysWeatherByLatLong() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        repsonse2 = weatherForecast.getNextFiveDaysWeatherByLatLong("49.214599770271455","-122.98873646818907");
        assertNotNull(repsonse2);
    }

    @Test
    void TestGetTodayWeather() throws IOException, InterruptedException, ParseException {
        TodayWeatherForecast todayWeatherForecast;
        todayWeatherForecast = weatherForecast.getTodayWeather("-49.214599770271455","122.98873646818907");
        assertNotNull(todayWeatherForecast);
    }

    @Test
    void TestGetNextFiveDayWeather() throws Exception {
        List<FiveDaysForecast> fiveDaysForecast;
        fiveDaysForecast = weatherForecast.getNextFiveDayWeather("49.214599770271455","-122.98873646818907");
        assertNotNull(fiveDaysForecast);
    }

    @Test
    void TestGetWeatherIcon() {
        icon = "c01d";
        Image iconImage = weatherForecast.getWeatherIcon(icon);
        assertNotNull(iconImage);
    }

    @Test
    void TestNullIPGetLatLong() throws IOException, ParseException, InterruptedException {
        ipAddress = "";
        Location location = weatherForecast.getLatLong(ipAddress);
        assertNotNull(location);
    }
    @Test
    void TestGetLatLong() throws IOException, ParseException, InterruptedException {
        ipAddress = getIPAddress.getIP();
        Location location = weatherForecast.getLatLong(ipAddress);
        assertNotNull(location);
    }
}