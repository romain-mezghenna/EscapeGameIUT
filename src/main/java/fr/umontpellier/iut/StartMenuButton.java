package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartMenuButton extends Parent {
    private Rectangle keyBackgroung;
    private Text keyContent;

    public StartMenuButton(String text) {
        keyContent = new Text(text);
        keyContent.setTextAlignment(TextAlignment.CENTER);
        keyContent.setFont(Font.font("Agency FB",70));

        keyBackgroung = new Rectangle(keyContent.getLayoutBounds().getWidth()+100,keyContent.getLayoutBounds().getHeight()+10, Color.rgb(255,255,255,0.5));
        keyBackgroung.setArcWidth(10);
        keyBackgroung.setArcHeight(10);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(keyBackgroung,keyContent);

        setOnMouseEntered(mouseEvent -> keyBackgroung.setFill(Color.rgb(125,125,125,0.5)));
        setOnMouseExited(mouseEvent -> keyBackgroung.setFill(Color.rgb(255,255,255,0.5)));
        setOnMousePressed(mouseEvent -> click());
        setOnMouseReleased(mouseEvent -> release());

        getChildren().add(stackPane);
    }

    public void click() {
        setTranslateY(2);
        keyBackgroung.setFill(Color.rgb(55,55,55,0.5));
    }

    public void release() {
        setTranslateY(-2);
        keyBackgroung.setFill(Color.rgb(255,255,255,0.5));
    }
}
