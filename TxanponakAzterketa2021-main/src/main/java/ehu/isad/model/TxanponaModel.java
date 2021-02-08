package ehu.isad.model;

import ehu.isad.controllers.db.DBController;
import ehu.isad.controllers.db.MainDB;
import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TxanponaModel {
    float size;
    String time;
    float bd;
    float ak;
    float volume;
    int trade_id;
    String type;
    float price;
    private Image portaera;
    public TxanponaModel(int trade_id, float price, String time, float volume, String type) {
        this.trade_id = trade_id;
        this.price = price;
        this.time = time;
        this.volume = volume;
        this.type = type;

        String imagePath = Utils.getProperties().getProperty("pathToImages")+"equals.png";

        try {

            this.portaera = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String selectImage(float price, String time) {
        return MainDB.getInstance().lortuNorabidea(price,time,this.getType());
    }




    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getBid() {
        return bd;
    }

    public void setBid(float bid) {
        this.bd = bid;
    }

    public float getAsk() {
        return ak;
    }

    public void setAsk(float ask) {
        this.ak = ask;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Image getPortaera() {
        return portaera;
    }

    public void setPortaera(Image portaera) {
        this.portaera = portaera;
    }



    @Override
    public String toString() {
        return "TxanponaModel{" +
                "trade_id=" + trade_id +
                ", price=" + price +
                ", size=" + size +
                ", time='" + time + '\'' +
                ", bid=" + bd +
                ", ask=" + ak +
                ", volume=" + volume +
                ", type=" + type +
                '}';
    }

}

