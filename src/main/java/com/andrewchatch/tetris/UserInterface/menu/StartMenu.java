package com.andrewchatch.tetris.UserInterface.menu;

import com.andrewchatch.tetris.GameLogic.FlowController;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StartMenu extends StackPane {
    // private StackPane menu = new StackPane();
    private final int START_SIZE = 38;
    private final int EXIT_SIZE = 28;

    public StartMenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        Button startButton = new MenuButton("Start", Color.rgb(248,247,0), START_SIZE);
        // Button optionsButton = new MenuButton("Options", 32);
        Button exitButton = new MenuButton("Exit", Color.rgb(2,247,5), EXIT_SIZE);

        startButton.setOnAction(FlowController.startGame);
        // optionsButton.setOnAction(FlowController.settings);
        exitButton.setOnAction(FlowController.exitGame);

        box.getChildren().add(startButton);
        // box.getChildren().add(optionsButton);
        box.getChildren().add(exitButton);

        box.setSpacing(30);

        Image image = new Image(getClass().getResourceAsStream("/images/start_menu_background.jpeg"), 612, 344, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        Background background = new Background(backgroundImage);
        this.setBackground(background);

        // try {
            
        // }
        // catch (FileNotFoundException e) {
        //     System.out.println(e);
        //     this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
        // }

        this.getChildren().add(box);
    }
}
