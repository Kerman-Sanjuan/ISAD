package ehu.isad.controllers;


import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud {

    @FXML
    private Label orID, izID, arID;
    @FXML
    private ImageView img;

    private Liburuak liburua;

    public void setMainApp(Liburuak main) {
        this.liburua = main;
    }

    @FXML
    void onClick(ActionEvent event) {
        liburua.mainErakutsi();
    }

    public  void data(Book b) throws IOException {
        izID.setText(b.getDetails().getTitle());
        orID.setText(b.getDetails().getNumber_of_pages().toString());

        //Gerta daiteke publisher bat baino gehiago egotea.
        String idazleak = "";
        for (int i=0; i < b.getDetails().getPublishers().length; i++){
            idazleak = idazleak  +  b.getDetails().getPublishers()[i];
        }
        arID.setText(idazleak);
        img.setImage(Sarea.createImage(b.getThumbnail_url()));
    }
}

