package fr.umontpellier.iut;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class StartMenu extends Niveaux {
    private ImageView menuBackground;
    private TilePane menuWhiteOverlay;

    public StartMenu() {
        //Define menu background image
        menuBackground = new ImageView(new Image(getClass().getResourceAsStream("/images/BackgroundCellMenu.png")));
        menuBackground.setFitWidth(Screen.getPrimary().getBounds().getWidth());
        menuBackground.setFitHeight(Screen.getPrimary().getBounds().getHeight());

        //Define menu overlay
        menuWhiteOverlay = new TilePane();
        menuWhiteOverlay.setPrefWidth(Screen.getPrimary().getBounds().getMaxX()/2-200);
        menuWhiteOverlay.setPrefHeight(Screen.getPrimary().getBounds().getMaxY());
        menuWhiteOverlay.setTranslateX(200);
        menuWhiteOverlay.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255,0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        menuWhiteOverlay.setPrefColumns(1);
        menuWhiteOverlay.setVgap(10);
        menuWhiteOverlay.setAlignment(Pos.CENTER);
        menuWhiteOverlay.setPrefRows(8);

        //Define game name for menu
        Text gameTitle = new Text("Liberté pour les S5");
        gameTitle.setFill(Color.RED);
        gameTitle.setFont(Font.font("Old English Text MT", FontWeight.SEMI_BOLD, 80));

        //Define Menu button
        StartMenuButton startGameButton = new StartMenuButton("Start game");
        StartMenuButton scoreButton = new StartMenuButton("ScoreFile");
        StartMenuButton creditsButton = new StartMenuButton("Credits");
        StartMenuButton exitGameButton = new StartMenuButton("Exit Game");

        //Empty text for space between title and button
        Text emptySpace = new Text();

        //Add all button to white overlay
        menuWhiteOverlay.getChildren().add(gameTitle);
        menuWhiteOverlay.getChildren().add(emptySpace);
        menuWhiteOverlay.getChildren().add(startGameButton);
        menuWhiteOverlay.getChildren().add(scoreButton);
        menuWhiteOverlay.getChildren().add(creditsButton);
        menuWhiteOverlay.getChildren().add(exitGameButton);

        //Effect for start game button
        startGameButton.setOnMouseClicked(mouseEvent -> Controller.startGame(this));

        //Effect for score buttton
        scoreButton.setOnMouseClicked(mouseEvent -> {
            menuShowHide();
            getChildren().add(Controller.scoreFile);
            Controller.scoreFile.display();
            Controller.scoreFile.setOnMouseClicked(mouseEvent1 -> {
                getChildren().remove(Controller.scoreFile);
                Controller.scoreFile.stopDisplay();
                menuShowHide();
            });
        });

        //Effect for credits Button
        creditsButton.setOnMouseClicked(mouseEvent -> {
            Credits creditsScene = new Credits();
            menuShowHide();
            getChildren().add(creditsScene);
            creditsScene.creditsRoll();
            creditsScene.setOnMouseClicked(mouseEvent1 -> {
                getChildren().remove(creditsScene);
                menuShowHide();
            });
        });

        //Effect for exit game button
        exitGameButton.setOnMouseClicked(mouseEvent -> Platform.exit());

        getChildren().add(menuBackground);
        getChildren().add(menuWhiteOverlay);
    }

    public void menuShowHide() {
        if (getChildren().contains(menuWhiteOverlay)) {
            getChildren().remove(menuWhiteOverlay);
        }
        else
            getChildren().add(menuWhiteOverlay);
    }
}
