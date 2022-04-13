package fr.umontpellier.iut;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Screen;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Optional;

public class Voiture extends Parent {
    private boolean ouverture;
    private boolean demarrer;
    private Label message=new Label("Cliquez sur la voiture.");

    public Voiture(){
        ImageView voiture =new ImageView(new Image(Voiture.class.getResourceAsStream("/Images/voiture.png")));

        voiture.setFitWidth(Screen.getPrimary().getBounds().getMaxX());
        voiture.setFitHeight(Screen.getPrimary().getBounds().getMaxY());
        ouverture = false;
        demarrer = false;
        message.setStyle("-fx-background-color: white");
        message.setStyle("-fx-border-color: black ");
        message.setStyle("-fx-font-size: 50px");

        this.getChildren().addAll(voiture/*,message*/);
        voiture.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof CleVoiture && dragEvent.getDragboard().hasContent(CleVoiture.cleVoitureDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(CleVoiture.cleVoitureDataFormat)) {
                fuite();
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

//        if (!ouverture){
//            this.setOnMousePressed(new EventHandler<MouseEvent>() {
//                public void handle(MouseEvent me){
//                    ouverture=true;
//                }
//                //On peut faire jouer un son si on a le temps
//
//            });}
//        if (ouverture){
//            this.setOnMousePressed(new EventHandler<MouseEvent>() {
//                public void handle(MouseEvent mouseEvent) {
//                    fuite();
//                }
//            });}
    }
    public void fuite(){
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
            exit.setTitle("La fin ??");
            exit.setHeaderText(null);
            exit.setContentText("Vous êtes sur le point d'allumer la voiture.\n" + "Voulez vous partir d'ici?");
        Optional<ButtonType> reponse = exit.showAndWait();

            if (reponse.get() == ButtonType.OK){
                Controller.endGame();
        }
            else {
            System.out.println("Il vous reste réelement des choses à faire ici ?");
     }
    }}

