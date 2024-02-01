package com.coursework.breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle implements ConstantsBreakout{
    public Rectangle createPaddle() {
        Rectangle paddle = new Rectangle(PADDLE_WIDTH, PADDLE_HEIGHT, Color.BLACK);
        paddle.setX((APPLICATION_WIDTH - PADDLE_WIDTH) / 2);
        paddle.setY(PADDLE_Y_OFFSET);
        return paddle;
    }
    java.awt.Color color1 = new java.awt.Color(0x323843);
}
