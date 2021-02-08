package ehu.isad.utils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class Utils {

    public static Properties getProperties()  {
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


    public void download2(String urlString) throws IOException {
            InputStream inputStream = new URL(urlString).openStream();
            Files.copy(inputStream, Paths.get(Utils.getProperties().getProperty("pathToFile")), StandardCopyOption.REPLACE_EXISTING);

        }

}