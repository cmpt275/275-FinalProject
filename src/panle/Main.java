package panle;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Api.getIPAddress.getIP;
import static Api.weatherForecast.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
	// write your code here
        //CalendarProgram mode =new CalendarProgram();
        Events events= new Events();
      //  getCurrentWeatherByLatLong("49.22830624640323", "-122.9998086267361");
     //   getNextFiveDaysWeather("49.22830624640323", "-122.9998086267361");
//        get120HoursForecast("49.22830624640323", "-122.9998086267361");
//        getLatLong("abc");
        String publicIP = getIP();
        System.out.println(publicIP);

    }
}
