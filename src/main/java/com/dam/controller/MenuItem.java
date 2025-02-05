package com.dam.controller;

import com.dam.model.BatchCommand;
import com.dam.model.MainModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Optional;

public class MenuItem {

    private File file;

    private Parent parentItem;

    @FXML
    public Button btnDelete;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnRun;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelTitle;

    @FXML
    void onDelete() {

    }



    @FXML
    void onRemove() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("Eliminar elemento");
        alerta.setContentText("¿Estás seguro de que quieres eliminar este elemento?");

        Optional<ButtonType> resultado = alerta.showAndWait();


        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            System.out.println("Elemento eliminado");
            MainModel.removeItem(parentItem);
        }


    }

    public void setMainController(Main mainController) {
       // this.mainController = mainController;
    }

    public void setParentItem(Parent parentItem) {
        this.parentItem = parentItem;
    }

    @FXML
    void onRun(ActionEvent event) {
        if (this.file != null) {
            BatchCommand bat = new BatchCommand(this.file.getAbsolutePath());
            bat.execute();
        }
    }

    public void setup(File file){
        labelTitle.setText(file.getName());
        labelDescription.setText(file.getAbsolutePath());
        this.file = file;


    }
}
