package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import se.lexicon.lars.CannonFodder;

public class GameRound {

    private int roundScore;

    public Fodder[] generateFodder(int amountOfFodder) {
        Fodder[] fodder = new Fodder[amountOfFodder];
        for (int i = 0; i < fodder.length; i++) {
            fodder[i] = new Fodder();
        }
        return fodder;
    }

    public Cannon createCannon() {
        return new Cannon();
    }

    //Check if object1 collides with object2
    public boolean collisionDetection(Object object1, double x, double y, double width, double height, Object object2, double x2, double y2, double width2, double height2) {
        return new Rectangle2D(x, y, width, height).intersects(x2, y2, width2, height2);
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }
}
