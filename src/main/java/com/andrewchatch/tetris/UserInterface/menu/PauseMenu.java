package com.andrewchatch.tetris.UserInterface.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PauseMenu extends StackPane {
    private MenuButton continueButton;
    private MenuButton helpButton;
    private MenuButton exitButton;

    public PauseMenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        this.continueButton = new MenuButton("Continue", Color.rgb(248,247,0), 36);
        this.helpButton = new MenuButton("Help", Color.rgb(248,247,0), 36);
        this.exitButton = new MenuButton("Exit", Color.rgb(248,247,0), 36);

        box.getChildren().addAll(continueButton, helpButton, exitButton);
        box.setSpacing(30);
        // continueButton.setOnAction(unpause);

        this.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.4), new CornerRadii(0), new Insets(0))));
        this.getChildren().add(box);
    }

    public MenuButton getContinueButton() {
        return this.continueButton;
    }

    public MenuButton getHelpButton() {
        return this.helpButton;
    }

    public MenuButton getExitButton() {
        return this.exitButton;
    }
}
