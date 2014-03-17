package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class http {
    public static String get(String url) throws MalformedURLException, IOException {
        return IO.inputStreamToString(new URL(url).openStream());
    }
}
