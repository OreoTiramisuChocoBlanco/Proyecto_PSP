package com.dam.model;

import javafx.stage.Stage;

public class MainModel {
    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        MainModel.mainStage = mainStage;
    }

    private static Stage mainStage;

    public static void closeApp(){
        mainStage.close();
    }


}
