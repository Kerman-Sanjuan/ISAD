package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.DBController;
import ehu.isad.controllers.db.ProbaDB;
import ehu.isad.model.CaptchaModel;
import ehu.isad.model.ProbaModel;
import ehu.isad.utils.Utils;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ProbaController implements Initializable {
    ObservableList<CaptchaModel> lista = ProbaDB.getInstance().lortuLista();
    private static ProbaController instance=new ProbaController();
    ProbaDB probaDB = ProbaDB.getInstance();
    private ProbaController() {}

    public static ProbaController getInstance() {
        return instance;
    }
    @FXML
    private TableColumn<CaptchaModel, Integer> idTable;

    @FXML
    private TableColumn<CaptchaModel, String> pathTable;

    @FXML
    private TableColumn<CaptchaModel, String> contentTable;

    @FXML
    private TableColumn<CaptchaModel, Date> dateTable;

    @FXML
    private TableColumn<CaptchaModel, Image> irudiaTable;

    @FXML
    private Button txertatuBTN;

    @FXML
    private Button gordeBTN;
    @FXML
    private TableView<CaptchaModel> tabla;

    @FXML
    void onClick(ActionEvent event) {
        if (event.getSource().equals(txertatuBTN)){
           this.gehituListara();
        }else{
            this.gordeLista();
        }

    }

    private void gordeLista() {
        for(CaptchaModel captchaModel : lista){
            probaDB.gehituLista(captchaModel);
        }
    }


    private void gehituListara()  {
        try {
            Integer id;
            Utils utils = new Utils();
            if(lista.size()==0) {
                id=1;
            }else{
                id = lista.get(lista.size() - 1).getId() + 1;
            }
            File file = File.createTempFile("captcha", ".png", new File(Utils.getProperties().getProperty("pathToImages")));
            BufferedImage img = ImageIO.read(new URL("http://45.32.169.98/captcha.php"));
            ImageIO.write(img, "png", file);
            CaptchaModel captchaModel = new CaptchaModel(id, file.getName(), "", (int) System.currentTimeMillis()/1000);
            lista.add(captchaModel);
        }catch(IOException e){
            System.out.println("he petado");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.loadTable();

    }
    private void loadTable() {
        tabla.setEditable(true);
        idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        pathTable.setCellValueFactory(new PropertyValueFactory<>("filename"));
        contentTable.setCellValueFactory(new PropertyValueFactory<>("value"));
        contentTable.setCellFactory(TextFieldTableCell.forTableColumn());

        irudiaTable.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
        contentTable.setOnEditCommit((TableColumn.CellEditEvent<CaptchaModel, String> event) -> {
            TablePosition<CaptchaModel, String> pos = event.getTablePosition();
            int row = pos.getRow();
            CaptchaModel captchaModel = event.getTableView().getItems().get(row);
            String content = event.getNewValue();
            captchaModel.setValue(content);

        });
        dateTable.setCellValueFactory(new PropertyValueFactory<>("date"));
        irudiaTable.setCellValueFactory(new PropertyValueFactory<>("image"));
        tabla.setItems(lista);

    }

}
