package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FlecheDirection extends Parent {
    private double direction;

    public FlecheDirection(double x, double y, double angle) {
        ImageView fleche = new ImageView(new Image(FlecheDirection.class.getResourceAsStream("/images/flecheverte.png")));
        fleche.setRotate(angle);
        //0 bas
        //90 vers la gauche
        //180 pour vers le haut
        //270 pour vers la droite

        direction = angle;

        setTranslateX(x-fleche.getLayoutBounds().getWidth()/2);
        setTranslateY(y-fleche.getLayoutBounds().getHeight()/2);
        this.getChildren().add(fleche);
    }

}
