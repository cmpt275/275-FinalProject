package Api.test;

import Api.getIPAddress;
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
    void TestGetIP(){
        assertNotNull(response);
    }

}