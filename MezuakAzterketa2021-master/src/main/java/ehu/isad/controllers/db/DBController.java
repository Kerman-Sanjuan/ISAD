package ehu.isad.controllers.db;

import ehu.isad.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DBController {

    Connection conn=null;
    private static final DBController controller = new DBController();
    private final String pathToSQLiteDB = Utils.getProperties().getProperty("pathToDB"); //SQLite
//    private final String nameOfMySQLDB = Utils.getProperties().getProperty("pathToDB"); //MySQL

    private DBController() {
        this.conOpen();
    }

    public static DBController getController() {
        return controller;
    }

    private void conOpen() {
        try {
            // MySQL
//            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", Utils.getProperties());
//            conn.setCatalog(nameOfMySQLDB);
            // SQLite
            conn = DriverManager.getConnection("jdbc:sqlite:"+pathToSQLiteDB);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
//        catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

    private void conClose() {

        if (conn != null)
            try {
                conn.close();
                System.out.println("Connection to SQLite has been terminated.\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public ResultSet execSQL(String query) {
        conClose();
        conOpen();
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                // select
                rs = s.executeQuery(query);
            } else {
                // update, delete, create, insert...
                int count = s.executeUpdate(query);
                System.out.println(count + " rows affected");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return rs;
    }

}
