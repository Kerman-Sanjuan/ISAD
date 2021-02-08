package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.model.ProbaModel;
import javafx.scene.image.Image;

import java.awt.print.Book;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
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

    private ProbaModel readFromUrl(String value) throws IOException {
        // get JSON
        URL api = new URL("website.com?id="+value+"");
        URLConnection yc = api.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine(); // raw data
        in.close();
        // process if needed
        Gson gson = new Gson();
        return gson.fromJson(inputLine, ProbaModel.class);
    }
    public   Image createImage() throws IOException {
        String url = "http://45.32.169.98/captcha.php";
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }
}