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

    //Set the boundary of cannonball.
    private Rectangle2D getBoundaryOfCannonBall(Cannon cannonBall) {
        return new Rectangle2D(cannonBall.getCannonBallX(), cannonBall.getCannonBallY(), cannonBall.getCannonBallWidth(), cannonBall.getCannonBallHeight());
    }
    //If cannonball collides with fodder.
    public boolean collisionWithCannonBall(Cannon cannonBall, Fodder fodder) {
        return getBoundaryOfCannonBall(cannonBall).intersects(fodder.getPositionX(), fodder.getPositionY(), fodder.getImageWidth(), fodder.getImageHeight());
    }

    public boolean fodderBoundary(Fodder fodder, Cannon cannon) {
        return fodder.getPositionY() >= (CannonFodder.windowHeight - cannon.getImageHeight() - 50);
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }
}
