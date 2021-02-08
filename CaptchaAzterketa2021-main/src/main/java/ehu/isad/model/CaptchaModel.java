package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CaptchaModel {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer id;
    String filename;
    String value;
    int date;
    Image image;

    public CaptchaModel(Integer id,String filaename, String value, int date){
        this.id = id;
        this.filename = filaename;
        this.value = value;
        this.date = date;
        this.setArgazkia();

    }

    private void setArgazkia() {
        try {
            this.image = new Image(new FileInputStream(Utils.getProperties().getProperty("pathToImages")+filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filaename) {
        this.filename = filaename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
