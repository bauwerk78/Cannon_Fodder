package se.lexicon.lars;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.lexicon.lars.model.Cannon;

public class CannonFodder extends Application {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    private long startNanoTime = System.nanoTime();

    public static void main(String[] args) {
        launch(args);
    }

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



        Cannon cannon = new Cannon();
        //cannon.setCannonBallCircle(root);
        cannon.setCannonBallCircle(root, gc);
        //cannon.setCannonBall();

        new AnimationTimer() {
            public void handle(long now) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, windowWidth, windowHeight);

                cannon.renderCannon(gc, mainScene, root);
                if(cannon.isCannonBall()) {
                    cannon.renderCannonBall(gc);
                }

                //cannon.renderCannonBall(gc, mainScene);

            }
        }.start();

        primaryStage.show();


    }
}
