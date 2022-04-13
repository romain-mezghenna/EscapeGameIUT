package fr.umontpellier.iut;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


public class Prisonnier extends Parent {

    private static int heureDebut;
    private static int heureFin;
    private int minutes;
    private static boolean firstRencontre = true;
    private static ImageView prisonnier;
    private static Group dialogueRencontreGroup;
    private static Horloge horloge;
    private static Group dialogueRejetCoussin;
    private static boolean closeFirstRencontre = false;
    public static boolean isHere = true;

    public Prisonnier(Horloge h, double tX, double tY){
        heureDebut =  (7);
        heureFin = (10);
        minutes = 0;
        horloge = h;


       dialogueRencontreGroup = new Group();
       dialogueRejetCoussin = new Group();


        prisonnier = new ImageView(new Image(Prisonnier.class.getResourceAsStream("/Images/prisonnier.png")));
        prisonnier.setFitHeight(400);
        prisonnier.setPreserveRatio(true);
        prisonnier.setRotate(prisonnier.getRotate() + 10);
        String textRencontre = "Aujourd'hui j'ai perdu mon livre intime...(la dernière fois que j'ai écris dedans c'était à la bibliothèque.";
        DialoguePrisonnier dialogueRencontre = new DialoguePrisonnier(0, 50, 140, 300, textRencontre);


        String textRejet = "Oh, tu fais quoi la ?!";
        DialoguePrisonnier dialogueRejet = new DialoguePrisonnier(0, 50, 140, 300, textRejet);
        Text oupsDialogue = new Text("Désolé.");
        oupsDialogue.setFont(new Font(25));
        oupsDialogue.setX(50);
        oupsDialogue.setY(180);

        //Close Dialogue
        oupsDialogue.setOnMouseClicked(mouseEvent -> dialogueRejetCoussin.setVisible(false));

        dialogueRejetCoussin.getChildren().add(dialogueRejet);
        dialogueRejetCoussin.getChildren().add(oupsDialogue);
        dialogueRejetCoussin.setVisible(false);

        Text okDialogue = new Text("Ok");
        okDialogue.setFont(new Font(25));
        okDialogue.setX(110);
        okDialogue.setY(180);

        //Close Dialogue Rencontre
        okDialogue.setOnMouseClicked(mouseEvent -> {
            dialogueRencontreGroup.setVisible(false);
            closeFirstRencontre = true;
            firstRencontre = false;
        });


        dialogueRencontreGroup.getChildren().add(dialogueRencontre);
        dialogueRencontreGroup.getChildren().add(okDialogue);


        this.getChildren().add(dialogueRejetCoussin);
        this.getChildren().add(dialogueRencontreGroup);
        this.getChildren().add(prisonnier);
        this.setTranslateX(tX);
        this.setTranslateY(tY);

    }
    //Fait apparaitre ou non le prisonnier
    public static void estPresent() {
        if(horloge.getHeures() >= heureDebut && horloge.getHeures() < heureFin){
            prisonnier.setVisible(false);
            isHere = false;
        }else{
            if(firstRencontre){
                dialogueRencontreGroup.setVisible(true);
                firstRencontre = false;
            }
            prisonnier.setVisible(true);
            isHere = true;
        }
    }

    //Affiche le dialogue du rejet
    public static void showRejetDialogue(){

        if(!closeFirstRencontre){
            dialogueRejetCoussin.setVisible(true);
            dialogueRencontreGroup.setVisible(false);
        }else{
            dialogueRejetCoussin.setVisible(true);
        }

    }



}
