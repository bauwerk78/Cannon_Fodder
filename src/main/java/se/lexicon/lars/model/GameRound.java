package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Iterator;

import static se.lexicon.lars.model.Graphics.windowHeight;
import static se.lexicon.lars.model.Graphics.windowWidth;

public class GameRound {

    private int level;
    private Cannon cannon;
    private ArrayList<Fodder> fodderList = new ArrayList<>();
    private Fodder fodder;
    private int roundScore;
    private int amountOfFodder;
    private int fodderSpeed;
    private boolean roundWon = false;

    public GameRound() {
    }

    public GameRound(int level) {
        initRound(level);
    }

    private void initRound(int level) {
        setLevel(level);
        setAmountOfFodder(level);
        setFodderSpeed(level);
        cannon = createCannon();
        fodderList = generateFodder();
    }

    private void resetRound() {
        cannon = null;
        fodderList = null;
        if (isRoundWon()) {
            level++;
        }
    }

    private ArrayList<Fodder> generateFodder() {
        for (int i = 0; i < amountOfFodder; i++) {
            fodderList.add(new Fodder(level));
        }
        if (fodderList.isEmpty()) {
            return null;
        }
        return fodderList;
    }

    private Cannon createCannon() {
        return new Cannon();
    }


    public void renderGameRound(GraphicsContext gc, Scene scene) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, windowWidth, windowHeight);
        //TODO render fodder, broken game atm.
        cannon.renderCannon(gc, scene);
        if (cannon.isCannonBall()) {
            cannon.renderCannonBall(gc);
        }
        Iterator<Fodder> fodders = fodderList.iterator();
        while(fodders.hasNext()) {
            Fodder fodder = fodders.next();
            fodder.renderFodder(gc);
            if(collisionDetection(fodder.getBoundaryOfFodder(), cannon.getBoundaryOfCannonBall())){
                fodderList.remove(fodder);
            }

        }
    }

    public void renderInformation(GraphicsContext gc) {
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 20);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        //String totalScore = ("Total score: " + getGameScore());
        //gc.fillText(totalScore, 10, 36);
        //String roundScore = ("Round score: " + getRoundScore();
        //String fps = ("Fps: ");
    }

    public void gameRound() {

    }

    public void endOfRound() {

    }

    //Check if these areas collides.
    public boolean collisionDetection(double x, double y, double width, double height, double x2, double y2, double width2, double height2) {
        return new Rectangle2D(x, y, width, height).intersects(new Rectangle2D(x2, y2, width2, height2));
    }

    //Check if object1 collides with object2
    public boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        return object1.intersects(object2);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFodderSpeed() {
        return fodderSpeed;
    }

    public void setFodderSpeed(int fodderSpeed) {
        this.fodderSpeed = fodderSpeed;
    }

    public int getAmountOfFodder() {
        return amountOfFodder;
    }

    public void setAmountOfFodder(int level) {
        this.amountOfFodder = level * 5;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public boolean isRoundWon() {
        return roundWon;
    }

    public void setRoundWon(boolean roundWon) {
        this.roundWon = roundWon;
    }
}
