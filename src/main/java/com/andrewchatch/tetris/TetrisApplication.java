package com.andrewchatch.tetris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrewchatch.tetris.UserInterface.TetrisWindow;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class TetrisApplication extends Application {
	public static void main(String[] args) {
		launch();
		SpringApplication.run(TetrisApplication.class, args);
	}

	@Override
	public void start(Stage stage) {
		TetrisWindow window = new TetrisWindow();
        stage.setTitle("Tetris Game");
		stage.setScene(window.getScene());
        stage.show();
    }
}
