package se.lexicon.lars.model;

public class MainGame extends GameRound {


    public static int totalGameScore;
    public static int level;






    public boolean renderGame() {

        return true;
    }

    private boolean renderGameRound() {

        return true;
    }

    public boolean game(boolean state) {

        return state;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    public void setTotalGameScore(int totalGameScore) {
        this.totalGameScore = totalGameScore;
    }
}
