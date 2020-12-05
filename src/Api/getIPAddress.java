package Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class getIPAddress {

    public static String getIP() throws IOException {

        InetAddress localhost = InetAddress.getLocalHost();
        String publicIPAddress = "";

        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            publicIPAddress = sc.readLine().trim();
        } catch (MalformedURLException e) {
            publicIPAddress = "Cannot Execute Properly";
        }
        return publicIPAddress;
    }

}
