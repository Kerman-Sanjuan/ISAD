package ehu.isad;

import ehu.isad.controllers.ui.ProbaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent root;
  private Stage stage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    loadUI(primaryStage);
    stageSetup();
    stage.show();
  }

  private void loadUI(Stage primaryStage) throws IOException {
    stage = primaryStage;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/proba.fxml"));
    ProbaController mainController = ProbaController.getInstance();
    loader.setController(mainController);
    root = loader.load();
  }

  private void stageSetup(){
    stage.setScene(new Scene(root));
    stage.setTitle("Azterketa");
    stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    stage.setResizable(false);
  }


  public static void main(String[] args) {
    launch(args);
  }
}
