package com.coursework.breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball implements ConstantsBreakout {

    public Circle createBall() {
        Circle ball = new Circle(BALL_RADIUS, Color.BLACK);
        ball.setCenterX(APPLICATION_WIDTH / 2);
        ball.setCenterY(APPLICATION_HEIGHT / 2);
        return ball;
    }
}
