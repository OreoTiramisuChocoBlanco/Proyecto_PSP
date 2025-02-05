package com.dam.controller;

import com.dam.model.BatchCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;

public class MenuItem {

    private File file;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnRun;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelTitle;

    @FXML
    void onDelete(ActionEvent event) {

    }

    @FXML
    void onRemove(ActionEvent event) {

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
