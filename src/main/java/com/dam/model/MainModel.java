package com.dam.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        MainModel.mainStage = mainStage;
    }
    public static void addItem(Parent batchItem){
        batchItems.add(batchItem);
    }
    public static void removeItem(Parent batchItem){
        batchItems.remove(batchItem);
    }

    public static ObservableList<Parent> getBatchList(){
        return batchItems;
    }
    private static Stage mainStage;
    private static ObservableList<Parent> batchItems = FXCollections.observableArrayList();


    public static void closeApp(){
        mainStage.close();
    }


}
