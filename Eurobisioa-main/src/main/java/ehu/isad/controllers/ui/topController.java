package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.topDB;
import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class topController implements Initializable {
    private Main asd;
    public void setMainApp(Main main) {
        this.asd = main;
    }
    @FXML
    private Button button;

    @FXML
    private ImageView image;

    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    @FXML
    void onClick(ActionEvent event) {
        asd.setHautatUI();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("/images/europision.png"));
    }


    public void set() throws SQLException {
       List<Ordezkaritza> top = topDB.getInstantzia().top3();
       lb1.setText(top.get(0).getHerrialdea()+"----"+ top.get(0).getPuntuak() );
       lb2.setText(top.get(1).getHerrialdea()+"----"+ top.get(1).getPuntuak() );
       lb3.setText(top.get(2).getHerrialdea()+"----"+ top.get(2).getPuntuak() );
    }
    //TODO: Put the flags on the top3, make the table editable.
}