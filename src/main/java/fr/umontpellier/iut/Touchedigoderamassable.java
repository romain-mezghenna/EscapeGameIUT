package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;


public class Touchedigoderamassable extends Parent {
    private ImageView touche;

    public Touchedigoderamassable() {

        this.touche = new ImageView(new Image(Laverie.class.getResourceAsStream("/Images/bouton9matieu.png")));
        touche.setPreserveRatio(true);
        touche.setX(Screen.getPrimary().getBounds().getMaxY() * 0.167);
        touche.setY(Screen.getPrimary().getBounds().getMaxY() * 0.255);
        this.getChildren().add(touche);
        setOnMousePressed(mouseEvent -> ramasser());

    }


    public void ramasser() {
        //if(inventairemain.add(this))
        // {this.getChildren().remove(touche);}

    }
}