package com.dam.controller;

import com.dam.model.BatchCommand;
import com.dam.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Main {

    @FXML
    private VBox batchList;

    @FXML
    private MenuItem btnImport;

    @FXML
    private MenuItem btnNew;

    @FXML
    private MenuItem btnOpen;

    @FXML
    private MenuItem btnQuit;

    @FXML
    private MenuItem btnSave;

    @FXML
    void openFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Abrir");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo .bat", "*.bat"));
        fc.setInitialDirectory( new File( System.getProperty("user.home") ) );

        File file = fc.showOpenDialog( MainModel.getMainStage() );

        if (file != null && file.exists()) {
            addBatch(file);
        }
    }

    @FXML
    void openImport(ActionEvent event) {

    }

    @FXML
    void openNew(ActionEvent event) {

    }

    @FXML
    void openSave(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {
        MainModel.closeApp();
    }

    void addBatch(File file){
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/FXML/menu_item.fxml") );
        try {
            Parent root = loader.load();
            batchList.getChildren().add(root);
            com.dam.controller.MenuItem controller = (com.dam.controller.MenuItem) loader.getController();
            controller.setup(file);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error añadiendo el archivo");
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

}
