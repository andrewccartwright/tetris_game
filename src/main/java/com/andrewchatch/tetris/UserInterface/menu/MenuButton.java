package com.andrewchatch.tetris.UserInterface.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.andrewchatch.tetris.UserInterface.TetrisWindow;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuButton extends Button {
    final int DEPTH = 80;

    public MenuButton(String text, Color color, int size) {
        super(text);

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.rgb(0,65,138));
        borderGlow.setHeight(DEPTH);
        borderGlow.setWidth(DEPTH);
        this.setEffect(borderGlow);

        try {
            Font neonFont = Font.loadFont(new FileInputStream(new File(TetrisWindow.basePath + "/src/main/java/com/andrewchatch/tetris/assets/fonts/neon_pixel-7.ttf")), size);
            this.setFont(neonFont);
            // this.setTextFill();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        this.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), new Insets(0))));
    }

    public MenuButton(String text, int size) {
        super(text);

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.rgb(0,106,249));
        borderGlow.setHeight(DEPTH);
        borderGlow.setWidth(DEPTH);
        this.setEffect(borderGlow);

        try {
            Font neonFont = Font.loadFont(new FileInputStream(new File(TetrisWindow.basePath + "/src/main/java/com/andrewchatch/tetris/assets/fonts/neon_pixel-7.ttf")), size);
            this.setFont(neonFont);
            this.setTextFill(Color.rgb(27,2,163));
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        this.setBackground(new Background(new BackgroundFill(Color.rgb(188,1,254), new CornerRadii(3), new Insets(0))));
    }
}
