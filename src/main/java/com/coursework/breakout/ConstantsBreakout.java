package com.coursework.breakout;

import javafx.scene.media.Media;

import java.io.File;

public interface ConstantsBreakout {
    double APPLICATION_WIDTH = 600;
    double APPLICATION_HEIGHT = 800;

    double PADDLE_WIDTH = 100;
    double PADDLE_HEIGHT = 20;
    double PADDLE_Y_OFFSET = 750;

    int BALL_RADIUS = 15;


    int NBRICKS_PER_ROW = 8;
    int NUM_BRICK_ROWS = 6;
    int BRICK_SEP = 2;

    double BRICK_HEIGHT = 28;
    double BRICK_WIDTH = (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1.0) * BRICK_SEP) / NBRICKS_PER_ROW;
    int BRICK_Y_OFFSET = 70;

    double BUTTON_WIDTH = 200;
    double BUTTON_HEIGHT = 40;
    double BUTTON_OFFSET = 125;
    double LABEL_OFFSET_X1 = 10;
    double LABEL_OFFSET_X2 = 255;
    double LABEL_OFFSET_X3 = 470;
    double LABEL_OFFSET_Y = 10;

    int LEVELS = 3;

    int POINTS_FOR_BRICKS = 50;

    double LIVE_RADIUS = 5;
    double LIVE_OFFSET_X = 540;
    double LIVE_OFFSET_Y = 26;

    String FILE_GAME_ICON = "file:assets/breakout_icon.png";
    String FILE_BACKGROUND_START = "file:assets/background_start.png";
    String FILE_ABOUT_GAME = "file:assets/about_game.png";
    String FILE_ABOUT_AUTHOR = "file:assets/about_author.png";

    Media SOUND_BRICK = new Media(new File("assets/brick_sound.mp3").toURI().toString());
    Media SOUND_PADDLE = new Media(new File("assets/paddle_sound.mp3").toURI().toString());
    Media SOUND_WALL = new Media(new File("assets/wall_sound.mp3").toURI().toString());
    Media SOUND_FLOOR = new Media(new File("assets/floor_sound.mp3").toURI().toString());
}
