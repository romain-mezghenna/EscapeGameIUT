package fr.umontpellier.iut;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import org.w3c.dom.Text;

import java.awt.*;


public class Parcking extends Niveaux {
    public Parcking() {
        Image parck = new Image(Parcking.class.getResourceAsStream("/Images/Parking.png"));
        Image parkCoffreOuvert = new Image(Parcking.class.getResourceAsStream("/Images/ParkingCoffreOuvert.png"));

        ImageView parcking = new ImageView(parck);
        //création de la fleche du bas
        FlecheDirection flechebas = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.90,0);
        flechebas.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.parking,Controller.portes));
        parcking.setFitWidth(Screen.getPrimary().getBounds().getMaxX());
        parcking.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
        Casier casier=new Casier();
        this.getChildren().addAll(parcking,casier);
        this.getChildren().add(flechebas);
        //e



    }
}
