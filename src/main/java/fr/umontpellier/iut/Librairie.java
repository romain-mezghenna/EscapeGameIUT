
package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class Librairie extends Niveaux {
    private final double screenheight = Screen.getPrimary().getBounds().getHeight();
    private final double screenwidth  = Screen.getPrimary().getBounds().getWidth();

    public Librairie() {

        //met l'image en place
        ImageView background_scene = new ImageView(new Image(Librairie.class.getResourceAsStream("/bibliotheque.jpg")));
        background_scene.setFitWidth(screenwidth);
        background_scene.setFitHeight(screenheight);

        //met le livre sur la table
        Livre livre=new Livre(680,600,screenwidth/8);
        //création de la fleche de droite
        FlecheDirection flechedroite = new FlecheDirection(Settings.screenwidth*0.90,Settings.screenheight*0.50,270);
        flechedroite.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.librairie,Controller.hall));

        this.getChildren().addAll(background_scene,livre);
        this.getChildren().add(flechedroite);
    }



}