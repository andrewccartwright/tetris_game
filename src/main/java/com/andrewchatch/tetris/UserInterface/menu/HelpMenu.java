package com.andrewchatch.tetris.UserInterface.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HelpMenu extends StackPane {
    private Button exit;

    public HelpMenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(0))));
        box.setPrefSize(600,500);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Text title = new Text("How to Play");
        title.setFont(new Font(36));
        Text body = new Text(fillText());
        body.setFont(new Font(24));

        this.exit = new Button("X");
        exit.setTextFill(Color.RED);
        exit.setTranslateX(270);
        exit.setTranslateY(-30);
        box.getChildren().addAll(title, exit, body);

        this.getChildren().add(box);
    }

    public String fillText() {
        String text = "Controls:\nUse left and right arrow keys to move horizontally.\nThe up arrow key will rotate the current shape.\nThe down arrow key will make the shape move faster.\nSpacebar will move the shape all the way down.";
        return text;
    }

    public Button getExitButton() {
        return this.exit;
    }
}
