package com.andrewchatch.tetris.UserInterface.game;

import com.andrewchatch.tetris.UserInterface.game.scoreboard.ScoreBoard;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GameWindow extends StackPane {
    public GameBoard board = new GameBoard();
    public ScoreBoard scoreBoard = new ScoreBoard(this.board);

    public GameWindow() {
        this.setPrefSize(800,800);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.scoreBoard.setTranslateX(400);
        this.scoreBoard.setTranslateY(-250);

        this.getChildren().add(this.board);
        this.getChildren().add(this.scoreBoard);

        Image image = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        Background mainBackground = new Background(backgroundImage);
        this.setBackground(mainBackground);
       
    }

    public GameBoard getGameBoard() {
        return this.board;
    }
}
