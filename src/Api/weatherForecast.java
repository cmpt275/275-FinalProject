package Api;
import entity.TodayWeatherForecast;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Map;

public class weatherForecast {
    public static HttpResponse<String> getCurrentWeatherByLatLong(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/current?lon="+longitude+"&lat="+latitude))
                .header("x-rapidapi-key", "e12472846amsh9bef1e0fecc4f61p1a9eb6jsn0e2b1b6e245f")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response;
    }

    public static HttpResponse<String> getNextFiveDaysWeather(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly?lat="+latitude+"&lon="+longitude))
                .header("x-rapidapi-key", "e12472846amsh9bef1e0fecc4f61p1a9eb6jsn0e2b1b6e245f")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response;
    }

    public static void getTodayWeather(String latitude, String longitude) throws IOException, InterruptedException {
        JSONObject todayObject = new JSONObject((Map) getCurrentWeatherByLatLong(latitude,longitude));
        TodayWeatherForecast todayWeatherForecast = new TodayWeatherForecast();

    }

    public static void getNextFiveDayWeather(String latitude, String longitude) throws IOException, InterruptedException {
        JSONObject fiveDaysObject = new JSONObject((Map) getNextFiveDaysWeather(latitude, longitude));
    }


}


