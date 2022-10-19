package com.andrewchatch.tetris.UserInterface.game.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    public static final int BRICK_SIZE = 25;

    public Brick(Color fill, int xPos, int yPos) {
        this.setFill(fill);
        this.setHeight(BRICK_SIZE);
        this.setWidth(BRICK_SIZE);
        this.setStroke(Color.WHITE);
        this.setX(xPos);
        this.setY(yPos);
    }

    public void setPosition(int xPos, int yPos) {
        this.setX(xPos);
        this.setY(yPos);
    }

    public int getSize() {
        return Brick.BRICK_SIZE;
    }

    public int getXPos() {
        return (int) this.getX();
    }

    public int getYPos() {
        return (int) this.getY();
    }

    public void setXPos(int xPos) {
        this.setX(xPos);
    }
    
    public void setYPos(int yPos) {
        this.setY(yPos);
    }
}
