package com.coursework.breakout.info;

import com.coursework.breakout.SceneGame;

public class InfoAuthor extends Info {
    SceneGame sceneGame;

    public InfoAuthor(SceneGame sceneGame) {
        super(sceneGame);
        this.sceneGame = sceneGame;
    }

    public void message() {
        super.message();
        sceneGame.setBackground(FILE_ABOUT_AUTHOR);
    }
}

