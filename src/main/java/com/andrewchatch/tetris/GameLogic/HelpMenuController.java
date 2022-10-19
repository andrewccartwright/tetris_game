package com.andrewchatch.tetris.GameLogic;

import com.andrewchatch.tetris.UserInterface.game.GameBoard;
import com.andrewchatch.tetris.UserInterface.menu.HelpMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class HelpMenuController {
    private HelpMenu helpMenu;
    private GameBoard board;

    public HelpMenuController(GameBoard board) {
        this.board = board;
        this.helpMenu = new HelpMenu();
        this.setButtons();
        ((StackPane) board.getParent()).getChildren().add(this.helpMenu);
    }

    public void setButtons() {
        this.helpMenu.getExitButton().setOnAction(exitHelpMenu());
    }

    public EventHandler<ActionEvent> exitHelpMenu() {
        EventHandler<ActionEvent> actionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                ((StackPane) board.getParent()).getChildren().remove(helpMenu);
            }
        };

        return actionHandler;
    }
}
