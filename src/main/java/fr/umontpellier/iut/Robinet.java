package fr.umontpellier.iut;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.IOException;

/*
Classe réalisée par Dylan Pereira,
Elle a pour but de rendre actionnable les robinets :
        - Image du robinet
        - Son Ouverture / Fermeture
        - Animation de rotation des robinets
        - Image eau qui coule du robinet correspondant
        - Son EauCoulante lorsque un robinet est ouvert

*/

public class Robinet extends Parent implements ElementInteractif{

    private boolean etat;
    private final ImageView animationEau;

    private final RotateTransition rotation;
    private Clip sonEauCoulante;

    public Robinet(String cheminImageRobinet,String cheminAnimationEau, double x,double y,int taille) {
        //Initialisation variable
        this.etat = true;

        //Positionnement / Dimensionnement du robinet
        ImageView robinet = new ImageView(new Image(SceneCellule.class.getResourceAsStream(cheminImageRobinet)));
        robinet.setPreserveRatio(true);
        robinet.setFitWidth(taille);
        robinet.setX(x);
        robinet.setY(y);

        //this.getChildren().add(robinet);
        animationEau = new ImageView(new Image(SceneCellule.class.getResourceAsStream(cheminAnimationEau)));
        animationEau.setFitWidth(Settings.screenwidth);animationEau.setFitHeight(Settings.screenheight);
        animationEau.setVisible(false);

        this.getChildren().addAll(robinet, animationEau);

        //Initialisation de l'animation de rotation
        this.rotation = new RotateTransition(Duration.millis(1000), robinet);
        rotation.setDuration(Duration.millis(500));
        rotation.setInterpolator(Interpolator.LINEAR);

        //Initialisation du sonEauCoulante
        try {
            sonEauCoulante = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonEauCoulante.wav"));
            sonEauCoulante.open(inputStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        //Initialisation action lors du clic de la souris
        setOnMouseClicked(mouseEvent -> actionner());
    }

    //Getter de l'état (Ouvert / Femer) du robinet
    public boolean getEtat() {
        return !etat;
    }

    //Fonction qui va choisir en fonction de son etat actuel d'ouvrir ou de fermer le robinet, et qui par la suite inversera son etat.
    public void actionner(){
        if(etat) ouvrir();
        else fermer();
        etat=!etat;
    }

    //Fonction d'ouverture du robinet : Son EauCoulante ; Son OuvertureRobinet ; Animation de rotation ; Apparition de l'eau qui coule (Visuel)
    public void ouvrir (){
        sonEauCoulante.start();
        sonEauCoulante.loop(sonEauCoulante.LOOP_CONTINUOUSLY);
        Clip sonOuvertureRobinet;
        try {
            sonOuvertureRobinet = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonOuvertureRobinet.wav"));
            sonOuvertureRobinet.open(inputStream);
            sonOuvertureRobinet.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        rotation.setByAngle(180);rotation.play();
        animationEau.setVisible(true);
    }

    //Fonction de fermeture du robinet : Arret Son EauCoulante ; Son FermetureRobinet ; Animation de rotation sens inverse ; Disparition de l'eau qui coule (Visuel)
    public void fermer(){
        sonEauCoulante.stop();
        Clip sonFermetureRobinet;
        try {
            sonFermetureRobinet = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonFermetureRobinet.wav"));
            sonFermetureRobinet.open(inputStream);
            sonFermetureRobinet.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        rotation.setByAngle(-180);rotation.play();
        animationEau.setVisible(false);
    }
}
