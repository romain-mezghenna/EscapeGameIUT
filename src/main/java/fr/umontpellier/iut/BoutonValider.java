package fr.umontpellier.iut;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.awt.*;
import java.util.ArrayList;

public class BoutonValider extends Parent {
    private boolean valideBool;
    private int essai=1;
    public BoutonValider(){
        String lettre="Valider";
        Label validerLabel=new Label(lettre);
        Rectangle validRectangle=new Rectangle(20,50,Color.WHITE);
        //valider.setStyle("-fx-background-color:WAIT");
        validerLabel.setTranslateX(Screen.getPrimary().getBounds().getMaxX()* 0.2);
        validerLabel.setTranslateY(Screen.getPrimary().getBounds().getMaxY()*0.065);
        //validerLabel.setTranslateY(270);
        //validerLabel.setTranslateX(50);
        validRectangle.setHeight(20);
        validRectangle.setWidth(50);
        validRectangle.setX(270);
        validRectangle.setY(50);
        this.getChildren().addAll(validRectangle);
        this.getChildren().add(validerLabel);

        this.setOnMousePressed(me -> {
            valideBool=true;
            validRectangle.setFill(Color.GREEN);
            appuyer();
        });

    }

    public boolean isValideBool() {
        return valideBool;
    }
    public boolean verif(){///ici on implementerais l'arrayList correcte
        ArrayList<Integer> correct=new ArrayList<>();
        correct.add(6);
        correct.add(8);
        correct.add(9);
        correct.add(11);
        correct.add(16);
        correct.add(18);
        return DigiCodeSchema.verifierCode(correct);
    }
    public void appuyer() {
        if (verif()) {
            Image parckingOuvertImage = new Image(getClass().getResourceAsStream("/Images/ParkingCoffreOuvert.png"));
            ImageView parckingOuvertView = new ImageView(parckingOuvertImage);
            Voiture voiture = new Voiture();
            Label message = new Label("Coffre debloqué");
            message.setTextFill(Color.WHITE);
            message.setStyle("-fx-font-size: 200%");
            message.setTranslateY(Screen.getPrimary().getBounds().getMaxY() *0.30);
            message.setTranslateX(Screen.getPrimary().getBounds().getMaxX() *0.31);
            parckingOuvertView.setFitWidth(Screen.getPrimary().getBounds().getMaxX());
            parckingOuvertView.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
            CleVoiture cleVoiture= new CleVoiture( "/Images/cleVoiture.png",40,Settings.screenwidth*0.7457,Settings.screenheight*0.7);
            this.getChildren().addAll(parckingOuvertView,voiture,message,cleVoiture);
        }
        else {//S'il ce trompe alors le jeux recomence ICI FONCTION RESET
            Controller.reset("Ce n'est pas le bon code, vous avez déclenché l'alarme!");
            /*Label messagePerdu=new Label("Ils t'ont chopper, Retourne en cellule");//a voir si changer ou pas
                Rectangle fond=new Rectangle();
                fond.setWidth(Screen.getPrimary().getBounds().getMaxX());
                fond.setHeight(Screen.getPrimary().getBounds().getMaxY() - 60);
                messagePerdu.setStyle("-fx-border-color: White ");
                messagePerdu.setStyle("-fx-font-size: 50px");

                fond.setFill(Color.WHITE);
                this.getChildren().addAll(fond,messagePerdu);*/
            }
        }
}

