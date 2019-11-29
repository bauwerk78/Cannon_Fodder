package se.lexicon.lars.model;

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

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }
}
