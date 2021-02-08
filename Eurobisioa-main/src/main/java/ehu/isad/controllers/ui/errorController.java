package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.hautatuDB;
import ehu.isad.model.Herrialdea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


public class errorController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label label;

    @FXML
    private Button btn;

    @FXML
    private ImageView imgFlag;

    @FXML
    void onClick(ActionEvent event) {
        asd.setHautatUI();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void hasieratu(Herrialdea herrialdea){
        System.out.println(herrialdea);
        Image logo = new Image("/images/europision.png");
        Image bandera = new Image("/images/"+herrialdea.toString().toLowerCase()+".png");

        Properties properties = Utils.lortuEzarpenak();
        label.setText(herrialdea.toString() +"Jada banatu ditu bere puntuak");
        img.setImage(logo);
        imgFlag.setImage(bandera);
    }

    private Main asd;

    public void setMainApp(Main main) {
        this.asd = main;
    }

    public void aldatu(Herrialdea herrialdea) throws SQLException {
        System.out.println(hautatuDB.getInstantzia().botoKopurua(herrialdea));
        int dago =hautatuDB.getInstantzia().botoKopurua(herrialdea);
        this.hasieratu(herrialdea);
    }
}
