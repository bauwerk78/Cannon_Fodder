package se.lexicon.lars.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

//Useful link https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm

public class MainGame extends GameRound {


    public static int totalGameScore;
    public static int level;
    private boolean gameIsRunning;
    Button button;
    private boolean rendButton = true;

    public MainGame(int level) {
        super(level);
        initNewGame();
    }

    public void startNewGame() {
        initNewGame();
    }

    public void renderButton(Group group, GraphicsContext gc, Scene scene) {
        setRendButton(true);
        button = new Button();
        button.setLayoutX((Graphics.windowWidth / 2.0d) - 100);
        button.setLayoutY(Graphics.windowHeight / 2.0d);

        if (isPlayerKilled()) {
            button.setText("You died, click to start a new game.");
        }
        if (isRoundWon()) {
            button.setText("You won the round, click to continue.");
        }
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Button clicked!");
                if (isPlayerKilled()) {
                    group.getChildren().remove(button);
                    setPlayerKilled(false);
                    initNewGame();
                    initNewRound(level);
                    startNewGame();
                    setRendButton(false);

                    //renderGame(group, gc, scene);
                }
                if (isRoundWon()) {
                    group.getChildren().remove(button);
                    setRoundWon(false);
                    initNewRound(level);
                    setRendButton(false);
                    //renderGame(group, gc, scene);
                }
            }
        };
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        group.getChildren().add(button);
       /*button.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if(isPlayerKilled()) {
                   group.getChildren().remove(button);
                   setPlayerKilled(false);
                   initNewGame();
                   initNewRound(level);
                   startNewGame();

                   //renderGame(group, gc, scene);
               }
               if(isRoundWon()) {
                   group.getChildren().remove(button);
                   setRoundWon(false);
                   initNewRound(level);
                   //renderGame(group, gc, scene);
               }
           }
       });*/


    }

    public void initNewGame() {
        setTotalGameScore(0);
        setLevel(1);
        setPlayerKilled(false);
    }

    public boolean renderGame(Group group, GraphicsContext gc, Scene scene) {
        if (getAmountOfFodder() == 0) {
            System.out.println("Round won.");
            setAmountOfFodder(-1);
            setRoundWon(true);
            setLevel(getLevel() + 1);
            initNewRound(level);
            //setRoundStillGoing(true);
            //renderButton(group, gc, scene);
            setRendButton(false);

        }
        if (isPlayerKilled()) {
            System.out.println("Player is dead.");
            whenPlayerIsDead();
            initNewGame();
            initNewRound(level);
            //renderButton(group, gc, scene);
            setRendButton(false);
        }

        if (isRoundStillGoing()) {
            renderGameRound(gc, scene);
        }


        return true;
    }

    public boolean isRendButton() {
        return rendButton;
    }

    public void setRendButton(boolean rendButton) {
        this.rendButton = rendButton;
    }

    public int getTotalGameScore() {
        return totalGameScore;
    }

    public void setTotalGameScore(int totalGameScore) {
        this.totalGameScore = totalGameScore;
    }
}
