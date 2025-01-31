package Api;


import entity.Holiday;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class holiday {
    /*get the JSON String holiday response from API*/
    public static String getHoliday(String year, String month) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://holidays.abstractapi.com/v1/?api_key=7c04e2bd10d745818b2f9a9efeeb6fb1&country=CA&year="+year+"&month="+month))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseEntity = response.body();
        System.out.println(responseEntity);

        return responseEntity;
    }

    /*Refactor the response and output the distinct holiday list*/
    public static List<Holiday> getThisMonthHolidayList(String year, String month) throws InterruptedException {
        List<Holiday> distinctHolidayList = new ArrayList<>();
        try{
            String response = getHoliday(year, month);
            JSONArray jsonArray = JSONArray.parseArray(response);

            List<Holiday> holidayList = new ArrayList<>();
            for (Object holidayObject : jsonArray) {
                JSONObject holidayInfo = (JSONObject) holidayObject;

                Holiday holiday = new Holiday();
                holiday.setName((String) holidayInfo.get("name"));
                holiday.setDate((String) holidayInfo.get("date"));
                holiday.setDate_year((String) holidayInfo.get("date_year"));
                holiday.setDate_month((String) holidayInfo.get("date_month"));
                holiday.setDate_day((String) holidayInfo.get("date_day"));

                holidayList.add(holiday);
            }

            /*Find the distinct holiday list*/
            Holiday fistHoliday = holidayList.get(0);
            distinctHolidayList = new ArrayList<>();
            distinctHolidayList.add(fistHoliday);
            System.out.println(holidayList.size());

            for(int i = 0; i< holidayList.size(); i++ ){
                if(i < holidayList.size() -1){
                    Holiday distinctHoliday = holidayList.get(i);
                    if(!distinctHoliday.getDate_day().equals(holidayList.get(i+1).getDate_day())){
                        distinctHolidayList.add(holidayList.get(i+1));
                    }
                }else{
                    Holiday distinctHoliday = holidayList.get(i);
                    if(!distinctHoliday.getDate_day().equals(holidayList.get(i).getDate_day())){
                        distinctHolidayList.add(distinctHoliday);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return distinctHolidayList;
    }
}
