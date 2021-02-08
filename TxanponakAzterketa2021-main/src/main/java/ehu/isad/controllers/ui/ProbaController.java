package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.DBController;
import javafx.fxml.FXML;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProbaController {


    private static ProbaController instance=new ProbaController();

    private ProbaController() {}

    public static ProbaController getInstance() {
        return instance;
    }
    private DBController dbController = DBController.getController();
    @FXML
    void initialize() throws SQLException {
        ResultSet rs = dbController.execSQL("select * from txanponak where id = '52'");
        if(rs.next())  System.out.println(rs.getDate("data"));
    }
}
