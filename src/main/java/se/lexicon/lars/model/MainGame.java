package se.lexicon.lars.model;

import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainGame extends GameRound {

    private int gameScore;
    private int level;


    public boolean renderGame() {

        return true;
    }

    public void renderInformation(GraphicsContext gc) {
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 20);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        String gameScore = ("Game score: " + getGameScore());
        gc.fillText(gameScore, 10, 36);
        String fps = ("Fps: ");
    }
    public boolean game(boolean state) {

        return state;
    }

    public void setGame(int currentLevel) {

    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
