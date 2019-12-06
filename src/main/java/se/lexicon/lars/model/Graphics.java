package se.lexicon.lars.model;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Graphics extends Application {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    //private long startNanoTime = System.nanoTime();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Cannon Fodder");
        Group root = new Group();
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        Canvas canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //MainGame mainGame = new MainGame();
        GameRound gameRound = new GameRound(1);
        MainGame mainGame = new MainGame(1);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //gameRound.renderGameRound(gc, mainScene);
                mainGame.renderGame(root, gc, mainScene);
            }
        }.start();

        primaryStage.show();


    }
}


