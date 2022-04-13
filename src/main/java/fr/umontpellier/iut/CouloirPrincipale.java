package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CouloirPrincipale extends Niveaux {

    public CouloirPrincipale() {
        ImageView lav = new ImageView(new Image(CouloirPrincipale.class.getResourceAsStream("/images/Couloir.png")));
        lav.setFitWidth(Settings.screenwidth);lav.setFitHeight(Settings.screenheight);
        this.getChildren().add(lav);

        //création des fleches
        FlecheDirection flechegauche = new FlecheDirection(Settings.screenwidth*0.1,Settings.screenheight/2,90); //fleche qui revient à la cellule de base
        flechegauche.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.corridor,Controller.cell1));
        getChildren().add(flechegauche);

        FlecheDirection flechedroite = new FlecheDirection(Settings.screenwidth*0.90,Settings.screenheight/2,270); //fleche qui vas a la cellule du prisonnier
        flechedroite.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.corridor,Controller.sceneCellulePrisonnier));
        getChildren().add(flechedroite);
        //création de la fleche du haut
        FlecheDirection flechehaut = new FlecheDirection(Settings.screenwidth/2,Settings.screenheight*0.54,180);
        flechehaut.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.corridor,Controller.hall));
        this.getChildren().add(flechehaut);

    }
}
