package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.CaptchaDB;
import ehu.isad.model.CaptchaModel;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CaptchaController implements Initializable {

    ObservableList<CaptchaModel> lista = FXCollections.observableArrayList();

    private static CaptchaController instance=new CaptchaController();

    private CaptchaController() {}

    public static CaptchaController getInstance() {
        return instance;
    }
    @FXML
    private TableView<CaptchaModel> tabla;

    @FXML
    private TableColumn<CaptchaModel, Integer> idColumn;

    @FXML
    private TableColumn<CaptchaModel, String> pathColumn;

    @FXML
    private TableColumn<CaptchaModel, String> contentColumn;

    @FXML
    private TableColumn<CaptchaModel, Date> dateColumn;

    @FXML
    private TableColumn<CaptchaModel, Image> irudiaColumn;

    @FXML
    private Button txertatuBTN;

    @FXML
    private Button gordeBTN;

    @FXML
    void onClick(ActionEvent event) {
        if (event.getSource().equals(txertatuBTN)){
            this.gehituListara();
        }else{
            CaptchaDB.getInstance().gordeDatuBasean(lista);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista = this.getLista();
        this.loadTabla();
    }
    private void gehituListara() {
        //https://github.com/Kerman-Sanjuan/CaptchaAzterketa2021/blob/main/src/main/java/ehu/isad/controllers/ui/ProbaController.java
        File file = null;
        CaptchaModel cp;
        try {
            file = File.createTempFile("captcha", ".png", new File(Utils.getProperties().getProperty("pathToImages")));
            BufferedImage img = null;
            img = ImageIO.read(new URL("http://45.32.169.98/captcha.php"));
            ImageIO.write(img, "png", file);
            if (lista.size() == 0) {
                 cp = new CaptchaModel(1, file.getName(), "",(int) System.currentTimeMillis()/1000);
            }else {
                 cp = new CaptchaModel(lista.get(lista.size()-1).getId()+1, file.getName(), "",(int) System.currentTimeMillis()/1000);
            }
            cp.setCaptcha(new Image(new FileInputStream(Utils.getProperties().getProperty("pathToImages")+file.getName())));
            lista.add(cp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<CaptchaModel>getLista(){
        return CaptchaDB.getInstance().getFromDB();
    }
    public void loadTabla(){
        tabla.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        irudiaColumn.setCellValueFactory(new PropertyValueFactory<>("captcha"));
        //Como hacer que las columnas sean editables
        //Esto en caso de text field.
        contentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //Asi es como se guarda en el objeto.
                contentColumn.setOnEditCommit((TableColumn.CellEditEvent<CaptchaModel, String> event) -> {
                   TablePosition<CaptchaModel, String> pos = event.getTablePosition();
                   int row = pos.getRow();
                   CaptchaModel captchaModel = event.getTableView().getItems().get(row);
                   String content = event.getNewValue();
                 captchaModel.setValue(content);
               });
        //Si es una foto, asi se carga el display
        irudiaColumn.setCellValueFactory(new PropertyValueFactory<>("captcha"));
        irudiaColumn.setCellFactory(p -> new TableCell<>() {
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
        tabla.setItems(lista);
    }
}
