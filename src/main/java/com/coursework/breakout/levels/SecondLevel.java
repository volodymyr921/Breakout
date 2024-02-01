package com.coursework.breakout.levels;

import com.coursework.breakout.SceneGame;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class SecondLevel  extends Level {
    public SecondLevel(SceneGame sceneGame) {
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
        List<Rectangle> obstacles = new ArrayList<>();
        Rectangle obstacle = createRectangle(150);
        obstacles.add(obstacle);
        sceneGame.addElement(obstacle);
        obstacle = createRectangle(390);
        obstacles.add(obstacle);
        sceneGame.addElement(obstacle);

        return obstacles;
    }

    private Rectangle createRectangle(double x) {
        Rectangle rectangle = new Rectangle(BALL_RADIUS * 4, BALL_RADIUS * 4, Color.MAGENTA);
        rectangle.setX(x);
        rectangle.setY((APPLICATION_HEIGHT / 2) + BALL_RADIUS * 2);
        return rectangle;
    }
}
