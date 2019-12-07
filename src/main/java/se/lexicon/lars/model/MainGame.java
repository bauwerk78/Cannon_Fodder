package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


//Useful link https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm

public class MainGame extends GameRound {

    Image image;
    public static int totalGameScore;
    public static int level;


    public MainGame(int level) {
        super(level);
        initNewGame();
    }

    public void initNewGame() {

        setTotalGameScore(0);
        setLevel(1);
        Fodder.fodderId = 0;
        resetCannon();
    }

    public void newGameBanner(GraphicsContext gc, Scene scene) {
        image = new Image("file:Images/newgameimage2.png");
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {

                    public void handle(KeyEvent event) {
                        if (event.getCode().toString().contains("ENTER")) {
                            initNewGame();
                            initNewRound(level);
                        }
                    }
                });
        gc.drawImage(image, (Graphics.windowWidth / 2d) - 150, (Graphics.windowHeight / 2d) - 50);
        System.out.println("newGameBanner triggered.");
    }

    public void renderGame(Group group, GraphicsContext gc, Scene scene, long currentNanoTime) {
        if (getAmountOfFodder() == 0) {
            System.out.println("Round won.");
            setAmountOfFodder(-1);
            setLevel(getLevel() + 1);
            initNewRound(level);
        }
        if (isPlayerKilled()) {
            System.out.println("Player is dead.");
            setPlayerKilled(false);
            newGameBanner(gc, scene);
        }
        if (isRoundStillGoing() && !isPlayerKilled()) {
            //System.out.println("Round still going.");
            renderGameRound(gc, scene, currentNanoTime);
        }
        gameTimer(currentNanoTime);
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    public void setTotalGameScore(int totalGameScore) {
        MainGame.totalGameScore = totalGameScore;
    }
}
