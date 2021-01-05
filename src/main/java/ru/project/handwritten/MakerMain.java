package ru.project.handwritten;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MakerMain extends Application {
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/makerVIEW.fxml"));

        primaryStage.setTitle("HAND WRITTEN TEXT MAKER");
        Scene scene = new Scene(root,603,365);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/logo.PNG"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
