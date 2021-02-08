package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.botoakDB;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class botoakController implements Initializable {
    botoakDB db = botoakDB.getInstantzia();
    private ObservableList<Ordezkaritza> ordezkatu;
    @FXML
    private TableView<Ordezkaritza> tabla;

    @FXML
    private TableColumn<Ordezkaritza, String> cHerrialdea;

    @FXML
    private TableColumn<Ordezkaritza, String> tArtista;

    @FXML
    private TableColumn<Ordezkaritza, String> tAbestia;

    @FXML
    private TableColumn<Ordezkaritza, Integer> tPuntuak;

    @FXML
    private Button botoia;

    @FXML
    private ImageView imgEurobisioa;

    @FXML
    private ImageView imgHerrialde;

    @FXML
    private Label lblText;

    @FXML
    void onClick(ActionEvent event) throws SQLException {
        asd.setTopUI();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void hasieratu(Herrialdea herrialdea) throws SQLException {
        tabla.setEditable(true);
        imgEurobisioa.setImage(new Image("/images/europision.png"));
        imgHerrialde.setImage(new Image("/images/"+herrialdea.toString().toLowerCase()+".png"));
        lblText.setText(herrialdea.toString() +" horrela banatu nahi ditu bere puntuak.");
        //Hasieratu zelden bistaraketa
        tPuntuak.setCellValueFactory(new PropertyValueFactory<>("puntuak"));
        tAbestia.setCellValueFactory(new PropertyValueFactory<>("abeztia"));
        tArtista.setCellValueFactory(new PropertyValueFactory<>("abezlaria"));
        cHerrialdea.setCellValueFactory(new PropertyValueFactory<>("herrialdea"));
        //Zelden begiraketa
        tPuntuak.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tPuntuak.setOnEditCommit((TableColumn.CellEditEvent<Ordezkaritza, Integer> event) -> {

            TablePosition<Ordezkaritza, Integer> pos = event.getTablePosition();
            int row = pos.getRow();
            Ordezkaritza ordezkaritza = event.getTableView().getItems().get(row);
            Integer newUserName = event.getNewValue();
            botoakDB.getInstantzia().puntuakGehitu(ordezkaritza.getHerrialdea(),newUserName);
            ordezkaritza.setPuntuak(newUserName);

        });
        //Hasieratu tablak
        ordezkatu = FXCollections.observableArrayList(botoakDB.getInstantzia().erakutsiAukerak(herrialdea));
        tabla.setItems(ordezkatu);
    }

    Main asd;
    public void setMainApp(Main main) {
        this.asd = main;
    }
}
