package com.andrewchatch.tetris.UserInterface.game.shapes.types;

import com.andrewchatch.tetris.UserInterface.game.shapes.Brick;
import com.andrewchatch.tetris.UserInterface.game.shapes.Shape;

import javafx.scene.paint.Color;

enum Position {
    ONE,
    TWO
}

public class IShape extends Shape {
    Position pos;

    public IShape(Color color, int startX, int startY) {
        super(color, startX, startY);
        this.fillBricks();
        this.pos = Position.ONE;
    }

    protected void fillBricks() {
        bricks[1] = new Brick(this.color, this.startX - 1, this.startY);
        bricks[2] = new Brick(this.color, this.startX + 1, this.startY);
        bricks[3] = new Brick(this.color, this.startX + 2, this.startY);
    }
}
