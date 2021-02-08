package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.hautatuDB;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class hautatuController implements Initializable {
    @FXML
    private ImageView img;

    @FXML
    private Label labelT;

    @FXML
    private ComboBox<Herrialdea> combo;

    @FXML
    private Button btn;


    @FXML
    void onClick(ActionEvent event) throws SQLException {
        if (hautatuDB.getInstantzia().botoKopurua(combo.getValue()) > 4) {
            asd.setErrorUI();
        } else {
            asd.setBotoakUI();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Properties properties = Utils.lortuEzarpenak();
        img.setImage(new Image("/images/europision.png"));
        labelT.setText("Aukeratu zein herrialdeak egin nahi duen bozkaketa:");
        combo.setItems(
                FXCollections.observableArrayList(this.lortuHerrialdeak())

        );



    }
    private Main asd;
    public void setMainApp(Main main) {
        this.asd = main;
    }
    private List<Herrialdea> lortuHerrialdeak(){
       return hautatuDB.getInstantzia().lortuHerrialdeak();

    }

    public Herrialdea bidali(){
        return combo.getValue();
    }
}