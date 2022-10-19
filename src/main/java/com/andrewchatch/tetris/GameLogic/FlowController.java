package com.andrewchatch.tetris.GameLogic;

import com.andrewchatch.tetris.UserInterface.game.GameWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FlowController {

    public static EventHandler<ActionEvent> startGame = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) ((Scene) ((Node) event.getSource()).getScene()).getWindow();
            Scene scene = (Scene) ((Node) event.getSource()).getScene();
            scene.setFill(Color.BLACK);
            GameWindow game = new GameWindow();
            scene.onKeyPressedProperty().bind(game.getGameBoard().onKeyPressedProperty());
            scene.setRoot(game);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            // stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {

            //     @Override
            //     public void changed(ObservableValue<? extends Boolean> observable,
            //             Boolean oldValue, Boolean newValue) {
            //         if(newValue != null && !newValue.booleanValue())
            //             stage.setFullScreen(true);
            //     }
            // });
        }
    };

    public static EventHandler<ActionEvent> restartGame = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) ((Scene) ((Node) event.getSource()).getScene()).getWindow();
            System.out.println(((Node) event.getSource()).getParent().getParent().getScene());
            Scene scene = stage.getScene();
            GameWindow game = new GameWindow();
            scene.onKeyPressedProperty().bind(game.getGameBoard().onKeyPressedProperty());
            scene.setRoot(game);
        }
    };

    public static EventHandler<ActionEvent> exitGame = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) ((Scene) ((Node) event.getSource()).getScene()).getWindow();
            
            stage.close();
        }
    };
}
