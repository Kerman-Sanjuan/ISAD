package ehu.isad.controllers;


import ehu.isad.Book;
import ehu.isad.Liburuak;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak liburua;
    public void setMainApp(Liburuak main) {
        this.liburua = main;
    }

    @FXML
    void onClick(ActionEvent event) throws IOException {
        liburua.xehetasunakErakutsi((Book)liburuakCombo.getValue());
    }
    @FXML
    private ComboBox liburuakCombo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> items = Liburuak.izenburuak();
        liburuakCombo.setItems(items);
    }
}

