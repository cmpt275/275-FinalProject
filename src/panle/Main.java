package panle;

import entity.FiveDaysForecast;
import entity.Location;
import entity.Weather;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static Api.getIPAddress.getIP;
import static Api.weatherForecast.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        //CalendarProgram mode =new CalendarProgram();
        Events events= new Events();
        if(false) {
            try {
                /////////////weather////////////////////////
                String publicIP = getIP();
                Location loc = getLatLong(publicIP);
                List<FiveDaysForecast> wlist = getNextFiveDayWeather(loc.getLatitude(), loc.getLongitude());
                int c = 0;
                String[] d = {"Today", "Tomorrow", "NextDay", "NextDay", "NextDay"};
                for (FiveDaysForecast ls : wlist) {
                    String temp = ls.getTemp();
                    String hum = ls.getRelative_humidity();
                    Image img = ls.getIconImage();
                    ImageIcon icon = new ImageIcon(img);
                    Weather weather = ls.getWeather();
                    String description = weather.getDescription();


                    JLabel day1 = new JLabel();

                    events.weatherP.add(day1);
                    day1.setBounds(0 + 252 * c, 0, 251, 85);
                    day1.setOpaque(true);
                    day1.setBackground(Color.white);
                    day1.setBorder(BorderFactory.createTitledBorder("<html><font color=blue size=4><h>" + d[c] + "</h></html>\""));
                    day1.setText("<html><font color=black size=3><body><p><b>Temp:" + temp + "° C</b></p>\n<p><b>Humidity:" + hum + "%</b></p>\n<p><b>" + description + "</b></p></body></html>\"");
                    day1.setIcon(icon);

                    c++;
                }
                ////////////end weather////////////////////
            } catch (IOException ioException) {
                ioException.printStackTrace();
                JLabel day1 = new JLabel("<html><font color=red size=4><body><p><b>" + "No Internet" + "</b></p><p>Cannot Load Weather Information</p></body></html>\"");
                events.weatherP.add(day1);
                day1.setBounds(20, 0, 251, 85);
            }
        }
        //  getCurrentWeatherByLatLong("49.22830624640323", "-122.9998086267361");
        //   getNextFiveDaysWeather("49.22830624640323", "-122.9998086267361");
//        get120HoursForecast("49.22830624640323", "-122.9998086267361");
//        getLatLong("abc");
        //  String publicIP = getIP();
        events.frmMain.setVisible(true);
        System.out.println("Started");


    }
}
