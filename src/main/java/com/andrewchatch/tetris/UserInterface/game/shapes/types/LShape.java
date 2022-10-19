package com.andrewchatch.tetris.UserInterface.game.shapes.types;

import com.andrewchatch.tetris.UserInterface.game.shapes.Brick;
import com.andrewchatch.tetris.UserInterface.game.shapes.Shape;

import javafx.scene.paint.Color;

public class LShape extends Shape {
    public LShape(Color color, int startX, int startY) {
        super(color, startX, startY);
        this.fillBricks();
    }


    protected void fillBricks() {
        bricks[1] = new Brick(this.color, this.startX - 1, this.startY);
        bricks[2] = new Brick(this.color, this.startX, this.startY + 1);
        bricks[3] = new Brick(this.color, this.startX, this.startY + 2);
    } 
}
