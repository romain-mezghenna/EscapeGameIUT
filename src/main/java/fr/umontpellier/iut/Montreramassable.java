package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;


public class Montreramassable extends Parent {
    private ImageView touche;

    public Montreramassable() {

        this.touche = new ImageView(new Image(Laverie.class.getResourceAsStream("/Images/Montreramassable.png")));
        touche.setPreserveRatio(true);
        touche.setX(Settings.screenwidth * 0.005);
        touche.setY(Settings.screenheight * 0.555);
        this.getChildren().add(touche);

        setOnMousePressed(mouseEvent -> ramasser(Controller.montre));

    }

    public void ramasser(Montre m) {
        m.findMontre();
        this.getChildren().remove(touche);

    }
}