package ehu.isad.controllers.db;

import ehu.isad.model.ProbaModel;

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

    public List<ProbaModel> getFromDB(){
        String query = "SELECT xxxx FROM xxxx";
        List<ProbaModel> list = new ArrayList<>();
        ResultSet rs = dbcontroller.execSQL(query);
        try {
            while (rs.next()) {
                String xx1 = rs.getString("xx");
                String xx2 = rs.getString("xx");
                list.add(new ProbaModel(xx1,xx2));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
