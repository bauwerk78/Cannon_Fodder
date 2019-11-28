package se.lexicon.lars.model;

import javafx.event.EventHandler;
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

    public Cannon() {
        setCannonImage();
        setCannonBall();
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

    private void moveCannon(Scene scene) {
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
        if(input.contains("SPACE")) {
            System.out.println("Firing on target!");
        }
    }


    private void setCannonImage() {
        cannonImage = new Image("file:Images/cannon2.png", 50, 50, false, false);
        imageWidth = cannonImage.getWidth();
        imageHeight = cannonImage.getHeight();
    }

    public void renderCannon(GraphicsContext gc, Scene scene) {
        moveCannon(scene);
        gc.drawImage(cannonImage, getPositionX(), getPositionY());
    }

    public void renderCannonBall(GraphicsContext gc, Scene scene) {
        //setCannonBall();
        //gc.fillOval();




    }

    public void setCannonBall() {
        circle = new Circle();
        circle.setCenterX(positionX);
        circle.setCenterY(positionY);
        circle.setRadius(20);
        circle.setFill(Color.RED);
        cannonBallSpeed = 10;
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
