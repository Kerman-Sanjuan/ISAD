package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.DBController;
import ehu.isad.controllers.db.MainDB;
import ehu.isad.model.TxanponaModel;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    //Singleton
    private static mainController instance=new mainController();
    private mainController() {}
    public static mainController getInstance() {
        return instance;
    }
    private DBController dbController = DBController.getController();
    private MainDB  mainDB = MainDB.getInstance();
    Utils utils;
    @FXML
    private Button sartuBTN;

    @FXML
    private Button gordeBTN;

    @FXML
    private ComboBox<String> conbo;

    @FXML
    private TableView<TxanponaModel> tabla;
    @FXML
    private TableColumn<TxanponaModel, Integer> tablaID;

    @FXML
    private TableColumn<TxanponaModel, String> tablaTxanpon;

    @FXML
    private TableColumn<TxanponaModel, Date> TablaData; //Puede ser el error en caso raro

    @FXML
    private TableColumn<TxanponaModel,Double > TablaZenbat;

    @FXML
    private TableColumn<TxanponaModel, Float> TablaBolumena;
    @FXML
    private TableColumn<TxanponaModel, Image> TablaPortaera;

    @FXML
    void onClick(ActionEvent event) throws IOException, SQLException {
        Button btn = (Button) event.getSource();
        if (sartuBTN.equals(btn)) {
                System.out.println(conbo.getValue());
                TxanponaModel txp = Utils.readFromUrlTxanpona(conbo.getValue());
                mainDB.sartuTxanpona(txp,conbo.getValue());
                this.loadTabla();
            System.out.println(txp.toString());
        }else{ //Two buttons, so this is the other case.



        }
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablaID.setCellValueFactory(new PropertyValueFactory<>("trade_id"));
        TablaZenbat.setCellValueFactory(new PropertyValueFactory<>("price"));
        tablaTxanpon.setCellValueFactory(new PropertyValueFactory<>("type"));
        TablaData.setCellValueFactory(new PropertyValueFactory<>("time"));
        TablaBolumena.setCellValueFactory(new PropertyValueFactory<>("volume"));
        TablaPortaera.setCellValueFactory(new PropertyValueFactory<>("portaera"));
        //Para que la imageen se coloque de forma correcta.
        TablaPortaera.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(25);
                    imageview.setFitWidth(40);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
        try {
            this.loadConbo();
            this.loadTabla();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.loadArgazkiak();
    }

    private void loadArgazkiak() {

    }

    public void loadConbo() throws SQLException {
        this.conbo.setItems(mainDB.getTxanponMotak());
    }
    public void loadTabla() throws SQLException {
        tabla.setItems(mainDB.getTxanponak());

    }
}

