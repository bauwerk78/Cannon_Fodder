package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import se.lexicon.lars.CannonFodder;

import java.util.Random;

public class Fodder {
    private static Random rand = new Random();

    private Image fodderImage;
    private double positionX;
    private double positionY;
    private double fodderSpeed;
    private double imageWidth;
    private double imageHeight;

    public Fodder() {
        setFodderImage();
        setPositionX(randomXPosition(CannonFodder.windowWidth));
        setPositionY(0 - getImageHeight() * 2);
        setFodderSpeed(1);

    }

    private Rectangle2D getBoundary() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    //If fodder collides with cannonball.
    public boolean collisionDetection(Cannon cannonBall){
        return getBoundary().intersects(cannonBall.getCannonBallX(), cannonBall.getCannonBallY(), cannonBall.getCannonBallWidth(), cannonBall.getCannonBallHeight());
    }

    private int randomXPosition(double windowWidth) {
        int windowMax = ((int) windowWidth - (int) imageWidth) - 1;
        return rand.nextInt(windowMax);
    }

    private void setFodderImage() {
        fodderImage = new Image("file:Images/rambo1.png", 50, 50, false, false);
        setImageWidth(fodderImage.getWidth());
        setImageHeight(fodderImage.getHeight());
    }

    private void moveFodder() {
        setPositionY(getPositionY() + getFodderSpeed());
    }

    public void renderFodder(GraphicsContext gc) {
        moveFodder();
        gc.drawImage(fodderImage, getPositionX(), getPositionY());
    }

    public double getFodderSpeed() {
        return fodderSpeed;
    }

    public void setFodderSpeed(double fodderSpeed) {
        this.fodderSpeed = fodderSpeed;
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
