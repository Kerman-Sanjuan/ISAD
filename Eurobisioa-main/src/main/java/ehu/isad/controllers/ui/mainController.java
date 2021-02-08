package ehu.isad.controllers.ui;

        import ehu.isad.Main;
        import ehu.isad.utils.Utils;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;

        import java.io.File;
        import java.net.URL;
        import java.util.Properties;
        import java.util.ResourceBundle;

public class mainController implements Initializable {
        private Main asd;
        public void setMainApp(Main main) {
            this.asd = main;
        }
    @FXML
    private Button bozkatuBtn;

    @FXML
    private ImageView euroView;

    @FXML
    void onClick(ActionEvent event) {
        asd.setHautatUI();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Properties properties = Utils.lortuEzarpenak();
        euroView.setImage(new Image("/images/europision.png"));
    }
}