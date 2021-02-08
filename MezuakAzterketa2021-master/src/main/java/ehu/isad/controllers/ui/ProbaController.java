package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.ProbaDB;
import ehu.isad.model.MezuaModel;
import ehu.isad.model.ProbaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProbaController implements Initializable {

    ObservableList<MezuaModel> lista = FXCollections.observableArrayList();

    private static ProbaController instance=new ProbaController();

    private ProbaController() {}

    public static ProbaController getInstance() {
        return instance;
    }

    @FXML
    private TableView<MezuaModel> tabla;

    @FXML
    private TableColumn<MezuaModel, String> fromColumn;

    @FXML
    private TableColumn<MezuaModel, String> toColumb;

    @FXML
    private TableColumn<MezuaModel, String> messageColumn;

    @FXML
    private Button databaseBTN;

    @FXML
    void onClick(ActionEvent event) {
        ProbaDB.getInstance().gordeDatuBasean(lista);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.kargatuMezuak();
        this.tablaSetup();
    }

    private void tablaSetup() {


        fromColumn.setCellValueFactory(new PropertyValueFactory<>("nor"));
        toColumb.setCellValueFactory(new PropertyValueFactory<>("nori"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("mezua"));

        tabla.setEditable(true);
        fromColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fromColumn.setOnEditCommit((TableColumn.CellEditEvent<MezuaModel, String> event) -> {
            TablePosition<MezuaModel, String> pos = event.getTablePosition();
            int row = pos.getRow();
            MezuaModel captchaModel = event.getTableView().getItems().get(row);
            String content = event.getNewValue();
            captchaModel.setNor(content);

        });
        toColumb.setCellFactory(TextFieldTableCell.forTableColumn());
        toColumb.setOnEditCommit((TableColumn.CellEditEvent<MezuaModel, String> event) -> {
            TablePosition<MezuaModel, String> pos = event.getTablePosition();
            int row = pos.getRow();
            MezuaModel captchaModel = event.getTableView().getItems().get(row);
            String content = event.getNewValue();
            captchaModel.setNori(content);

        });
        messageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        messageColumn.setOnEditCommit((TableColumn.CellEditEvent<MezuaModel, String> event) -> {
            TablePosition<MezuaModel, String> pos = event.getTablePosition();
            int row = pos.getRow();
            MezuaModel captchaModel = event.getTableView().getItems().get(row);
            String content = event.getNewValue();
            captchaModel.setMezua(content);

        });
        tabla.setItems(lista);
    }

    private void kargatuMezuak() {
        lista = ProbaDB.getInstance().getFromDB();
    }
}
