package com.coursework.breakout;

import com.coursework.breakout.levels.FirstLevel;
import com.coursework.breakout.levels.Level;
import com.coursework.breakout.levels.SecondLevel;
import com.coursework.breakout.levels.ThirdLevel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.*;

public class RunGame implements ConstantsBreakout {
    private final SceneGame sceneGame;
    private MediaPlayer mediaPlayer = null;
    private Circle ball;
    private Rectangle paddle;
    private List<Rectangle> bricksList;
    private List<Rectangle> obstacles;
    private final HashMap<Integer, Level> levels = new HashMap<>();
    Label scoreMessage = null;
    private int score = 0;
    Label levelMessage = null;
    private int levelNumber = 1;
    Label headPointMessage = null;
    List<Circle> lives = new ArrayList<>();
    private int headPoint = 3;
    String boldFontStyle = "-fx-font-weight: bold;";
    String fontSize = "-fx-font-size: 16pt;";
    private double vx, vy;

    public RunGame(SceneGame sceneGame) {
        this.sceneGame = sceneGame;
        levels.put(1, new FirstLevel(sceneGame));
        levels.put(2, new SecondLevel(sceneGame));
        levels.put(3, new ThirdLevel(sceneGame));
    }

    public void move() {
        createLevel();
        movePaddle();
        moveBall();
    }

    public void createLevel() {
        Level level = getLevel(levelNumber);
        String backgroundFile = sceneGame.getBackgroundFile(levelNumber);
        sceneGame.setBackground(backgroundFile);
        paddle = level.addPaddle();
        ball = level.addBall();
        bricksList = level.addBricks();
        obstacles = level.addObstacles();
    }

    Level getLevel(Integer levelNumber) {
        return levels.get(levelNumber);
    }

     public void movePaddle() {
        sceneGame.getScene().setOnMouseMoved(event -> {
            double newX = event.getX() - paddle.getWidth() / 2.0;
            if (paddleNotTouchesTheWall(newX)) {
                paddle.setX(newX);
            }
        });
    }

    private boolean paddleNotTouchesTheWall(double x) {
        if (x < 0) return false;
        return (!(x >= (APPLICATION_WIDTH - PADDLE_WIDTH)));
    }

