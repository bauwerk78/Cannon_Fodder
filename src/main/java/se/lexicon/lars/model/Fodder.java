package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

//import static se.lexicon.lars.model.Graphics.windowWidth;
//import static se.lexicon.lars.model.Graphics.windowHeight;

public class Fodder {

    public static int fodderId = 0;
    //Todo potentially give different fodder hp so it needs more hits so be removed.
    protected static Random rand = new Random();

    protected double windowWidth = Graphics.windowWidth;
    protected double windowHeight = Graphics.windowHeight;
    private Image fodderImage;
    private double positionX;
    private double positionY;
    private double fodderSpeed;
    private double imageWidth;
    private double imageHeight;
    private int thisFodderId;

    public Fodder() {
        setFodderImage("Images/rambo1.png");
        setPositionX(randomXPosition());
        setPositionY(-randomYPosition());
        setFodderSpeed(100);
        setThisFodderId(++fodderId);

    }

    public Fodder(double speed, String imagePath) {
        setFodderImage(imagePath);
        setPositionX(randomXPosition());
        setPositionY(-randomYPosition());
        setFodderSpeed(speed);
        setThisFodderId(++fodderId);

    }

    public Rectangle2D getBoundaryOfFodder() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    private int randomXPosition() {
        return rand.nextInt(((int) windowWidth - (int) imageWidth));
    }

    private int randomYPosition() {
        return rand.nextInt(700) + 50;
    }

    private void setFodderImage(String fodderImagePath) {
        fodderImage = new Image("file:" + fodderImagePath, 50, 50, false, false);
        setImageWidth(fodderImage.getWidth());
        setImageHeight(fodderImage.getHeight());
    }

    protected void moveFodder() {
        double newPosition = getPositionY();
        newPosition += getFodderSpeed() * GameRound.elapsedTime;
        setPositionY(newPosition);
        /*setPositionY(getPositionY() + getFodderSpeed());*/
    }

    public void renderFodder(GraphicsContext gc) {
        moveFodder();
        gc.drawImage(fodderImage, getPositionX(), getPositionY());
    }

    public int getThisFodderId() {
        return thisFodderId;
    }

    public void setThisFodderId(int thisFodderId) {
        this.thisFodderId = thisFodderId;
    }

    public int getFodderId() {
        return fodderId;
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
