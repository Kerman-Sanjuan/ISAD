package ehu.isad.utils;


import ehu.isad.model.CaptchaModel;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Utils {

    public static Properties getProperties() {
        Properties properties = null;

        try (InputStream in = Utils.class.getResourceAsStream("/setup.properties")) {
            properties = new Properties();
            properties.load(in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private void readFromUrl(String value) throws IOException {
        // get JSON
        URL api = new URL("website.com?id=" + value + "");
        URLConnection yc = api.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine(); // raw data
        in.close();
        // process if needed
    }
}
