package ehu.isad.controllers.db;

import ehu.isad.model.CaptchaModel;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaptchaDB {

    private static final CaptchaDB instance = new CaptchaDB();
    private static final DBController dbcontroller = DBController.getController();

    private CaptchaDB() {}

    public static CaptchaDB getInstance() {
        return instance;
    }

    public void addToDB(){
        String query = "INSERT INTO xxxx(xx,xx,xx) VALUES ('xx','xx','xx')";
        dbcontroller.execSQL(query);
    }

    public ObservableList<CaptchaModel> getFromDB(){
        String query = "SELECT * FROM captchas";
        ObservableList<CaptchaModel> list = FXCollections.observableArrayList();
        ResultSet rs = dbcontroller.execSQL(query);
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String filename = rs.getString("filename");
                String value = rs.getString("value");
                Integer data =  rs.getInt("date");
                CaptchaModel cp = new CaptchaModel(id,filename,value,data);
                cp.setCaptcha(new Image(new FileInputStream(Utils.getProperties().getProperty("pathToImages")+filename)));
                list.add(cp);
            }
        } catch(SQLException | FileNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }

    public void gordeDatuBasean(ObservableList<CaptchaModel> lista) {
        String query = "delete from captchas";
        DBController.getController().execSQL(query);
        for (CaptchaModel cp : lista){
            query = "insert into captchas values('"+cp.getId()+"','"+cp.getFilename()+"','"+cp.getValue()+"',"+cp.getData()+");";
            System.out.println(query);
            DBController.getController().execSQL(query);
        }
    }
}
