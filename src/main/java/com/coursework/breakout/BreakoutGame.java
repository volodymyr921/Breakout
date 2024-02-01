package com.coursework.breakout;


import javafx.application.Application;
import javafx.stage.Stage;


public class BreakoutGame extends Application implements ConstantsBreakout {
    public SceneGame sceneGame = new SceneGame();
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage stage){
        sceneGame.setTheStage(stage);
        StartScreen startGame = new StartScreen(sceneGame);
        startGame.addStartScreen();
    }
}




