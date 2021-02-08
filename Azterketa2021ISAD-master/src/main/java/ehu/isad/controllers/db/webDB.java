package ehu.isad.controllers.db;

import ehu.isad.model.webModel;
import java.sql.ResultSet;
import java.sql.SQLException;


public class webDB {

    private static final webDB instance = new webDB();
    private static final DBController dbcontroller = DBController.getController();

    private webDB() {}

    public static webDB getInstance() {
        return instance;
    }

    public void addToDB(webModel captchaModel){
        String query = "INSERT INTO checksums(idCMS,version,md5,path) VALUES ("+1+",'"+captchaModel.getVersion()+"','"+captchaModel.getMd5()+"','README')";
        dbcontroller.execSQL(query);
    }


    public webModel gaineratuBertsioa(webModel wb) {
        String query = "select version from checksums where md5 = '" + wb.getMd5() + "';";
        System.out.println(query);
        ResultSet rs = DBController.getController().execSQL(query);
        try {
            if (rs.next()) {
                String vs = rs.getString("version");
                System.out.println(vs);
                wb.setVersion(vs);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wb;
    }
}
