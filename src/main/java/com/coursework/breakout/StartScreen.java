package com.coursework.breakout;

import com.coursework.breakout.info.Info;
import com.coursework.breakout.info.InfoAuthor;
import com.coursework.breakout.info.InfoGame;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class StartScreen implements ConstantsBreakout {
    private final SceneGame sceneGame;

    public StartScreen(SceneGame sceneGame) {
        this.sceneGame = sceneGame;
    }

    public void addStartScreen() {
        sceneGame.setBackground(FILE_BACKGROUND_START);
        VBox buttonsLayout = createButtonsLayout();

        Button startButton = new Button("Розпочати гру");
        setButtonStyle(startButton);
        Button aboutGameButton = new Button("Про гру");
        setButtonStyle(aboutGameButton);
        Button aboutAuthorButton = new Button("Про автора");
        setButtonStyle(aboutAuthorButton);
        Button exitButton = new Button("Вийти");
        setButtonStyle(exitButton);

        buttonsLayout.getChildren().addAll(startButton, aboutGameButton, aboutAuthorButton, exitButton);
        sceneGame.addElement(buttonsLayout);
        handleButtonPress(buttonsLayout, startButton, aboutGameButton, aboutAuthorButton, exitButton);
    }

    private VBox createButtonsLayout() {
        int spacing = 20;
        VBox buttonsLayout = new VBox(spacing); // Вертикальний контейнер для розташування кнопок
        buttonsLayout.setAlignment(Pos.CENTER); // Вирівнювання елементів по центру
        buttonsLayout.setLayoutX((APPLICATION_WIDTH - BUTTON_WIDTH) / 2);
        buttonsLayout.setLayoutY((APPLICATION_HEIGHT - BUTTON_HEIGHT) / 2);
        return buttonsLayout;
    }

    public static void setButtonStyle(Button button) {
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        String boldFontStyle = "-fx-font-weight: bold;";
        String darkColor = "-fx-base: #333333;";
        String largeFontSize = "-fx-font-size: 16pt;";

        button.setStyle(boldFontStyle + darkColor + largeFontSize);
    }

    public void handleButtonPress(VBox buttonsLayout, Button startButton, Button aboutGameButton, Button aboutAuthorButton, Button exitButton) {
        startButton.setOnAction(e -> {
            sceneGame.removeElement(buttonsLayout);
            RunGame runGame = new RunGame(sceneGame);
            runGame.move();
        });
        aboutGameButton.setOnAction(e -> {
            sceneGame.removeElement(buttonsLayout);
            Info aboutGame = new InfoGame(sceneGame);
            aboutGame.message();
        });

        aboutAuthorButton.setOnAction(e -> {
            sceneGame.removeElement(buttonsLayout);
            Info aboutAuthor = new InfoAuthor(sceneGame);
            aboutAuthor.message();
        });

        exitButton.setOnAction(StartScreen::exitGame);
    }
    private static void exitGame(ActionEvent e) {
        System.exit(0);
    }
}
