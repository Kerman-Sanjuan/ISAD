package ehu.isad.utils;

import ehu.isad.Book;
import com.google.gson.Gson;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    public static Book readFromUrl(String isbn) throws IOException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();

        String[] zatiak = inputLine.split("ISBN:" + isbn + "\":");
        inputLine = zatiak[1].substring(0, zatiak[1].length() - 1);

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Book.class);
    }

    public static Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }
}
