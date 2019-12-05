package se.lexicon.lars.model;

public class MainGame extends GameRound {

    private int level;
    private int totalGameScore;


    public boolean renderGame() {

        return true;
    }

    private boolean renderGameRound() {

        return true;
    }

    public boolean game(boolean state) {

        return state;
    }


}
