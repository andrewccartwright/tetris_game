package com.andrewchatch.tetris.GameLogic;

import com.andrewchatch.tetris.UserInterface.game.GameBoard;
import com.andrewchatch.tetris.UserInterface.menu.HelpMenu;
import com.andrewchatch.tetris.UserInterface.menu.PauseMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PauseController {
    private PauseMenu pauseMenu;
    private boolean isPaused = false;
    private GameBoard board;
    private GameDriver driver;

    public PauseController(GameDriver driver, GameBoard board) {
        this.pauseMenu = new PauseMenu();
        this.board = board;
        this.driver = driver;
        this.setPauseButtons();
    }

    public boolean getIsPaused() {
        return this.isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void setPauseButtons() {
        this.pauseMenu.getContinueButton().setOnAction(continueGame());
        this.pauseMenu.getHelpButton().setOnAction(openHelpMenu());
        this.pauseMenu.getExitButton().setOnAction(exitGame());
    }

    public EventHandler<ActionEvent> continueGame() {
        EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                togglePause();
            }
        };

        return actionHandler;
    }

    public EventHandler<ActionEvent> openHelpMenu() {
        EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                HelpMenuController helpController = new HelpMenuController(board);
                
            }
        };

        return actionHandler;
    }

    public EventHandler<ActionEvent> exitGame() {
        EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                Stage stage = (Stage) ((Scene) ((Node) event.getSource()).getScene()).getWindow();
                stage.close();
            }
        };

        return actionHandler;
    }

    public void togglePause() {
        if (this.isPaused) {
            this.driver.start();
            ((StackPane) this.board.getParent()).getChildren().remove(pauseMenu);
        }
        else {
            this.driver.stop();
            ((StackPane) this.board.getParent()).getChildren().add(pauseMenu);
        }
        this.isPaused = !this.isPaused;
    }
}