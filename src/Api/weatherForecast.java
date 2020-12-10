package Api;
import entity.FiveDaysForecast;
import entity.Location;
import entity.TodayWeatherForecast;
import entity.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class weatherForecast {

    /*Get today's weather from API response*/
    public static String getCurrentWeatherByLatLong(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/current?lon="+longitude+"&lat="+latitude))
                .header("x-rapidapi-key", "e12472846amsh9bef1e0fecc4f61p1a9eb6jsn0e2b1b6e245f")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseEntity = response.body();
        System.out.println(responseEntity);
        return responseEntity;
    }

    /*Get next five day's weather from API response*/
    public static String getNextFiveDaysWeatherByLatLong(String latitude, String longitude) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly?lat="+latitude+"&lon="+longitude))
                .header("x-rapidapi-key", "e12472846amsh9bef1e0fecc4f61p1a9eb6jsn0e2b1b6e245f")
                .header("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String responseEntity = response.body();
        System.out.println(responseEntity);
        return responseEntity;
    }

    /*Create model to store today's weather information*/
    public static TodayWeatherForecast getTodayWeather(String latitude, String longitude) throws IOException, InterruptedException, ParseException {
        String response = getCurrentWeatherByLatLong(latitude, longitude);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONArray obj_jsonArray = (JSONArray) jsonObject.get("data");
        JSONObject weather_info = (JSONObject) obj_jsonArray.get(0);

        TodayWeatherForecast todayWeatherForecast = new TodayWeatherForecast();
        todayWeatherForecast.setRelative_humidity( weather_info.get("rh").toString());
        todayWeatherForecast.setPressure((Double) weather_info.get("pres"));
        todayWeatherForecast.setVisibility((Long) weather_info.get("vis"));
        todayWeatherForecast.setSolar_rad( weather_info.get("solar_rad").toString());
        todayWeatherForecast.setCity_name((String) weather_info.get("city_name"));
        todayWeatherForecast.setWind_speed((Double) weather_info.get("wind_spd"));
        todayWeatherForecast.setWind_cdir((String) weather_info.get("wind_cdir"));
        todayWeatherForecast.setSunrise((String) weather_info.get("sunrise"));
        todayWeatherForecast.setSunset((String) weather_info.get("sunset"));
        todayWeatherForecast.setDatetime((String) weather_info.get("datetime"));
        todayWeatherForecast.setTemp(weather_info.get("temp").toString());
        todayWeatherForecast.setApp_temp(weather_info.get("app_temp").toString());

        JSONObject weather_details = (JSONObject) weather_info.get("weather");
        Weather weather = new Weather();
        weather.setDescription((String) weather_details.get("description"));
        weather.setIcon((String) weather_details.get("icon"));
        weather.setCode((Long) weather_details.get("code"));
        todayWeatherForecast.setWeather(weather);
        todayWeatherForecast.setIconImage(getWeatherIcon(todayWeatherForecast.getWeather().getIcon()));
        return todayWeatherForecast;
    }

    /*Create model list to store five day's weather information*/
    public static List<FiveDaysForecast> getNextFiveDayWeather(String latitude, String longitude) throws Exception {
        String response = getNextFiveDaysWeatherByLatLong(latitude, longitude);
        JSONParser parser = new JSONParser();
        JSONObject jsonObjects = (JSONObject) parser.parse(response);
        JSONArray weatherInfoArray = (JSONArray) jsonObjects.get("data");

        List<FiveDaysForecast> fiveDaysForecastsList = new ArrayList<>();

        for(int i = 0; i < weatherInfoArray.size();){
            JSONObject weather_info = (JSONObject) weatherInfoArray.get(i);

            FiveDaysForecast fiveDaysForecast = new FiveDaysForecast();
            fiveDaysForecast.setRelative_humidity(weather_info.get("rh").toString());
            fiveDaysForecast.setApp_temp(weather_info.get("app_temp").toString());
            fiveDaysForecast.setTemp(weather_info.get("temp").toString());
            fiveDaysForecast.setWind_cdir_full((String) weather_info.get("wind_cdir_full"));

            JSONObject weather_details = (JSONObject) weather_info.get("weather");
            Weather weather = new Weather();
            weather.setDescription((String) weather_details.get("description"));
            weather.setIcon((String) weather_details.get("icon"));
            weather.setCode((Long) weather_details.get("code"));
            fiveDaysForecast.setWeather(weather);
            fiveDaysForecast.setIconImage(getWeatherIcon(fiveDaysForecast.getWeather().getIcon()));
            fiveDaysForecastsList.add(fiveDaysForecast);
            i = i + 8;
        }

        return fiveDaysForecastsList;
    }

    /*Read the icon string and return the icon image*/
    public static Image getWeatherIcon(String icon) {
        String iconSubString = icon.substring(1);
        System.out.println(iconSubString);

        Image image = null;
        try {
            URL url = new URL("http://openweathermap.org/img/wn/"+iconSubString+"@2x.png");
            image = ImageIO.read(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    /*Get the Geographic coordinates from the ip Address */
    public static Location getLatLong(String ipAddress) throws IOException, InterruptedException, ParseException {

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;
        if(!ipAddress.isEmpty()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.ipstack.com/" + ipAddress + "?access_key=2e8cec8da06de2be8d8306af6154d636"))
                    .build();
            response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        else {
            //default ip address if the ip fetch failed
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.ipstack.com/206.116.113.82?access_key=2e8cec8da06de2be8d8306af6154d636"))
                    .build();
            response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        String responseEntity = response.body();
        System.out.println(responseEntity);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(responseEntity);
        //default location if no location return
        Location location;
        if(jsonObject.isEmpty()){
            location = new Location("49.214599770271455","-122.98873646818907");
        }
        else {
            location = new Location("", "");
            location.setLatitude(jsonObject.get("latitude").toString());
            location.setLongitude(jsonObject.get("longitude").toString());
        }
        return location;
    }
}


