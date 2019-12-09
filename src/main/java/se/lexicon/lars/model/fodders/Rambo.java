package se.lexicon.lars.model.fodders;

import se.lexicon.lars.model.Fodder;
import se.lexicon.lars.model.GameRound;

public class Rambo extends Fodder {

    public Rambo(double speed, String imagePath) {
        super(speed, imagePath);
    }

    @Override
    protected void moveFodder() {
        setPositionY(getPositionY() + getFodderSpeed() * GameRound.elapsedTime);
        int randomXDirection = rand.nextInt(2);
        if(randomXDirection == 0) {
            setPositionX(getPositionX() - getFodderSpeed() * GameRound.elapsedTime);
        }
        if(randomXDirection == 1) {
            setPositionX(getPositionX() + getFodderSpeed() * GameRound.elapsedTime);
        }
        if(getPositionX() < 0) {
            setPositionX(0);
        }
        if(getPositionX() + getImageWidth() > windowWidth) {
            setPositionX(windowWidth - getImageWidth());
        }


    }


}
