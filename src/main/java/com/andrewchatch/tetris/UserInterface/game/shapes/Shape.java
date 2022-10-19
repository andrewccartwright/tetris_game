package com.andrewchatch.tetris.UserInterface.game.shapes;

import com.andrewchatch.tetris.UserInterface.game.GameBoard;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Brick[] bricks = new Brick[4];
    protected Color color = Color.BLUE;
    protected int startX;
    protected int startY;
    protected Color[] colors = {Color.BLUE, Color.YELLOW, Color.CYAN, Color.PURPLE, Color.GREEN, Color.SALMON, Color.RED};
    protected boolean isMoving = true;
    protected boolean isReversed = false;

    public Shape(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.bricks[0] = new Brick(this.color, this.startX, this.startY);
    }

    public Shape(Color color, int startX, int startY) {
        this.color = color;
        this.startX = startX;
        this.startY = startY;
        this.bricks[0] = new Brick(this.color, this.startX, this.startY);
    }

    protected abstract void fillBricks();

    public void addBricks(GameBoard board) {

        for (int i = 0; i < bricks.length; i++) {
            int x = bricks[i].getXPos();
            int y = bricks[i].getYPos();

            board.add(bricks[i], x, y);
            GridPane.setHalignment(bricks[i], HPos.CENTER);
        }
    }

    public Brick[] getBricks() {
        return this.bricks;
    }

    public boolean getIsMoving() {
        return this.isMoving;
    }

    public void moveDown() {
        if (!checkVerticalCollision()) {
            for (int i = 0; i < this.bricks.length; i++) {
                GridPane.setRowIndex(this.bricks[i], (int) (this.bricks[i].getY() + 1));
                this.bricks[i].setY(this.bricks[i].getY() + 1);
                if (this.bricks[i].getY() == GameBoard.GRID_HEIGHT) {
                    this.isMoving = false;
                }
            }
        }
    }

    public void moveFast() {
        do {
            this.moveDown();
        }
        while (!checkVerticalCollision());
    }

    private boolean checkVerticalCollision() {
        boolean collided = false;

        for (int i = 0; i < this.bricks.length; i++) {
            int row = GridPane.getRowIndex(bricks[i]) + 1;
            int col = GridPane.getColumnIndex(bricks[i]);

            if (row < 0 || row >= GameBoard.GRID_HEIGHT) {
                this.isMoving = false;
                collided = true;
            }
            else if (compareToChildren(bricks[i], row, col)) {
                this.isMoving = false;
                collided = true;
            }

        }
        return collided;
    }

    private boolean findInArray(Brick brick) {
        for (int i = 0; i < this.bricks.length; i++) {
            if (this.bricks[i] == brick) {
                return true;
            }
        }
        return false;
    }

    public void moveLeft() {
        if (!checkHorizontalCollision(-1)) {
            for (int i = 0; i < bricks.length; i++) {
                GridPane.setColumnIndex(bricks[i], (int) (bricks[i].getX() - 1));
                bricks[i].setX(bricks[i].getX() - 1);
            }
        }
    }

    public void moveRight() {
        if (!checkHorizontalCollision(1)) {
            for (int i = 0; i < bricks.length; i++) {
                GridPane.setColumnIndex(bricks[i], (int) (bricks[i].getX() + 1));
                bricks[i].setX(bricks[i].getX() + 1);
            }
        }
    }

    private boolean checkHorizontalCollision(int direction) {
        boolean collision = false;

        for (int i = 0; i < bricks.length; i++) {
            int row = GridPane.getRowIndex(this.bricks[i]);
            int col = GridPane.getColumnIndex(this.bricks[i]) + 1 * direction;

            if (col < 0 || col >= GameBoard.GRID_WIDTH) {
                collision = true;
            }
            else if (compareToChildren(bricks[i], row, col)) {
                collision = true;
            }
        }

        return collision;
    }

    public boolean compareToChildren(Brick b, int row, int col) {
        ObservableList<Node> children = b.getParent().getChildrenUnmodifiable();

        for (Node child: children) {
            if ((GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == col) && (b.getClass() == child.getClass() && !findInArray((Brick) child))) {
                return true;
            }
        }

        return false;
    }

    public void rotate()
    {
        int tempX = 0;
        int tempY = 0;
        int temp = 0;

        if (checkRotation()) {
            if (!isReversed) {
                for(int i = 0; i < 4; i++)
                {
        
                    tempX = bricks[i].getXPos() - bricks[0].getXPos();
                    tempY = bricks[i].getYPos() - bricks[0].getYPos();
        
                    temp = tempX;
                    tempX = -tempY;
                    tempY = temp;
    
                    int newX = bricks[0].getXPos() + tempX;
                    int newY = bricks[0].getYPos() + tempY;
    
                    GridPane.setColumnIndex(bricks[i], newX);
                    GridPane.setRowIndex(bricks[i], newY);
                    bricks[i].setXPos(newX);
                    bricks[i].setYPos(newY);
                }
                isReversed = !isReversed;
            }
            else {
                for(int i = 0; i < 4; i++)
                {
        
                    tempX = bricks[i].getXPos() - bricks[0].getXPos();
                    tempY = bricks[i].getYPos() - bricks[0].getYPos();
        
                    temp = tempX;
                    tempX = -tempY;
                    tempY = temp;

                    int newX = bricks[0].getXPos() + tempX;
                    int newY = bricks[0].getYPos() + tempY;

                    GridPane.setColumnIndex(bricks[i], newX);
                    GridPane.setRowIndex(bricks[i], newY);
                    bricks[i].setXPos(newX);
                    bricks[i].setYPos(newY);
                }
                isReversed = !isReversed;
            }
        }
    }

    private boolean checkRotation() {
        boolean canRotate = true;

        for (int i = 0; i < this.bricks.length; i++) {
            int tempX = bricks[i].getXPos() - bricks[0].getXPos();
            int tempY = bricks[i].getYPos() - bricks[0].getYPos();
            int temp = tempX;
            tempX = - tempY;
            tempY = temp;

            int x = bricks[i].getXPos() + tempX;
            int y = bricks[i].getYPos() + tempY;

            if (x < 0 || x >= GameBoard.GRID_WIDTH || y < 0 || y >= GameBoard.GRID_HEIGHT) {
                canRotate = false;
            }
        }

        return canRotate;
    }

    public boolean checkGameOver() {
        for (int i = 0; i < this.bricks.length; i++) {
            int row = GridPane.getRowIndex(bricks[i]);
            int col = GridPane.getColumnIndex(bricks[i]);
            if (row == 0 && compareToChildren(bricks[i], row, col)) {
                return true;
            }
        }

        return false;
    }
}
