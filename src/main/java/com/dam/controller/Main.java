package com.dam.controller;

import com.dam.model.MainModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Main implements Initializable {

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
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo .txt", "*.txt"));
        fc.setInitialDirectory( new File( System.getProperty("user.home") ) );

        File file = fc.showOpenDialog( MainModel.getMainStage() );
        if (file != null && file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    File f = new File(line);
                    if (f.exists()) {
                        addBatch(f);
                    }
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void openImport(ActionEvent event) {
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
    void openNew(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory( new File(System.getProperty("user.home")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo .bat", "*.bat"));
        File file = fc.showSaveDialog(batchList.getScene().getWindow());
        if (file != null) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    ProcessBuilder pb = new ProcessBuilder("notepad.exe", file.getAbsolutePath());
                    pb.start();
                    addBatch(file);
                } catch (IOException e) {
                    System.err.println("ERROR: Error al crear el archivo: "+e.getMessage());
                }
            }
        }
    }

    @FXML
    void openSave(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory( new File(System.getProperty("user.home")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fc.showSaveDialog(batchList.getScene().getWindow());
        if (file!=null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < MainModel.fileList.size(); i++) {
                    File f = MainModel.fileList.get(i);
                    System.out.println(f.getAbsolutePath());
                    writer.write(f.getAbsolutePath()+"\n");
                }
            } catch (IOException e) {
                System.err.println("Error abriendo el archivo: "+e.getMessage());
            }
        }
    }

    @FXML
    void quit(ActionEvent event) {
        MainModel.closeApp();
    }

    void addBatch(File file){
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/FXML/menu_item.fxml") );
        try {
            Parent root = loader.load();
            MainModel.addItem(root);
            MainModel.fileList.add(file);
            com.dam.controller.MenuItem controller = (com.dam.controller.MenuItem) loader.getController();
            controller.setup(file);
            controller.setParentItem(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error añadiendo el archivo");
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    static void removeBatch(Parent item) {
        MainModel.removeItem(item);
        System.out.println("Elemento eliminado de batchList y batchItems.");
    }

    public static void deleteBatch(File file, Button btnDelete){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Seguro que deseas eliminar este archivo?");
        alert.setContentText(file.getAbsolutePath());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (file.delete()) {

                Node node = btnDelete.getParent();
                if (node != null) {
                    removeBatch(node.getParent());
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
