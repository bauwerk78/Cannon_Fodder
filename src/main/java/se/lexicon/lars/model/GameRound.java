package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se.lexicon.lars.model.fodders.Rambo;

import java.util.ArrayList;
import java.util.Iterator;

import static se.lexicon.lars.model.Graphics.*;
import static se.lexicon.lars.model.MainGame.totalGameScore;
import static se.lexicon.lars.model.MainGame.level;

public class GameRound {

    public static double elapsedTime;

    private Cannon cannon = new Cannon();
    private ArrayList<Fodder> fodderList = new ArrayList<>();
    private ArrayList<Rambo> ramboList = new ArrayList<>();
    private int roundScore;
    private int amountOfFodder;
    private double fodderSpeed;
    private boolean roundStillGoing = true;
    private boolean playerKilled = false;
    private Image explosion1 = new Image("file:Images/explosionfromabove1.png", 50, 50, false, false);
    private double explosion1ImageWidth = 50;
    private double explosion1ImageHeight = 50;
    private double explosionPosX;
    private double explosionPosY;
    private double explosionTimer = 0;
    private boolean fodderExploded = false;

    Image image;

    public GameRound() {
    }

    public GameRound(int level) {
        initNewRound(level);
    }

    public void initNewRound(int level) {
        setLevel(level);
        setAmountOfFodder((level * 2) + 3);
        setFodderSpeed((level * 5d) + 40d);
        setRoundScore(0);
        Fodder.fodderId = 0;
        setRoundStillGoing(true);
        fodderList = generateFodder();
    }

    private ArrayList<Fodder> generateFodder() {
        fodderList.clear();
        for (int i = 0; i < amountOfFodder; i++) {
            fodderList.add(new Rambo(getFodderSpeed(), "Images/rambo2.gif"));
        }
        if (fodderList.isEmpty()) {
            return null;
        }
        return fodderList;
    }

/*    private ArrayList<Rambo> generateRambo() {
        ramboList.clear();
        for (int i = 0; i < amountOfFodder; i++) {
            ramboList.add(new Rambo(getFodderSpeed(), "Images/rambo2.gif"));
        }
        if (ramboList.isEmpty()) {
            return null;
        }
        return ramboList;
    }*/

/*    private ArrayList<Fodder> generateFodder() {
        fodderList.clear();
        for (int i = 0; i < amountOfFodder; i++) {
            fodderList.add(new Fodder(getFodderSpeed(), "Images/rambo2.gif"));
        }
        if (fodderList.isEmpty()) {
            return null;
        }
        return fodderList;
    }*/


    protected Cannon createCannon() {
        return new Cannon();
    }

    protected void resetCannon() {
        if (cannon != null) {
            cannon = null;
        }
        cannon = new Cannon();
    }

    public void gameTimer(long currentNanoTime) {
        elapsedTime = (currentNanoTime - startNanoTime.doubleValue()) / 1_000_000_000d;
        startNanoTime = currentNanoTime;
    }

    public void renderGameRound(GraphicsContext gc, Scene scene, long currentNanoTime) {
        //System.out.println(elapsedTime);
        //System.out.println(getFodderSpeed());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, windowWidth, windowHeight);
        renderRedBorderRectangle(gc);
        renderGradientBackgroundRectangles(gc, Color.BLACK, Color.DARKGREEN);
        cannon.renderCannon(gc, scene);
        if (cannon.isCannonBall()) {
            cannon.renderCannonBall(gc);
        }
        Iterator<Fodder> fodders = fodderList.iterator();
        while (fodders.hasNext()) {
            Fodder fodder = fodders.next();
            fodder.renderFodder(gc);
            //System.out.println("fodder id: " + fodder.getThisFodderId() + " fodder Y position: " + fodder.getPositionY());
            if (collisionDetection(fodder.getBoundaryOfFodder(), cannon.getBoundaryOfCannonBall())) {
                setExplosionPosition(fodder.getPositionX(), fodder.getPositionY());
                renderExplosion(gc);
                fodders.remove();
                whenCollidedWithFodder(gc);
            }
            if (fodder.getPositionY() >= (windowHeight - 1) - (fodder.getImageHeight() * 3) - 48) {
                whenPlayerIsDead(gc);
                break;
            }
        }
        if (fodderExploded) {
            renderExplosion(gc);
        }
        renderInformation(gc);
    }//End of render game round.

