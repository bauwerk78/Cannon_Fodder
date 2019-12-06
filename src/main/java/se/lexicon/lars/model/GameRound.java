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
import static se.lexicon.lars.model.MainGame.totalGameScore;
import static se.lexicon.lars.model.MainGame.level;
import static se.lexicon.lars.model.Fodder.playerKilled;

public class GameRound {

    //private int level;
    private Cannon cannon;
    private ArrayList<Fodder> fodderList = new ArrayList<>();
    private Fodder fodder;
    private int roundScore;
    private int amountOfFodder;
    private int fodderSpeed;
    private boolean roundStillGoing = true;
    private boolean roundWon = false;

    public GameRound() {
    }

    public GameRound(int level) {
        initNewRound(level);
    }

    public void initNewRound(int level) {
        setRoundStillGoing(true);
        setLevel(level);
        setAmountOfFodder(level * 5);
        setFodderSpeed(level);
        setPlayerKilled(false);
        if (cannon != null) {
            cannon = null;
            //fodderList = null;
        }
        cannon = createCannon();
        fodderList = generateFodder();
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
        if (roundStillGoing) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, windowWidth, windowHeight);
            renderRectangle(gc);
            cannon.renderCannon(gc, scene);
            if (cannon.isCannonBall()) {
                cannon.renderCannonBall(gc);
            }
            Iterator<Fodder> fodders = fodderList.iterator();
            while (fodders.hasNext()) {
                Fodder fodder = fodders.next();
                fodder.renderFodder(gc);
                if (collisionDetection(fodder.getBoundaryOfFodder(), cannon.getBoundaryOfCannonBall())) {
                    fodders.remove();
                    whenCollidedWithFodder();
                }

            }
/*            for (Fodder fodder : fodderList) {
                if (fodder.isPlayerKilled()) {
                    //System.out.println("Player is dead!");
                    for (Fodder fod : fodderList) {
                        fod.setFodderSpeed(0);
                    }

                    break;
                }
            }*/

           /* if (getAmountOfFodder() == 0) {
                System.out.println("Round won.");
                setRoundWon(true);
                setLevel(getLevel() + 1);

            }*/

            renderInformation(gc);
        }
    }

    public void whenCollidedWithFodder() {
        setAmountOfFodder(getAmountOfFodder() - 1);
        cannon.setCannonBall(false);
        setRoundScore(getRoundScore() + 1);
        totalGameScore = totalGameScore + 10;
    }

    public void whenPlayerIsDead() {
        cannon.setCannonBall(false);
        setRoundStillGoing(false);
        if (isPlayerKilled()) {
            for (Fodder fod : fodderList) {
                fod.setFodderSpeed(0);
            }
        }

    }

    public void renderRectangle(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(0, (windowHeight - 1) - (cannon.getImageHeight() * 2) - 48, windowWidth - 1, 5);
    }

    public void renderInformation(GraphicsContext gc) {
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 15);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        String totalScore = ("Total score: " + totalGameScore);
        gc.fillText(totalScore, 10, 25);
        String roundScore = ("Kills this round: " + getRoundScore());
        gc.fillText(roundScore, 10, 56);
        String fodderLeft = ("Fodder left to kill: " + getAmountOfFodder());
        gc.fillText(fodderLeft, 10, 87);
        //String fps = ("Fps: ");
    }

    public boolean isPlayerKilled() {
        return playerKilled;
    }

    public void setPlayerKilled(Boolean set) {
        playerKilled = set;
    }

    public void renderGameOver() {

    }

    public void renderGameRoundWon() {

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

    public boolean isRoundWon() {
        return roundWon;
    }

    public void setRoundWon(boolean roundWon) {
        this.roundWon = roundWon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        MainGame.level = level;
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
