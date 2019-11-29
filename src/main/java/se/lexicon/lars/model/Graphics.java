package se.lexicon.lars.model;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Graphics extends Application {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    private long startNanoTime = System.nanoTime();
    private Fodder fodder;
    private Cannon cannon;

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


        cannon = new Cannon();
        cannon.setCannonBallCircle(gc);

        fodder = new Fodder();

        new AnimationTimer() {
            public void handle(long now) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, windowWidth, windowHeight);

                cannon.renderCannon(gc, mainScene);
                fodder.renderFodder(gc);
                if(cannon.isCannonBall()) {
                    cannon.renderCannonBall(gc);
                }
                if(fodder.collisionDetection(cannon)) {
                    System.out.println("Cannonball hits the target.");
                    fodder = null;
                    fodder = new Fodder();
                }

                if(cannon.collisionDetection(fodder)) {
                    System.out.println("Fodder hits the cannon.");
                }

            }
        }.start();

        primaryStage.show();


    }
}


