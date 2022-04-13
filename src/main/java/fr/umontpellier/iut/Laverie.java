package fr.umontpellier.iut;


import javafx.scene.Parent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class Laverie extends Niveaux   {

    private final double hauteur = Screen.getPrimary().getBounds().getHeight();
    private final double larguer  = Screen.getPrimary().getBounds().getWidth();

    public Laverie() {
        ImageView lav = new ImageView(new Image(Laverie.class.getResourceAsStream("/images/Laverie.jpg")));
        lav.setFitWidth(Settings.screenwidth);lav.setFitHeight(Settings.screenheight);
        Uniforme uniforme = new Uniforme("/images/uniforme.png",96,Settings.screenwidth*0.77,Settings.screenheight*0.324, false);
        ToucheDigicode toucheDigicode9 = new ToucheDigicode("/images/bouton9digicodeCasse.png",20,Settings.screenwidth*0.231,Settings.screenheight*0.777,9);
        Montreramassable montre = new Montreramassable();
        //création de la fleche
        FlecheDirection fleche = new FlecheDirection(Settings.screenwidth*0.1,Settings.screenheight*0.50,90);
        fleche.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.laverie,Controller.hall));

        this.getChildren().add(lav);
        getChildren().add(fleche);
        this.getChildren().add(montre);
        this.getChildren().add(uniforme);
        this.getChildren().add(toucheDigicode9);
    }
}