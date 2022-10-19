package com.andrewchatch.tetris.UserInterface.game;

import com.andrewchatch.tetris.GameLogic.GameDriver;
import com.andrewchatch.tetris.UserInterface.game.scoreboard.ScoreBoard;
import com.andrewchatch.tetris.UserInterface.game.shapes.Brick;
import com.andrewchatch.tetris.UserInterface.game.shapes.Shape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.IShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.LShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.ReverseLShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.SShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.SquareShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.TShape;
import com.andrewchatch.tetris.UserInterface.game.shapes.types.ZShape;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard extends GridPane {
    public final static int GRID_WIDTH = 18;
    public final static int GRID_HEIGHT = 30;
    private final int BRICK_SIZE = Brick.BRICK_SIZE;
    private final int START_X = (GRID_WIDTH / 2);
    private final int START_Y = 0;
    private final int NUM_SHAPES = 7;
    private int currentScore = 0;
    public Shape currentShape;
    public Shape nextShape;

    private final Color PURPLE = Color.rgb(199,36,177);
    private final Color LIGHTBLUE = Color.AQUA;
    private final Color YELLOW = Color.rgb(255,255,0);
    private final Color ORANGE = Color.rgb(255,124,1);
    private final Color RED = Color.rgb(210,39,48);
    private final Color BLUE = Color.rgb(2,64,254);
    private final Color GREEN = Color.rgb(68,214,44);

    public GameBoard() {
        super();

        this.fillGrid();

        this.currentShape = generateShape();

        this.setPrefSize(GameBoard.GRID_WIDTH * BRICK_SIZE, GameBoard.GRID_HEIGHT * BRICK_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        this.runGame();
    }

    private void fillGrid() {
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                Rectangle node = new Rectangle();
                node.setHeight(BRICK_SIZE);
                node.setWidth(BRICK_SIZE);
                node.setFill(Color.BLACK);
                node.setStroke(Color.rgb(50,152,226));
                this.add(node, i, j);
            }
        }
    }

    public Shape getRandomShape() {
        Shape shape;
        int random = (int) (Math.random() * NUM_SHAPES);
        switch(random) {
            case 0:
                shape = new LShape(PURPLE, START_X, START_Y); //PURPLE
                break;
            case 1:
                shape = new IShape(LIGHTBLUE, START_X, START_Y); //BLUE
                break;
            case 2:
                shape = new ReverseLShape(YELLOW, START_X, START_Y); //YELLOW
                break;
            case 3:
                shape = new SquareShape(ORANGE, START_X, START_Y); //ORANGE
                break;
            case 4:
                shape = new SShape(RED, START_X, START_Y); //RED
                break;
            case 5:
                shape = new ZShape(BLUE, START_X, START_Y); //FUSCHIA
                break;
            case 6:
                shape = new TShape(GREEN, START_X, START_Y); //GREEN
                break;
            default:
                shape = new IShape(LIGHTBLUE, START_X, START_Y); //BLUE
                break;
        }

        return shape;
    }

    public Shape generateShape() {
        Shape shape = getRandomShape();
        this.currentShape = shape;
        this.currentShape.addBricks(this);
        return shape;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public void incrementScore() {
        this.currentScore += 100;
        ObservableList<Node> children = this.getParent().getChildrenUnmodifiable();
        for (Node child : children) {
            if (child instanceof ScoreBoard) {
                ((ScoreBoard) child).updateScore();
            }
        }
    }

    public void checkForFullRows() {
        ObservableList<Node> children = this.getChildrenUnmodifiable();
        FilteredList<Node> row = new FilteredList<Node>(children);
        int rowIndex = 0;
        
        for (int i = 0; i < GRID_HEIGHT; i++) {
            final int tempIndex = i; 
            row = children.filtered(node -> filterFunction(node, tempIndex));
            rowIndex = tempIndex;
            if (row.size() == GRID_WIDTH) {
                this.removeRow(row, rowIndex);
            }
        }

        
    }

    public boolean filterFunction(Node node, int rowIndex) {
        if (GridPane.getRowIndex(node) == rowIndex && node instanceof Brick) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void removeRow(FilteredList<Node> row, int rowIndex) {
        for (int i = 0; i < row.size(); i++) {
            this.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowIndex && node instanceof Brick);
        }

        this.incrementScore();
        this.shiftDown(rowIndex);
    }

    public void shiftDown(int deletedRowIndex) {
        ObservableList<Node> children = this.getChildrenUnmodifiable();

        for (Node child : children) {
            if (child.getClass() == Brick.class) {
                int rowIndex = GridPane.getRowIndex(child);
                if (rowIndex < deletedRowIndex) {
                    GridPane.setRowIndex(child, rowIndex + 1);
                }
            }
        }
    }

    public void runGame() {
        GameDriver timer = new GameDriver(this);
        timer.start();
    }
}