    public void moveBall() {
        vx = getRandomVx();
        vy = getRandomV();

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private double getRandomVx() {
        Random random = new Random();
        double velocity = random.nextDouble() + 2;
        if (random.nextBoolean()) {
            velocity = -velocity;
        }
        return velocity;
    }

    private double getRandomV() {
        Random random = new Random();
        return random.nextDouble() + 2;
    }

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
        showLabels();
        touchWithWalls();
        touchPaddle();
        touchObstacle();
        touchBrick();
        remainBricks();

        ball.setTranslateX(ball.getTranslateX() + vx);
        ball.setTranslateY(ball.getTranslateY() + vy);
    }));

    private void showLabels() {
        showScore();
        showLevel();
        showHeadPoints();
    }

    private void showScore() {
        sceneGame.removeElement(scoreMessage);
        scoreMessage = new Label("Score: " + score);
        appendToScreen(scoreMessage,LABEL_OFFSET_X1);
    }

    private void showLevel() {
        sceneGame.removeElement(levelMessage);
        levelMessage = new Label("LEVEL " + levelNumber);
        appendToScreen(levelMessage, LABEL_OFFSET_X2);

    }

    private void showHeadPoints() {
        sceneGame.removeElement(headPointMessage);
        headPointMessage = new Label("Lives: ");
        addHeadPoint();
        appendToScreen(headPointMessage,LABEL_OFFSET_X3);
    }

    private void appendToScreen(Label message, double x) {
        message.setLayoutX(x);
        message.setLayoutY(LABEL_OFFSET_Y);
        message.setStyle(boldFontStyle + fontSize);
        sceneGame.addElement(message);
    }

    private void addHeadPoint() {
        removeLives();

        for (int i = 0; i < headPoint; i++) {
            lives.add(new Circle(LIVE_RADIUS, Color.RED));
            lives.get(i).setCenterX(LIVE_OFFSET_X + (i * lives.get(i).getRadius() * 3));
            lives.get(i).setCenterY(LIVE_OFFSET_Y);
            sceneGame.addElement(lives.get(i));
        }
    }

    private void removeLives() {
        for (Circle live : lives) {
            sceneGame.removeElement(live);
        }
    }

    public void touchWithWalls() {
        if (ballTouchesTopWall()) {
            playSound(SOUND_WALL);
            vy = -vy;
        }
        if (ballTouchesSideWall()) {
            playSound(SOUND_WALL);
            vx = -vx;
        }
        if (ballTouchesBottomWall()) {
            playSound(SOUND_FLOOR);
            headPoint--;

            if (headPoint == 0) {
                sceneGame.setBackground(FILE_BACKGROUND_START);
                removeEverything();
                messageGame("The End!\nYou LOST!!!\n TOTAL SCORE: " + score, Color.RED);
            } else {
                sceneGame.removeElement(ball);
                ball = levels.get(levelNumber).addBall();
                vx = getRandomVx();
                vy = getRandomV();
            }
        }
    }

    private boolean ballTouchesTopWall() {
        return ball.getCenterY() + ball.getTranslateY() - BALL_RADIUS + vy <= 0;
    }

    private boolean ballTouchesSideWall() {
        if (ball.getCenterX() + ball.getTranslateX() - BALL_RADIUS + vx <= 0) return true;
        return ball.getCenterX() + ball.getTranslateX() + BALL_RADIUS + vx >= APPLICATION_WIDTH;
    }

    private boolean ballTouchesBottomWall() {
        return ball.getCenterY() + ball.getTranslateY() + BALL_RADIUS + vy >= APPLICATION_HEIGHT;
    }

    private void playSound(Media soundFile) {
        if (mediaPlayer != null) mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(soundFile);
        mediaPlayer.play();
    }

    public void touchPaddle() {
        double ballCenterX = ball.getBoundsInParent().getCenterX();
        if (ball.getBoundsInParent().intersects(paddle.getBoundsInParent())) {
            playSound(SOUND_PADDLE);

            double section = (ballCenterX - paddle.getBoundsInParent().getMinX()) / (paddle.getBoundsInParent().getWidth());
            if (section < 0.33) {
                vx = -Math.abs(vx);
                vx *= 1.1;
            } else if (section < 0.67) {
                vx /= 1.2;
                vy *= 1.1;
            } else {
                vx = Math.abs(vx);
                vx *= 1.1;
            }
            vy = -vy;
        }
    }

    private void touchObstacle() {
        for (Rectangle rectangle : obstacles) {
            Point2D[] points = calculateIntersectionPoint(rectangle);
            if (points != null) {
                playSound(SOUND_WALL);
                handleCollision(points, rectangle);
            }
        }
    }

    public void touchBrick() {
        Iterator<Rectangle> iterator = bricksList.iterator();
        while (iterator.hasNext()) {
            Rectangle brick = iterator.next();

            if(ball.getBoundsInParent().intersects(brick.getBoundsInParent())) {
                Point2D[] points = calculateIntersectionPoint(brick);

                if (points != null) {
                    playSound(SOUND_BRICK);
                    handleCollision(points, brick);
                    score += POINTS_FOR_BRICKS;
                    sceneGame.removeElement(brick);
                    iterator.remove();
                }
            }
        }

    }

    private Point2D[] calculateIntersectionPoint(Shape object) {
        Point2D[] twoPoints = new Point2D[2];
        Shape intersection = Shape.intersect(ball, object);

        if (!intersection.getBoundsInLocal().isEmpty()) {
            double x = intersection.getBoundsInLocal().getMinX();
            double y = intersection.getBoundsInLocal().getMinY();
            twoPoints[0] = new Point2D(x,y);
            x = intersection.getBoundsInLocal().getMaxX();
            y = intersection.getBoundsInLocal().getMaxY();
            twoPoints[1] = new Point2D(x,y);

            return twoPoints;
        }
        return null;
    }

    private void handleCollision(Point2D[] collisionPoints, Shape object) {
        double minPointCollisionY = collisionPoints[0].getY();
        double maxPointCollisionY = collisionPoints[1].getY();

        double brickBottomY = object.getBoundsInParent().getMaxY();
        double brickTopY = object.getBoundsInParent().getMinY();

        if ((minPointCollisionY >= brickBottomY + vy) || (maxPointCollisionY <= brickTopY + vy)) {
            vy = -vy;
        } else {
            vx = -vx;
        }
    }

    private void remainBricks() {
        if (bricksList.isEmpty()) {

            if (levelNumber < LEVELS) {
                stopAndRemoveObjects();
                levelNumber++;
                Button continueButton = new Button("Next Level");
                continueButton.setLayoutX((APPLICATION_WIDTH - BUTTON_WIDTH) / 2);
                continueButton.setLayoutY((APPLICATION_HEIGHT - BUTTON_HEIGHT) / 2);
                StartScreen.setButtonStyle(continueButton);
                sceneGame.addElement(continueButton);

                continueButton.setOnAction(e ->  {
                    sceneGame.removeElement(continueButton);
                    move();
                });
            } else {
                sceneGame.setBackground(FILE_BACKGROUND_START);
                removeEverything();
                messageGame("Congratulations!\nYou WINNER!!!\n TOTAL SCORE: " + score, Color.GREEN);
            }
        }
    }

    private void removeEverything() {
        stopAndRemoveObjects();
        sceneGame.removeElement(scoreMessage);
        sceneGame.removeElement(levelMessage);
        sceneGame.removeElement(headPointMessage);
        removeLives();
    }

    private void stopAndRemoveObjects() {
        mediaPlayer.stop();
        timeline.stop();
        sceneGame.removeElement(ball);
        sceneGame.removeElement(paddle);
        for(Rectangle brick : bricksList) {
            sceneGame.removeElement(brick);
        }
        for(Rectangle obstacle : obstacles) {
            sceneGame.removeElement(obstacle);
        }
    }

    private void messageGame(String textMessage, Color colorMessage) {
        Text loserMessage = new Text(textMessage);
        loserMessage.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        loserMessage.setFill(colorMessage);
        loserMessage.setTextAlignment(TextAlignment.CENTER);
        loserMessage.setX((APPLICATION_WIDTH - loserMessage.getBoundsInLocal().getWidth()) / 2);
        loserMessage.setY(APPLICATION_HEIGHT / 2);
        sceneGame.addElement(loserMessage);
    }
}
