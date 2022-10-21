package com.andrewchatch.tetris.UserInterface;

import com.andrewchatch.tetris.UserInterface.menu.StartMenu;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class TetrisWindow {
    private final int WINDOW_WIDTH = 612; 
    private final int WINDOW_HEIGHT = 344; 
    StackPane root;
    private Scene scene;

    public TetrisWindow() {
        this.root = new StackPane();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        StartMenu menu = new StartMenu();


        root.getChildren().add(menu);
    }

    public Scene getScene() {
        return this.scene;
    }
}
