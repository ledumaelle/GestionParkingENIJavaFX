package fr.eni.gestion_parking_eni_javafx.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe Main
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/gestion_parking.fxml"));
            AnchorPane rootLayout= loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Gestion de Parking ENI");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            //Exception qui intervient si le fichier fxml file n'a pas pu être chargé
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
