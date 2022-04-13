package fr.umontpellier.iut;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Credits extends Parent {
    private Text creditsText;

    public Credits() {
        creditsText = new Text("Adrien\n" +
                "Barnabé\n" +
                "Billal\n" +
                "Clément\n" +
                "Dylan\n" +
                "Evan\n" +
                "Gabriel\n" +
                "Gaston\n" +
                "Ismael\n" +
                "Johan\n" +
                "Kilian\n" +
                "Mathias\n" +
                "Mathieu\n" +
                "Romain\n" +
                "Shun\n" +
                "Thibault\n" +
                "Tom\n" +
                "William");
        creditsText.setFont(Font.font(50));
        creditsText.setFill(Color.WHITE);
        creditsText.setTextAlignment(TextAlignment.CENTER);
        creditsText.setTranslateY(-creditsText.getLayoutBounds().getHeight());

        StackPane credits = new StackPane();
        credits.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        credits.setPrefSize(Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
        credits.getChildren().addAll(creditsText);

        getChildren().add(credits);
    }

    public void creditsRoll() {
        Translate rollUp = new Translate(0,0);
        creditsText.getTransforms().add(rollUp);

        Timeline creditsRoll = new Timeline();
        creditsRoll.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(rollUp.yProperty(),creditsText.getLayoutBounds().getHeight()+ Screen.getPrimary().getBounds().getHeight())),
                new KeyFrame(Duration.seconds(30), new KeyValue(rollUp.yProperty(),0))
        );
        creditsRoll.setAutoReverse(false);
        creditsRoll.play();
    }
}
