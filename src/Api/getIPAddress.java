package Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class getIPAddress {
    /*Get the local IPV4 address*/
    public static String getIP() throws IOException {

        InetAddress localhost = InetAddress.getLocalHost();
        String publicIPAddress;

        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            publicIPAddress = sc.readLine().trim();
            System.out.println(publicIPAddress);
        } catch (MalformedURLException e) {
            publicIPAddress = "";
        }
        return publicIPAddress;
    }

}
