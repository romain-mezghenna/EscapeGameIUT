package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.io.IOException;

/*
Classe réalisée par Dylan Pereira,
Elle a pour but d'ouvrir un inventaire lorsque le joueur clic sur le coussin.

NB : L'inventaire à été réalisé par Mathias De Busschere (cf la classe Inventory)
*/

public class Coussin extends Parent implements ElementInteractif{

    private final Inventory inventaire;
    private boolean etat;
    private final ImageView coussin;

    public Coussin(String chemin,double x,double y,double taille) {
        //Importation de l'inventaire (fait par Mathias)
        inventaire = new Inventory("Inventaire",5,75);
        inventaire.setTranslateX(800);inventaire.setTranslateY(200);
        inventaire.setVisible(false);

        //Importation du coussin
        this.coussin = new ImageView(new Image(SceneCellule.class.getResourceAsStream(chemin)));
        coussin.setPreserveRatio(true);
        coussin.setFitWidth(taille);
        coussin.setX(x);
        coussin.setY(y);
        this.getChildren().addAll(inventaire,coussin);

        //Initialisation etat (ouvert / fermer)
        this.etat = true;

        //Initialisation action lors du clic de la souris
        coussin.setOnMouseClicked(mouseEvent -> actionner());
    }

    //Fonction qui va choisir en fonction de son etat actuel d'ouvrir ou de fermer le coussin, et qui par la suite inversera son etat.
    public void actionner(){
        if(etat){
            ouvrir();
        }else{
            fermer();
        }
        etat=!etat;
    }

    //Fonction d'ouverture du coussin : Son ; affichage Inventaire ; déplacement du coussin
    public void ouvrir(){
        Clip sonOuvertureCoussin;
        try {
            sonOuvertureCoussin = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonOuvertureCoussin.wav"));
            sonOuvertureCoussin.open(inputStream);
            sonOuvertureCoussin.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        inventaire.setVisible(true);
        coussin.setX(Settings.screenwidth/2.95);coussin.setY(Settings.screenheight/1.96);
    }

    //Fonction de fermeture du coussin : Son ; désaffichage Inventaire ; redéplacement du coussin
    public void fermer(){
        Clip sonFermetureCoussin;
        try {
            sonFermetureCoussin = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonFermetureCoussin.wav"));
            sonFermetureCoussin.open(inputStream);
            sonFermetureCoussin.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        inventaire.setVisible(false);
        coussin.setX(Settings.screenwidth/2.46);coussin.setY(Settings.screenheight/3.85);
    }
}
