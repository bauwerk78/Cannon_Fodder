package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import se.lexicon.lars.CannonFodder;

public class GameRound {
    private static int level = 1;

    private Cannon cannon;
    private Fodder[] fodder;
    private int roundScore;
    private int fodderSpeed = 1;

    public GameRound() {
        initRound(level);
    }

    public Fodder[] generateFodder(int amountOfFodder) {
        Fodder[] fodder = new Fodder[amountOfFodder];
        for (int i = 0; i < fodder.length; i++) {
            fodder[i] = new Fodder(fodderSpeed);
        }
        return fodder;
    }

    public Cannon createCannon() {
        return new Cannon();
    }

    public void initRound(int level) {
        cannon = createCannon();
        fodder = generateFodder(10);
        if (level > 1) {
            fodderSpeed++;
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
}
