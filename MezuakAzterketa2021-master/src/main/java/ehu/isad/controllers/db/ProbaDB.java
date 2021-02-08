package ehu.isad.controllers.db;

import ehu.isad.model.MezuaModel;
import ehu.isad.model.ProbaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProbaDB{

    private static final ProbaDB instance = new ProbaDB();
    private static final DBController dbcontroller = DBController.getController();

    private ProbaDB() {}

    public static ProbaDB getInstance() {
        return instance;
    }

    public void addToDB(){
        String query = "INSERT INTO xxxx(xx,xx,xx) VALUES ('xx','xx','xx')";
        dbcontroller.execSQL(query);
    }

    public ObservableList<MezuaModel> getFromDB(){
        String query = "SELECT * FROM DirectMessage";
        ObservableList<MezuaModel> lista = FXCollections.observableArrayList();
        ResultSet rs = dbcontroller.execSQL(query);
        try {
            while (rs.next()) {
                String nor = rs.getString("fromUser");
                String nori = rs.getString("toUser");
                String mezua =  rs.getString("message");
                lista.add(new MezuaModel(nor,nori,mezua));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void gordeDatuBasean(ObservableList<MezuaModel> lista) {
        String query = "Delete from DirectMessage";
        DBController.getController().execSQL(query);
        for (MezuaModel mezua : lista) {
            query = "insert into DirectMessage(fromUser,toUser,message) values('"+mezua.getNor()+"','"+mezua.getNori()+"','"+mezua.getMezua()+"');";
            System.out.println(query);
            DBController.getController().execSQL(query);
        }
    }
}
