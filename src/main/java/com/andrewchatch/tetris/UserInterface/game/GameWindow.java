package com.andrewchatch.tetris.UserInterface.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.andrewchatch.tetris.UserInterface.TetrisWindow;
import com.andrewchatch.tetris.UserInterface.game.scoreboard.ScoreBoard;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GameWindow extends StackPane {
    public GameBoard board;
    public ScoreBoard scoreBoard;

    public GameWindow() {
        try {
            Image image = new Image(new FileInputStream(TetrisWindow.basePath + "/src/main/java/com/andrewchatch/tetris/assets/images/background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
            Background mainBackground = new Background(backgroundImage);
            this.setBackground(mainBackground);
            this.setPrefSize(800,800);
            this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            this.board = new GameBoard();
            this.scoreBoard = new ScoreBoard(this.board);

            this.scoreBoard.setTranslateX(400);
            this.scoreBoard.setTranslateY(-250);

            this.getChildren().add(this.board);
            this.getChildren().add(this.scoreBoard);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
       
    }

    public GameBoard getGameBoard() {
        return this.board;
    }
}
