package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import se.lexicon.lars.CannonFodder;

import java.util.ArrayList;

public class Cannon {

    Image cannonImage;
    private ArrayList<String> input = new ArrayList<String>();
    private double positionX;
    private double positionY;
    private double imageWidth;
    private double imageHeight;
    private double speed;
    private double windowWidth = CannonFodder.windowWidth;
    private double windowHeight = CannonFodder.windowHeight;


    public Cannon() {
        setCannonImage();
        positionX = (windowWidth / 2);
        positionY = windowHeight - imageHeight - 50;
        speed = 5;

    }

    public Cannon(double positionX, double positionY, double speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        setCannonImage();

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
            if (positionX + speed + imageWidth > windowWidth - 1) {
                positionX = (windowWidth - 1) - imageWidth;
            } else {
                positionX = positionX + speed;
            }
        }
        if (input.contains("LEFT")) {
            if (positionX - speed < 0) {
                positionX = 0;
            } else {
                positionX = positionX - speed;
            }
        }
    }


    private void setCannonImage() {
        cannonImage = new Image("file:Images/cannon2.png", 50, 50, false, false);
        imageWidth = cannonImage.getWidth();
        imageHeight = cannonImage.getHeight();
        System.out.println(imageWidth + " " + imageHeight);
    }

    public void renderCannon(GraphicsContext gc, Scene scene) {
        moveCannon(scene);
        gc.drawImage(cannonImage, getPositionX(), getPositionY());
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
