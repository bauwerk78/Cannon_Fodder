package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class GameRound {

    private int level = 1;
    private Cannon cannon;
    private ArrayList<Fodder> fodderList = new ArrayList<>();
    private int roundScore;
    private int amountOfFodder = level * 5;
    private int fodderSpeed = 1;
    private boolean roundWon = false;

    public GameRound() {
        initRound();
    }

    public ArrayList<Fodder> generateFodder() {
        for (int i = 0; i < amountOfFodder; i++) {
            fodderList.add(new Fodder());
            fodderList.get(i).setFodderSpeed(level);
        }
        if(fodderList != null) {
            return fodderList;
        }
        return null;
    }

    public Cannon createCannon() {
        return new Cannon();
    }

    public void initRound() {
        cannon = createCannon();
        fodderList = generateFodder();
        fodderSpeed = level;
    }

    public void resetRound() {
        cannon = null;
        fodderList = null;
        if(isRoundWon()) {
            level++;
        }
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
