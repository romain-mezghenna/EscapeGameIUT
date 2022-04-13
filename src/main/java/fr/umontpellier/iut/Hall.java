package fr.umontpellier.iut;


import javafx.scene.Parent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class Hall extends Niveaux {

    private final double hauteur = Screen.getPrimary().getBounds().getHeight();
    private final double larguer  = Screen.getPrimary().getBounds().getWidth();

    public Hall() {

        ImageView hal = new ImageView(new Image(Hall.class.getResourceAsStream("/images/Hall.png")));
        hal.setFitWidth(larguer);hal.setFitHeight(hauteur);
        //création de la fleche du bas
        FlecheDirection flechebas = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.900,0);
        flechebas.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.hall,Controller.corridor));
        //création de la fleche du haut
        FlecheDirection flechehaut = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.60,180);
        flechehaut.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.hall,Controller.scenegarde));
        //création de la fleche de gauche
        FlecheDirection flechegauche = new FlecheDirection(Settings.screenwidth*0.100,Settings.screenheight*0.50,90);
        flechegauche.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.hall,Controller.librairie));
        //création de la fleche de droite
        FlecheDirection flechedroite = new FlecheDirection(Settings.screenwidth*0.90,Settings.screenheight*0.50,270);
        flechedroite.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.hall,Controller.laverie));
        this.getChildren().add(hal);
        this.getChildren().add(flechebas);
        this.getChildren().add(flechedroite);
        this.getChildren().add(flechehaut);
        this.getChildren().add(flechegauche);
    }
}