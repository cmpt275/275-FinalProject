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

        if(true) {
            int currentday = events.realDay;
            int currentY = events.realYear;
            int currentM = events.realMonth+1;
            String[] d = {"Today", "Tomorrow", "NextDay", "NextDay", "NextDay"};
            for(int i = 0;i<5;i++){
                if (i ==0) {
                    d[i] = str_week(currentY, currentM, (currentday + i))+" (Today)  "+currentM+"/"+currentday+"/"+currentY;
                }else {
                    d[i] = str_week(currentY, currentM, (currentday + i));
                }

            }
            try {
                /////////////weather////////////////////////
                String publicIP = getIP();
                Location loc = getLatLong(publicIP);
                List<FiveDaysForecast> wlist = getNextFiveDayWeather(loc.getLatitude(), loc.getLongitude());
                int c = 0;
                //String[] d = {"Today", "Tomorrow", "NextDay", "NextDay", "NextDay"};
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
                JLabel day1 = new JLabel("<html><font color=red size=3><body><p><b>" + "No Internet" + "</b></p><p>Cannot Load Weather Information</p></body></html>\"");
                events.weatherP.add(day1);
                day1.setBounds(5, 0, 251, 85);
                day1.setBorder(BorderFactory.createTitledBorder("<html><font color=blue size=4><h>" + d[0] + "</h></html>\""));
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

    private static String str_week(int y,int m,int d){
        int a=7;
        String str_date="";
        if((m==1)||(m==2))
        {
            m+=12;
            y--;
        }
        a=(d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)%7;
        switch (a){
            case 0:
                str_date="Monday";
                break;
            case 1:
                str_date="Tuesday";
                break;
            case 2:
                str_date="Wednesday";
                break;
            case 3:
                str_date="Thursday";
                break;
            case 4:
                str_date="Friday";
                break;
            case 5:
                str_date="Saturday";
                break;
            case 6:
                str_date="Sunday";
                break;
        }
        return str_date;
    }

}
