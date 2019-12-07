package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Cannon {

    private static int cannonId = 0;

    private Image cannonImage;
    private ArrayList<String> input = new ArrayList<>();
    private double positionX;
    private double positionY;
    private double imageWidth;
    private double imageHeight;
    private double cannonSpeed;
    private double windowWidth = Graphics.windowWidth;
    private double windowHeight = Graphics.windowHeight;
    private double cannonBallX;
    private double cannonBallY;
    private double cannonBallSpeed;
    private boolean cannonBall = false;
    private double cannonBallWidth;
    private double cannonBallHeight;


    public Cannon() {
        setCannonImage();
        setPositionX((windowWidth / 2));
        setPositionY((windowHeight - 1) - imageHeight - 49);
        setCannonSpeed(5);
        cannonId++;

    }

    public Cannon(double cannonSpeed) {
        setCannonImage();
        setPositionX((windowWidth / 2));
        setPositionY((windowHeight - 1) - imageHeight - 49);
        setCannonSpeed(cannonSpeed);
        cannonId++;
    }

    public Rectangle2D getBoundaryOfCannon() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    public Rectangle2D getBoundaryOfCannonBall() {
        return new Rectangle2D(getCannonBallX(), getCannonBallY(), getCannonBallWidth(), getCannonBallHeight());
    }

    private void getPlayerInput(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

    }

    private void moveCannon(Scene scene, GraphicsContext gc) {
        getPlayerInput(scene);
        if (input.contains("RIGHT")) {
            if (positionX + cannonSpeed + imageWidth > windowWidth - 1) {
                positionX = (windowWidth - 1) - imageWidth;
            } else {
                positionX = positionX + cannonSpeed;
            }
        }
        if (input.contains("LEFT")) {
            if (positionX - cannonSpeed < 0) {
                positionX = 0;
            } else {
                positionX = positionX - cannonSpeed;
            }
        }
        if (input.contains("SPACE") && !isCannonBall()) {
            setCannonBallCircle(gc);
            System.out.println("Firing on target!");
            setCannonBall(true);

        }
    }

    private void moveCannonBall() {
        if (getCannonBallY() < 0) {
            setCannonBall(false);
        } else {
            setCannonBallY(getCannonBallY() - getCannonBallSpeed());
        }
    }

    private void setCannonImage() {
        cannonImage = new Image("file:Images/cannon2.png", 50, 50, false, false);
        imageWidth = cannonImage.getWidth();
        imageHeight = cannonImage.getHeight();
    }

    public void renderCannon(GraphicsContext gc, Scene scene) {
        moveCannon(scene, gc);
        gc.drawImage(cannonImage, getPositionX(), getPositionY());
    }

    public void renderCannonBall(GraphicsContext gc) {
        moveCannonBall();
        gc.setFill(Color.RED);
        gc.fillOval(getCannonBallX(), getCannonBallY(), getCannonBallWidth(), getCannonBallHeight());
    }

    public void setCannonBallCircle(GraphicsContext gc) {
        setCannonBallX(getPositionX() + (imageWidth / 2) - 7);
        setCannonBallY(getPositionY());
        setCannonBallWidth(10);
        setCannonBallHeight(10);
        setCannonBallSpeed(3);
        gc.setFill(Color.RED);
        gc.fillOval(getCannonBallX(), getCannonBallY(), getCannonBallWidth(), getCannonBallHeight());

        //group.getChildren().add(circle);
/*        circle.setCenterX(positionX);
        circle.setCenterY(positionY);
        circle.setRadius(20);
        circle.setFill(Color.RED);*/
    }


    public double getCannonSpeed() {
        return cannonSpeed;
    }

    public void setCannonSpeed(double cannonSpeed) {
        this.cannonSpeed = cannonSpeed;
    }

    public double getCannonBallWidth() {
        return cannonBallWidth;
    }

    public void setCannonBallWidth(double cannonBallWidth) {
        this.cannonBallWidth = cannonBallWidth;
    }

    public double getCannonBallHeight() {
        return cannonBallHeight;
    }

    public void setCannonBallHeight(double cannonBallHeight) {
        this.cannonBallHeight = cannonBallHeight;
    }

    public boolean isCannonBall() {
        return cannonBall;
    }

    public void setCannonBall(boolean cannonBall) {
        this.cannonBall = cannonBall;
    }

    public double getCannonBallX() {
        return cannonBallX;
    }

    public void setCannonBallX(double cannonBallX) {
        this.cannonBallX = cannonBallX;
    }

    public double getCannonBallY() {
        return cannonBallY;
    }

    public void setCannonBallY(double cannonBallY) {
        this.cannonBallY = cannonBallY;
    }

    public double getCannonBallSpeed() {
        return cannonBallSpeed;
    }

    public void setCannonBallSpeed(double cannonBallSpeed) {
        this.cannonBallSpeed = cannonBallSpeed;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(double imageWidth) {
        this.imageWidth = imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(double imageHeight) {
        this.imageHeight = imageHeight;
    }
}
