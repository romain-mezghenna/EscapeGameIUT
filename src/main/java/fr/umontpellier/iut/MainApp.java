package fr.umontpellier.iut;

import fr.umontpellier.iut.digicode.InteractionSceneDigicode;
import fr.umontpellier.iut.scene_ordi.SuperSceneOrdi;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage){

		primaryStage.setTitle("EscapeGame");
		Group root = new Group();
		Scene scene = new Scene(root, 800, 800);
		Controller.group.requestFocus();

		Controller.group.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ESCAPE) {
				Controller.pause();
			}
		});

		Controller.group.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.F5) {
				Controller.cheatCode.setVisible(!Controller.cheatCode.isVisible());
			}
		});

		scene.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.F11) {
				primaryStage.setFullScreen(!primaryStage.isFullScreen());
			}
		});

		primaryStage.fullScreenExitHintProperty().setValue("Press F11 to exit FullScreen.");
		primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.SHIFT_DOWN));

		Controller.group.getChildren().add(Controller.startMenu);
		root.getChildren().add(Controller.group);

		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();

	}
}