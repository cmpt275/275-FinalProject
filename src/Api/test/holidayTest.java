package Api.test;

import Api.holiday;
import entity.Holiday;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class holidayTest {
    private static String response;
    private static List<Holiday> holidayList;

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        response = holiday.getHoliday("2020","12");
    }

    @Test
    void TestGetResponse(){
        assertNotNull(response);
    }

    @Test
    void TestGetHolidayResponse() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        holidayList = holiday.getThisMonthHolidayList("2020", "12");
        assertNotNull(holidayList);
    }

    @Test
    void TestGetHolidayListSize() throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(2);
        holidayList = holiday.getThisMonthHolidayList("2020", "12");
        assertEquals(8, holidayList.size());
    }

}