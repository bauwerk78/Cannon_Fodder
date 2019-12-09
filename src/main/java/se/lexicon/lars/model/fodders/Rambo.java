package se.lexicon.lars.model.fodders;

import se.lexicon.lars.model.Fodder;
import se.lexicon.lars.model.GameRound;

public class Rambo extends Fodder {

    private double timeCounter = 0;
    private boolean movingRight;
    private boolean movingLeft;

    public Rambo(double speed, String imagePath) {
        super(speed, imagePath);
    }

//TODO all beneath this.
    public boolean delayer() {
        int secondsToDelay = 2;
        if (timeCounter < secondsToDelay) {
            timeCounter += GameRound.elapsedTime;
            return true;
        }
        timeCounter = 0;
        movingLeft = false;
        movingRight = false;
        return false;
    }

    public void randomXDirection() {
        int randomXDirection = rand.nextInt(2);
        if (!delayer()) {
            if (randomXDirection == 0) {
                movingLeft = true;
            }
            if(randomXDirection == 1){
                movingRight = true;
            }
        }
    }

    @Override
    protected void moveFodder() {
        setPositionY(getPositionY() + getFodderSpeed() * GameRound.elapsedTime);
        randomXDirection();
        if (movingLeft && delayer()) {
            //System.out.println("going left");
            setPositionX(getPositionX() - (getFodderSpeed() * GameRound.elapsedTime));
        }
        if (movingRight && delayer()) {
            //System.out.println("going right");
            setPositionX(getPositionX() + (getFodderSpeed() * GameRound.elapsedTime));
        }
        if (getPositionX() < 0) {
            setPositionX(0);
        }
        if (getPositionX() + getImageWidth() > windowWidth) {
            setPositionX(windowWidth - getImageWidth());
        }


    }






}
