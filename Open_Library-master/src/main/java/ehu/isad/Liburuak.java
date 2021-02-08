package ehu.isad;

import ehu.isad.controllers.LiburuKud;
import ehu.isad.controllers.XehetasunakKud;
import ehu.isad.utils.Sarea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.scene.control.Control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Liburuak extends Application {
    private Parent liburuakUI;
    private Parent xehetasunakUI;
    private Stage stage;
    private LiburuKud libKud;
    private XehetasunakKud xeheKud;
    private Scene liburuaS,xehetasunakS;


    public static ObservableList<Book> izenburuak() {

        ObservableList<Book> books = FXCollections.observableArrayList();

        //Liburu bat gehitu bada, hemen bakarrik jartzearekin balioko du.
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
        return books;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Aurreko ariketa adibidea erreferentzia moduan artuta:
        stage = primaryStage;

        pantailakKargatu();

        stage.setTitle("Open Library by Kerman Sanjuan");
        stage.setScene(liburuaS);
        stage.show();
    }
    private void pantailakKargatu() throws IOException {

        //Scenen Loader-ak
        FXMLLoader loaderLiburua = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuakUI = (Parent) loaderLiburua.load();
        libKud = loaderLiburua.getController();
        libKud.setMainApp(this);
        liburuaS = new Scene(liburuakUI);

        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderMain.load();
        xeheKud = loaderMain.getController();
        xeheKud.setMainApp(this);
        xehetasunakS = new Scene(xehetasunakUI);

    }
    public void mainErakutsi() {
        stage.setScene(liburuaS);
        stage.show();
    }
    public void xehetasunakErakutsi(Book liburua) throws IOException {
        Book l = Sarea.readFromUrl(liburua.isbn);
        xeheKud.data(l);
        stage.setScene(xehetasunakS);
        stage.show();
    }


}
