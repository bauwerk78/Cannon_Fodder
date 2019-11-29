package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import se.lexicon.lars.CannonFodder;

import java.util.ArrayList;



public class Cannon {

    private Image cannonImage;
    private Circle circle;
    private ArrayList<String> input = new ArrayList<String>();
    private double positionX;
    private double positionY;
    private double imageWidth;
    private double imageHeight;
    private double cannonSpeed;
    private double cannonBallSpeed;
    private double windowWidth = CannonFodder.windowWidth;
    private double windowHeight = CannonFodder.windowHeight;
    private double cannonBallX;
    private double cannonBallY;
    private boolean cannonBall = false;
    private double cannonBallWidth;
    private double cannonBallHeight;

    public Cannon() {
        setCannonImage();
        //setCannonBall();
        positionX = (windowWidth / 2);
        positionY = windowHeight - imageHeight - 50;
        cannonSpeed = 5;

    }

    public Cannon(double positionX, double positionY, double cannonSpeed) {
        setCannonImage();
        this.positionX = positionX;
        this.positionY = positionY;
        this.cannonSpeed = cannonSpeed;

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

    private void moveCannon(Group group, Scene scene, GraphicsContext gc) {
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
        if(input.contains("SPACE") && !isCannonBall()) {
            setCannonBallCircle(group, gc);
            System.out.println("Firing on target!");
            setCannonBall(true);

        }
    }

    private void moveCannonBall(GraphicsContext gc) {
        //circle.setCenterY(getCannonBallY() - getCannonBallSpeed());
        if(getCannonBallY() < 0) {
            setCannonBall(false);
        } else {
            setCannonBallY(getCannonBallY() - getCannonBallSpeed());
            gc.setFill(Color.RED);
            gc.fillOval(getCannonBallX(), getCannonBallY(), getCannonBallWidth(), getCannonBallHeight());
        }
    }

    private void setCannonImage() {
        cannonImage = new Image("file:Images/cannon2.png", 50, 50, false, false);
        imageWidth = cannonImage.getWidth();
        imageHeight = cannonImage.getHeight();
    }

    public void renderCannon(GraphicsContext gc, Scene scene, Group group) {
        moveCannon(group, scene, gc);
        gc.drawImage(cannonImage, getPositionX(), getPositionY());
    }

    public void renderCannonBall(GraphicsContext gc) {
        //setCannonBall();
        //gc.fillOval();
        moveCannonBall(gc);
    }

    public void setCannonBallCircle(Group group, GraphicsContext gc) {
        setCannonBallX(getPositionX() + (imageWidth / 2) - 7);
        setCannonBallY(getPositionY());
        setCannonBallWidth(10);
        setCannonBallHeight(10);
        //circle = new Circle(getCannonBallX() + (imageWidth / 2) - 2, getCannonBallY(), 4, Color.RED);
        setCannonBallSpeed(3);
        gc.setFill(Color.RED);
        gc.fillOval(getCannonBallX(), getCannonBallY(), getCannonBallWidth(), getCannonBallHeight());
        //group.getChildren().add(circle);
/*        circle.setCenterX(positionX);
        circle.setCenterY(positionY);
        circle.setRadius(20);
        circle.setFill(Color.RED);*/
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
