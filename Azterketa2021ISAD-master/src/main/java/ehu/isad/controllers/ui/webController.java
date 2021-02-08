package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.webDB;
import ehu.isad.model.webModel;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class webController implements Initializable {

    @FXML
    private TextField textField;

    @FXML
    private Button checkBTN;

    @FXML
    private TableView<webModel> tabla;

    @FXML
    private TableColumn<webModel, String> urlColumn;

    @FXML
    private TableColumn<webModel, String> md5Column;

    @FXML
    private TableColumn<webModel, String> versionColumn;

    @FXML
    private Label lbl;
    Utils utils = new Utils();

    ObservableList<webModel> lista = FXCollections.observableArrayList();

    private static webController instance=new webController();

    private webController() {}

    public static webController getInstance() {
        return instance;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loadTabla();
        lbl.setText("");
    }

    public void loadTabla(){
        tabla.setEditable(true);
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        md5Column.setCellValueFactory(new PropertyValueFactory<>("md5"));

        //Editagarria egiteko.
        versionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        versionColumn.setOnEditCommit((TableColumn.CellEditEvent<webModel, String> event) -> {
                    TablePosition<webModel, String> pos = event.getTablePosition();
                    int row = pos.getRow();
                    webModel captchaModel = event.getTableView().getItems().get(row);
                    String content = event.getNewValue();
                    captchaModel.setVersion(content);
                    webDB.getInstance().addToDB(captchaModel);
                });
        tabla.setItems(lista);
    }
    @FXML
    void onClick(ActionEvent event) {
        String md5 = "";
        String url = "";
        url = textField.getText() + "/README";
        try {
            if (url.contains("https") || url.contains("http") ) {
                utils.download2(url); //FUNTZIONATZEN DU
                InputStream is = Files.newInputStream(Paths.get(Utils.getProperties().getProperty("pathToFile")));
                md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is); //FUNTZIONATZEN DU
                this.gehituListara(url, md5); //FUNTZIONATZEN DU
            }else {
                //URL ez badago ondo
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error on URL");
                alert.setHeaderText("Error on reading the provided URL");
                alert.setContentText("The URL "+ url + "  doesn't seem tohave protocol");
                alert.showAndWait();
            }
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getVersion(String value) throws IOException { //EZ dut erabiltzen, baina funtzionatzen du. Datu basean dagoen jakiteko MD5 konparatzen dut.
        //Arazoak ez egoteko, zenbakien sarrerakin tableviewarekin edo, 1.(espazio) 2.3 jartzen badu edo zerbait.
        var url = new URL(value);
        try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            var sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (sb.toString().contains("Version")) {
                   // System.out.println(sb.toString().split(" ")[3]);
                    return sb.toString().split(" ")[3]; //Hemen dago bertsioa.
                }
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            System.out.println("Version not found");
        }
        return null;
    }
    private void gehituListara(String url, String md5) {
        //md5 badago gehitu bertsioa bestela ez.
        webModel wb = new webModel(url,md5,"");
        wb= webDB.getInstance().gaineratuBertsioa(wb);
        if(wb.getVersion().equals("")) {lbl.setText("Ez zegoen datu basean"); }else{ lbl.setText("Datubasean zegoen"); }
        lista.add(wb);
    }
}
