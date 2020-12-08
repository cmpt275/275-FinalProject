package Api;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class getIPAddressTest {
    private static String response;

    @BeforeAll
    public static void setUp() throws IOException {
        response = getIPAddress.getIP();
    }

    @Test
    void getIPTest(){
        assertEquals(!response.isEmpty(), !response.isEmpty());
    }

}