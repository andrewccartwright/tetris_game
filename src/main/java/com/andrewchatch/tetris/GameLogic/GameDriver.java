package com.andrewchatch.tetris.GameLogic;

import com.andrewchatch.tetris.UserInterface.game.GameBoard;
import com.andrewchatch.tetris.UserInterface.game.shapes.Shape;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameDriver extends AnimationTimer {
    private int FRAME_DIVISOR = 30;
    public Shape currentShape;
    public GameBoard board;
    private int frameCount;
    public Stage stage;
    private PauseController pauseController;

    public GameDriver(GameBoard board) {
        super();
        this.currentShape = board.currentShape;
        this.board = board;
        this.pauseController = new PauseController(this, this.board);
        this.board.setOnKeyPressed(keyEventHandler());
        this.board.addEventHandler(ActionEvent.ACTION, handlePopup());
    }

    public void handle(long now) {
        if (frameCount % (FRAME_DIVISOR * 150) == 0 && FRAME_DIVISOR >= 10) {
            FRAME_DIVISOR -= 3;
        }

        if (frameCount % FRAME_DIVISOR == 0) {
            if (this.currentShape.checkGameOver()) {
                this.endGame();
            }

            currentShape.moveDown();
            

            board.checkForFullRows();

            if (!currentShape.getIsMoving()) {
                currentShape = board.generateShape();
            }

        }
        frameCount++;
    }

    public EventHandler<KeyEvent> keyEventHandler() {
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().toString() == "ESCAPE") {
                    pause();
                }
                if (pauseController.getIsPaused()) {
                    return;
                }
                else {
                    if (event.getCode().toString() == "LEFT") {
                        board.currentShape.moveLeft();
                    }
                    else if (event.getCode().toString() == "RIGHT") {
                        board.currentShape.moveRight();
                    }
                    else if (event.getCode().toString() == "UP") {
                        board.currentShape.rotate();
                    }
                    else if (event.getCode().toString() == "DOWN") {
                        board.currentShape.moveDown();
                    }
                    else if (event.getCode().toString() == "SPACE") {
                        board.currentShape.moveFast();
                    }
                }
            }
        };

        return eventHandler;
    }

    public void createGameOverWindow() {
        StackPane gameOverWindow = new StackPane();
        Text gameOverMessage = new Text("Game Over! Want to play again?");
        Button startOver = new Button("Play Again");
        VBox box = new VBox();

        box.getChildren().add(gameOverMessage);
        box.getChildren().add(startOver);
        box.setAlignment(Pos.CENTER);

        startOver.setOnAction(FlowController.restartGame);

        gameOverWindow.setPrefSize(250, 250);
        gameOverWindow.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Background background = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0)));
        gameOverWindow.setBackground(background);
        gameOverWindow.getChildren().add(box);

        ((StackPane) this.board.getParent()).getChildren().add(gameOverWindow);
    }

    public EventHandler<ActionEvent> handlePopup() {
        EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // Stage stage = (Stage) ((Scene) ((Node) event.getSource()).getScene()).getWindow();
                createGameOverWindow();
            }
        };

        return actionHandler;
    }

    @Override
    public void stop() {
        super.stop();
    }

    public void endGame() {
        super.stop();
        ActionEvent event = new ActionEvent();
        ActionEvent.fireEvent(board, event);
    }

    @Override
    public void start() {
        super.start();
    }

    public void pause() {
        pauseController.togglePause();
    }

    
}
