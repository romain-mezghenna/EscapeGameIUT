package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class Pause extends Parent {
    public Pause() {
        //Pause visual
        StackPane pauseStackPane = new StackPane();
        pauseStackPane.setPrefSize(Screen.getPrimary().getBounds().getMaxX(),Screen.getPrimary().getBounds().getMaxY());

        Text pauseText = new Text("PAUSE");
        pauseText.setFill(Color.RED);
        pauseText.setFont(Font.font(99));
        pauseStackPane.getChildren().add(pauseText);
        getChildren().add(pauseStackPane);
    }
}
