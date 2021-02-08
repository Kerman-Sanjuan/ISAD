package ehu.isad.model;

import javafx.scene.image.Image;

import java.util.Date;

public class CaptchaModel {

    int id;
    String filename, value;
    Integer data;

    public Image getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Image captcha) {
        this.captcha = captcha;
    }

    Image captcha;

    public CaptchaModel(int id, String filename, String value, Integer data) {
        this.id = id;
        this.filename = filename;
        this.value = value;
        this.data = data;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }



}
