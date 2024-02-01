package com.coursework.breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Brick implements ConstantsBreakout{
    public Rectangle createBrick(int numberRow, int numberColumn) {
        Rectangle brick = new Rectangle(BRICK_WIDTH,BRICK_HEIGHT, getColorBrick(numberRow));
        brick.setX(numberColumn * (BRICK_WIDTH + BRICK_SEP));
        brick.setY(numberRow * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET);

        return brick;
    }

    private Color getColorBrick(int number) {
        return switch (number) {
            case 0, 1 -> Color.RED;
            case 2, 3 -> Color.GREEN;
            case 4, 5 -> Color.YELLOW;
            default -> Color.BLUE;
        };
    }
}
