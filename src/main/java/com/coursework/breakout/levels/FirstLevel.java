package com.coursework.breakout.levels;

import com.coursework.breakout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class FirstLevel extends Level {
    public FirstLevel(SceneGame sceneGame) {
        super(sceneGame);
    }

    @Override
    public Rectangle addPaddle() {
        return super.addPaddle();
    }

    @Override
    public Circle addBall() {
        return super.addBall();
    }
    @Override
    public List<Rectangle> addBricks() {
        return super.addBricks();
    }

    @Override
    public List<Rectangle> addObstacles() {
       return new ArrayList<>();
    }
}
