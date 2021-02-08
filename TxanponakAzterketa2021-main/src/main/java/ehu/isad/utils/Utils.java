package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.model.ProbaModel;
import ehu.isad.model.TxanponaModel;

import java.awt.print.Book;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.stream.Stream;

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
    public static TxanponaModel readFromUrlTxanpona(String txanpon) throws IOException {
            URL openlibrary = new URL("https://api.gdax.com/products/"+txanpon+"-eur/ticker");
            URLConnection yc = openlibrary.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            Gson gson = new Gson();
            System.out.println(inputLine);
            return gson.fromJson(inputLine, TxanponaModel.class);
        }
    }
