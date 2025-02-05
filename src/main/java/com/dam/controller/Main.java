package com.dam.controller;

import com.dam.model.BatchCommand;
import com.dam.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

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

    private List<VBox> batchItems = new ArrayList<>();

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

    public void removeBatch(VBox vbox) {
        batchList.getChildren().remove(vbox);
        batchItems.remove(vbox);
        System.out.println("Elemento eliminado de batchList y batchItems.");
    }

    void deleteBatch(File file, Button btnDelete){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Seguro que deseas eliminar este archivo?");
        alert.setContentText(file.getAbsolutePath());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (file.delete()) {

                Node node = btnDelete.getParent();
                if (node != null) {
                    batchList.getChildren().remove(node.getParent());
                }

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText("Ha ocurrido un error");
                alert2.show();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText("Ha ocurrido un error");
            alert1.show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainModel.getBatchList().addListener((ListChangeListener<? super Parent>) (change) ->{
            batchList.getChildren().setAll(MainModel.getBatchList());
        });
    }
}
