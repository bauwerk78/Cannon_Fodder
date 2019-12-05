package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import se.lexicon.lars.CannonFodder;
import static se.lexicon.lars.model.Graphics.windowHeight;
import static se.lexicon.lars.model.Graphics.windowWidth;

import java.util.Random;

public class Fodder {

    private static int fodderId = 0;
    //Todo potentially give different fodder hp so it needs more hits so be removed.
    private static Random rand = new Random();

    private Image fodderImage;
    private double positionX;
    private double positionY;
    private double fodderSpeed;
    private double imageWidth;
    private double imageHeight;
    private boolean playerKilled = false;

    public Fodder() {
        setFodderImage();
        setPositionX(randomXPosition());
        setPositionY(-randomYPosition());
        setFodderSpeed(1);
        fodderId++;

    }

    public Fodder(double speed) {
        setFodderImage();
        setPositionX(randomXPosition());
        setPositionY(-randomYPosition());
        setFodderSpeed(speed);
        fodderId++;
    }

    public Rectangle2D getBoundaryOfFodder() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    //If fodder collides with cannonball.
    public boolean collisionDetection(Cannon cannonBall){
        return getBoundaryOfFodder().intersects(cannonBall.getCannonBallX(), cannonBall.getCannonBallY(), cannonBall.getCannonBallWidth(), cannonBall.getCannonBallHeight());
    }

    private int randomXPosition() {
        int windowMax = ((int) windowWidth - (int) imageWidth) - 1;
        return rand.nextInt(windowMax);
    }

    private int randomYPosition() {
        return rand.nextInt(700) + 50;
    }

    private void setFodderImage() {
        fodderImage = new Image("file:Images/rambo1.png", 50, 50, false, false);
        setImageWidth(fodderImage.getWidth());
        setImageHeight(fodderImage.getHeight());
    }

    private void moveFodder() {
        setPositionY(getPositionY() + getFodderSpeed());
        if(getPositionY() >= (windowHeight - 1) - (imageHeight * 2) - 48) {
            setPlayerKilled(true);
        }
    }

    public void renderFodder(GraphicsContext gc) {
        moveFodder();
        gc.drawImage(fodderImage, getPositionX(), getPositionY());
    }

    public boolean isPlayerKilled() {
        return playerKilled;
    }

    public void setPlayerKilled(boolean playerKilled) {
        this.playerKilled = playerKilled;
    }

    public static int getFodderId() {
        return fodderId;
    }

    public static void setFodderId(int fodderId) {
        Fodder.fodderId = fodderId;
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
