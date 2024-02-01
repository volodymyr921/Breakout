package com.coursework.breakout.info;

import com.coursework.breakout.SceneGame;

public class InfoGame extends Info {
    SceneGame sceneGame;

    public InfoGame(SceneGame sceneGame) {
        super(sceneGame);
        this.sceneGame = sceneGame;
    }

    @Override
    public void message() {
        super.message();
        sceneGame.setBackground(FILE_ABOUT_GAME);
    }
}
