package se.lexicon.lars.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainGame extends GameRound {

    private int gameScore;


    public void level1() {

    }

    public void renderInformation(GraphicsContext gc) {
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 30);
        gc.setFont(theFont);
        gc.setFill(Color.WHITE);
        String gameScore = ("Game score: " + getGameScore());
        gc.fillText(gameScore, 10, 36);
    }
    public boolean game() {

        return true;
    }

    public void setGame() {

    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
