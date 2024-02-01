package com.coursework.breakout.levels;

import com.coursework.breakout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class Level implements ConstantsBreakout{
    SceneGame sceneGame;
    Ball ballObject = new Ball();
    Paddle paddleObject = new Paddle();

    public Level(SceneGame sceneGame) {
        this.sceneGame = sceneGame;
    }

    public Rectangle addPaddle() {
        Rectangle paddle = paddleObject.createPaddle();
        sceneGame.addElement(paddle);
        return paddle;
    }

    public Circle addBall() {
        Circle ball = ballObject.createBall();
        sceneGame.addElement(ball);
        return ball;
    }

    public List<Rectangle> addBricks() {
        List<Rectangle> bricksList = new ArrayList<>();
        for (int i = 0; i < NUM_BRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                Rectangle brick = new Brick().createBrick(i, j);
                bricksList.add(brick);
                sceneGame.addElement(brick);
            }
        }
        return bricksList;
    }

    public abstract List<Rectangle> addObstacles();
}
