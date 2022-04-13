package fr.umontpellier.iut;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CheatCode extends Parent {

    public CheatCode() {
        //endGame button
        Button endGameButton = new Button();
        endGameButton.setText("End Game");
        endGameButton.setMinSize(25,25);
        endGameButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15), Insets.EMPTY)));
        endGameButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //gouache button
        Button gouacheButton = new Button();
        gouacheButton.setText("gouache button");
        gouacheButton.setMinSize(25,25);
        gouacheButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),Insets.EMPTY)));
        gouacheButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //prisonner Uniforme button
        Button uniformButton = new Button();
        uniformButton.setText("Uniforme prisonnier button");
        uniformButton.setMinSize(25,25);
        uniformButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),Insets.EMPTY)));
        uniformButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //guard uniforme button
        Button guardUniforme = new Button();
        guardUniforme.setText("Uniforme guard button");
        guardUniforme.setMinSize(25,25);
        guardUniforme.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),Insets.EMPTY)));
        guardUniforme.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //ordi game skip button
        Button ordiGameWinButton = new Button();
        ordiGameWinButton.setText("Insta win hack");
        ordiGameWinButton.setMinSize(25,25);
        ordiGameWinButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),Insets.EMPTY)));
        ordiGameWinButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //stuck reset button
        Button stuckResetButton = new Button();
        stuckResetButton.setText("hard reset");
        stuckResetButton.setMinSize(25,25);
        stuckResetButton.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(15),Insets.EMPTY)));
        stuckResetButton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //button grid
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.addColumn(0, endGameButton,gouacheButton,guardUniforme,uniformButton,ordiGameWinButton,stuckResetButton);
        buttonGridPane.setVgap(5);
        buttonGridPane.setTranslateX(5);
        buttonGridPane.setTranslateY(5);

        getChildren().add(buttonGridPane);

        //Effect for end game button
        endGameButton.setOnMouseClicked(mouseEvent -> Controller.endGame());

        //Effect for gouache button
        gouacheButton.setOnMouseClicked(mouseEvent -> {
            Gouache gouache = new Gouache("/images/gouache.png",150,500,500);
            getChildren().add(gouache);
        });

        //Effect for guard uniform button
        guardUniforme.setOnMouseClicked(mouseEvent -> {
            Uniforme uniforme = new Uniforme("/images/uniformeNoir.png",150,250,250, true);
            getChildren().add(uniforme);
        });

        //Effect for prison uniforme button
        uniformButton.setOnMouseClicked(mouseEvent -> {
            Uniforme uniforme = new Uniforme("/images/uniformeOrange.png",150,250,250, false);
            getChildren().add(uniforme);
        });

        //Effect for ordi game skip button
        ordiGameWinButton.setOnMouseClicked(mouseEvent -> Controller.portes.setOuverte());

        //Effect for hard reset button
        stuckResetButton.setOnMouseClicked(mouseEvent -> Controller.reset("Cheat Code hard reset"));

        setVisible(false);

    }
}
