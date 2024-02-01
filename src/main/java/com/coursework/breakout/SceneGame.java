package com.coursework.breakout;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneGame implements ConstantsBreakout {
    Scene scene;
    private final Pane pane = new Pane();
    private Background background = null;
    HashMap<Integer, String> backgrounds = new HashMap<>();

    public SceneGame() {
        backgrounds.put(1, "file:assets/background_level01.jpg");
        backgrounds.put(2, "file:assets/background_level01.jpg");
        backgrounds.put(3, "file:assets/background_level01.jpg");
    }

    public void setTheStage(Stage stage) {
        scene = new Scene(pane, APPLICATION_WIDTH, APPLICATION_HEIGHT);
        stage.setTitle("Breakout Game");
        Image iconGame = new Image(FILE_GAME_ICON);
        stage.getIcons().add(iconGame);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void setBackground(String fileUrl) {
        pane.getChildren().remove(background);
        Image image = new Image(fileUrl);
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImage);
        pane.setBackground(background);
    }

    public String getBackgroundFile(Integer levelNumber) {
        return backgrounds.get(levelNumber);
    }

    public void addElement(Node element) {
        pane.getChildren().add(element);
    }

    public  void removeElement(Node element) {
        pane.getChildren().remove(element);
    }

    public Scene getScene() {
        return scene;
    }
}