    public void whenCollidedWithFodder(GraphicsContext gc) {
        setAmountOfFodder(getAmountOfFodder() - 1);
        cannon.setCannonBall(false);
        setRoundScore(getRoundScore() + 1);
        totalGameScore = totalGameScore + 10;
    }

    public void whenPlayerIsDead(GraphicsContext gc) {
        for (Fodder fod : fodderList) {
            fod.setFodderSpeed(0);
            fod.renderFodder(gc);
        }
        setPlayerKilled(true);
        cannon.setCannonBall(false);
        setRoundStillGoing(false);

    }

    public void setExplosionPosition(double positionX, double positionY) {
        explosionPosX = positionX;
        explosionPosY = positionY;
    }

    public boolean explosionTimer() {
        int secondsToDelay = 1;
        if (explosionTimer < secondsToDelay) {
            explosionTimer += GameRound.elapsedTime;
            fodderExploded = true;
            return true;
        }
        explosionTimer = 0;
        fodderExploded = false;
        return false;
    }

    public void renderExplosion(GraphicsContext gc) {
        if (explosionTimer()) {
            gc.drawImage(explosion1, explosionPosX, explosionPosY);
        }
    }

    public void renderRedBorderRectangle(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(0, (windowHeight - 1) - (cannon.getImageHeight() * 2) - 48, windowWidth - 1, 5);
    }

    public void renderGradientBackgroundRectangles(GraphicsContext gc, Color firstColor, Color secondColor) {
        Stop[] stops = {new Stop(0, firstColor), new Stop(1, secondColor)};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(lg);
        gc.fillRect(0, (windowHeight - 1) - (cannon.getImageHeight() * 2) - 47, windowWidth, 99);
        Stop[] stops2 = {new Stop(0, secondColor), new Stop(1, firstColor)};
        LinearGradient lg2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops2);
        gc.setFill(lg2);
        gc.fillRect(0, (windowHeight - 1) - 49, windowWidth, 55);
    }

    public void renderInformation(GraphicsContext gc) {
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 15);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        String totalScore = ("Total score: " + totalGameScore);
        gc.fillText(totalScore, 10, 25);
        String roundScore = ("Kills this round: " + getRoundScore());
        gc.fillText(roundScore, 10, 56);
        String fodderLeft = ("Fodder left to kill this level: " + getAmountOfFodder());
        gc.fillText(fodderLeft, 10, 87);
        String level = ("Level: " + getLevel());
        gc.fillText(level, 10, 118);
        /*String fodderSpeed = ("Fodderspeed: " + fodderList.get(0).getFodderSpeed());
        gc.fillText(fodderSpeed, 10, 120);
        String fodderYPosition = ("FodderYPosition: " + fodderList.get(0).getPositionY());
        gc.fillText(fodderYPosition, 10, 150);*/
        //String fps = ("Fps: ");
    }

    public void clearFodderList() {
        fodderList.clear();
    }

    public boolean isPlayerKilled() {
        return playerKilled;
    }

    public void setPlayerKilled(Boolean set) {
        playerKilled = set;
    }

    //Check if these areas collides.
    public boolean collisionDetection(double x, double y, double width, double height, double x2, double y2, double width2, double height2) {
        return new Rectangle2D(x, y, width, height).intersects(new Rectangle2D(x2, y2, width2, height2));
    }

    //Todo might have ruined something here? Certain objects is disappearing without collision.
    //Check if object1 collides with object2
    public boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        if (object1.intersects(object2)) {
            //System.out.println("collision detected.");
            object2 = null;
            object1 = null;
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        MainGame.level = level;
    }

    public double getFodderSpeed() {
        return fodderSpeed;
    }

    public void setFodderSpeed(double fodderSpeed) {
        this.fodderSpeed = fodderSpeed;
    }

    public int getAmountOfFodder() {
        return amountOfFodder;
    }

    public void setAmountOfFodder(int fodder) {
        this.amountOfFodder = fodder;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public boolean isRoundStillGoing() {
        return roundStillGoing;
    }

    public void setRoundStillGoing(boolean roundStillGoing) {
        this.roundStillGoing = roundStillGoing;
    }
}
