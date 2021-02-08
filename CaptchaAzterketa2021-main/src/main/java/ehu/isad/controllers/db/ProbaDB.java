package ehu.isad.controllers.db;

import ehu.isad.model.CaptchaModel;
import ehu.isad.model.ProbaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProbaDB {

    private static final ProbaDB instance = new ProbaDB();
    private static final DBController dbcontroller = DBController.getController();

    private ProbaDB() {

    }

    public static ProbaDB getInstance() {
        return instance;
    }

    public void addToDB() {
        String query = "INSERT INTO xxxx(xx,xx,xx) VALUES ('xx','xx','xx')";
        dbcontroller.execSQL(query);
    }

    public List<ProbaModel> getFromDB() {
        String query = "SELECT xxxx FROM xxxx";
        List<ProbaModel> list = new ArrayList<>();
        ResultSet rs = dbcontroller.execSQL(query);
        try {
            while (rs.next()) {
                String xx1 = rs.getString("xx");
                String xx2 = rs.getString("xx");
                list.add(new ProbaModel(xx1, xx2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<CaptchaModel> lortuLista(){
        ObservableList<CaptchaModel> lista = FXCollections.observableArrayList();
        String query = "Select * from captchas";
        ResultSet rs = DBController.getController().execSQL(query);
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String filename= rs.getString("filename");
                String value = rs.getString("value");
                int date = rs.getInt("date");
                CaptchaModel captchaModel = new CaptchaModel(id,filename,value,date);
                lista.add(captchaModel);
            }
        }catch (SQLException e){
                e.printStackTrace();
            }
        return lista;
    }

    public void gehituLista(CaptchaModel captchaModel) {
        String query = "insert into captchas values("+captchaModel.getId()+",'"+captchaModel.getFilename()+"','"+captchaModel.getValue()+"',"+captchaModel.getDate()+");";
        DBController.getController().execSQL(query);
    }
}
