package ehu.isad.controllers.db;

import ehu.isad.model.TxanponaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainDB {
    private static final MainDB instance = new MainDB();
    private static final DBController dbcontroller = DBController.getController();

    private MainDB() {
    }

    public static MainDB getInstance() {
        return instance;
    }

    //Needed methods.

    public ObservableList<String> getTxanponMotak() throws SQLException {
        ObservableList<String> lista = FXCollections.observableArrayList();
        String query = "select distinct(mota) from txanponak";
        ResultSet rs = dbcontroller.execSQL(query);
        while (rs.next()) lista.add(rs.getString("mota"));
        System.out.println(lista);
        return lista;
    }

    public ObservableList<TxanponaModel> getTxanponak() throws SQLException {
        ObservableList<TxanponaModel> lista = FXCollections.observableArrayList();
        String query = "select * from txanponak";
        ResultSet rs = dbcontroller.execSQL(query);

        while (rs.next()) {
            Float balioa = rs.getFloat("balioa");
            System.out.println(rs.getFloat("balioa"));
            Float bolumena = rs.getFloat("bolumena");
            Integer id = rs.getInt("id");
            System.out.println(id);
            String mota = rs.getString("mota");
            String date = rs.getDate("data").toString();
            TxanponaModel txp = new TxanponaModel(id, balioa, date, bolumena, mota);
            txp.setPortaera(this.portaeraDefinitu(txp.getPrice(), txp.getType()));
            lista.add(txp);
        }
        return lista;
    }

    private Image portaeraDefinitu(Float balioa, String mota) {
        Image img = new Image("/images/equal.png");
        //Los paths posibles para la foto.
        return img;
    }

    public void sartuTxanpona(TxanponaModel txp, String mota) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String query = "insert into txanponak(data,balioa,mota,bolumena) values('" + dateFormat.format(date).replace("/", "-") + "'," + txp.getPrice() + ",'" + mota + "'," + txp.getVolume() + ");";
        System.out.println(query);
        ResultSet rs = dbcontroller.execSQL(query);
    }

    public String lortuNorabidea(float price, String time,String txanpona) {
        String query = "select * from txanponak where id= (select MAX(id) from txanponak where mota = '"+txanpona+"')"; //Ultimo precio
        System.out.println(query);
        String result = "";
        try {
            ResultSet rs = DBController.getController().execSQL(query);
            rs.next();
            Float balioa = rs.getFloat("balioa");
            if (balioa < price) result = "up.png";
            else if (balioa > price) result = "down.png";
            else result = "equal.png";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
