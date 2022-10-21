package com.andrewchatch.tetris.UserInterface.game.scoreboard;

import com.andrewchatch.tetris.UserInterface.game.GameBoard;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreBoard extends StackPane {
    private Text scoreCounter; 
    public GameBoard board;

    public ScoreBoard(GameBoard board) {
        super();
        VBox box = new VBox();
        this.board = board;
        this.scoreCounter = new Text("Score: " + this.board.getCurrentScore());
        this.scoreCounter.setFill(Color.WHITE);
        Font neonFont = Font.loadFont(getClass().getResourceAsStream("/fonts/neon_pixel-7.ttf"), 48);
        this.scoreCounter.setFont(neonFont);
        // try {
            
        // }
        // catch (FileNotFoundException e) {
        //     System.out.println(e);
        // }
        
        box.getChildren().add(scoreCounter);
        box.setAlignment(Pos.CENTER);

        box.setPrefSize(250, 250);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.getChildren().add(box);
    }

    public void updateScore() {
        scoreCounter.setText("Score: " + this.board.getCurrentScore());
    }
}
