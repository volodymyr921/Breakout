package com.coursework.breakout.info;

import com.coursework.breakout.ConstantsBreakout;
import com.coursework.breakout.SceneGame;
import com.coursework.breakout.StartScreen;
import javafx.scene.control.Button;

public abstract class Info implements ConstantsBreakout {
    SceneGame sceneGame;
    Button backButton;

    public Info(SceneGame sceneGame) {
        this.sceneGame = sceneGame;
        this.backButton = new Button("Повернутися назад");
    }

    public void message() {
        addStyle();
        sceneGame.addElement(backButton);
        handlePressButton();
    }

    private void handlePressButton() {
        backButton.setOnAction(e -> {
            sceneGame.removeElement(backButton);
            StartScreen startScreen = new StartScreen(sceneGame);
            startScreen.addStartScreen();
        });
    }

    private void addStyle() {
        backButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        String boldFontStyle = "-fx-font-weight: bold;";
        String darkColor = "-fx-base: #333333;";
        String largeFontSize = "-fx-font-size: 12pt;"; // Збільшення розміру шрифту

        // Додавання стилів до кожної кнопки
        backButton.setStyle(boldFontStyle + darkColor + largeFontSize);
        backButton.setLayoutX((APPLICATION_WIDTH - BUTTON_WIDTH) / 2);
        backButton.setLayoutY(APPLICATION_HEIGHT - BUTTON_OFFSET);
    }
}
