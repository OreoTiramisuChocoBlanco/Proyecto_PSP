package com.dam;

import com.dam.controller.Main;
import com.dam.model.MainModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/FXML/main.fxml") );
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("test");
        primaryStage.show();
        Main controlador = loader.getController();
        MainModel.setMainStage(primaryStage);
    }
}