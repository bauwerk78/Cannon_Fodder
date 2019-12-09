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
    Image chuckImage;
    public static int totalGameScore;
    public static int level;
    boolean gameLost = false;

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

    public Image newGameBanner(GraphicsContext gc, Scene scene) {
        image = new Image("file:Images/newgameimage2.png");
        Image chuck = new Image("file:Images/chuck2.gif");
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent event) {
                        if (event.getCode().toString().contains("ENTER")) {
                            initNewGame();
                            initNewRound(level);
                            gameLost = false;
                        }
                    }
                });
        System.out.println("newGameBanner triggered.");
        return chuck;
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
            //gameLost = true;
            chuckImage = newGameBanner(gc, scene);
        }
        if (isRoundStillGoing() && !isPlayerKilled()) {
            chuckImage = null;
            image = null;
            //System.out.println("Round still going.");
            renderGameRound(gc, scene, currentNanoTime);
        }
        //Just for testing.
        if (gameLost) {
            image = new Image("file:Images/chuck2.gif");
            gc.drawImage(image, (Graphics.windowWidth / 2d) - 230, (Graphics.windowHeight / 2d) - 50);
            System.out.println("game lost");
        }
        gc.drawImage(chuckImage, (Graphics.windowWidth / 2d) - 230, (Graphics.windowHeight / 2d) - 50);
        gc.drawImage(image, (Graphics.windowWidth / 2d) - 150, (Graphics.windowHeight / 2d) - 50);
        gameTimer(currentNanoTime);
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    public void setTotalGameScore(int totalGameScore) {
        MainGame.totalGameScore = totalGameScore;
    }
}
