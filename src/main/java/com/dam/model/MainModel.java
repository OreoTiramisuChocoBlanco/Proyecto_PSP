package com.dam.model;

import javafx.stage.Stage;

public class MainModel {
    private static Stage mainStage;

    public static void closeApp(){
        mainStage.close();
    }
}
