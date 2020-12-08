package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private static Location location;

    @BeforeAll
    public static void setUp(){
        location = new Location("49.214599770271455","-122.98873646818907");
    }

    @Test
    void testSetLatitude() {
        location.setLatitude("49.266193");
        assertEquals("49.266193", location.getLatitude());
    }

    @Test
    void testGetLatitude() {
        assertEquals("49.266193", location.getLatitude());
    }

    @Test
    void testSetLongitude() {

        location.setLongitude("-123.18207083492435");
        assertEquals("-123.18207083492435", location.getLongitude());
    }

    @Test
    void testGetLongitude() {
        assertEquals("-123.18207083492435", location.getLongitude());
    }

}