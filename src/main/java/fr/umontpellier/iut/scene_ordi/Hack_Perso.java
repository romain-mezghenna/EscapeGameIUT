package fr.umontpellier.iut.scene_ordi;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Hack_Perso extends Parent {

    // position du perso
    double posx;
    double posy;

    // taille du perso
    int width;
    int height;

    // vitesse du perso
    private double vitesse;

    // direction du perso
    private boolean vadroite;
    private boolean vagauche;
    private boolean vahaut;
    private boolean vabas;

    // private int nbmort = 0;          // ne sert potentiellement a rien (en reflexion)

    // corp du perso (ce qui va tre visible)
    ImageView corp;

    public Hack_Perso(){

        // initialisation place, taille, vitesse de base
        width = 20;
        height = 20;
        posx = 100;
        posy = 30 + (542-(double)height)/2 +2;
        vitesse = 2;

        // initialisation de la direction
        vadroite = false;
        vagauche = false;
        vahaut = false;
        vabas = false;

        // corp du perso
        corp = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/perso-hack.png")));
        setTranslateX(posx);
        setTranslateY(posy);
        getChildren().add(corp);



        ////////////////////////////// event clavier

        // quand on appuie sur une touche
        this.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.D){
                vadroite = true;
                //vagauche = false;                     // en commentaire car 2 modes potentiel de deplacement
                //vabas = false;                        // soit deplacement normal (actuel)
                //vahaut = false;                       // soit deplacement auto et on change juste la direction (augmente drastiquement la difficulté)
            }
            if(ke.getCode() == KeyCode.Q){
                vagauche = true;
                //vadroite = false;
                //vabas = false;
                //vahaut = false;
            }
            if(ke.getCode() == KeyCode.Z){
                vahaut = true;
                //vabas = false;
                //vagauche = false;
                //vadroite = false;
            }
            if(ke.getCode() == KeyCode.S){
                vabas = true;
                //vahaut = false;
                //vagauche = false;
                //vadroite = false;
            }
        });

        // quand on relache une touche (partie a enlevé si deplacement auto choisi)
        this.setOnKeyReleased(ke -> {
            if(ke.getCode() == KeyCode.D){
                vadroite = false;
            }
            if(ke.getCode() == KeyCode.Q){
                vagauche = false;
            }
            if(ke.getCode() == KeyCode.Z){
                vahaut = false;
            }
            if(ke.getCode() == KeyCode.S){
                vabas = false;
            }
        });



        // Animation (verification constante des conditions qui l'a composent)
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now)
            {

                // arret du perso quand touche les bordures de la fenetre
                if (posx >= 914-width){
                    vadroite = false;
                }
                if (posx <= 0){
                    vagauche = false;
                }
                if (posy <= 30){
                    vahaut = false;
                }
                if (posy >= 542-height+30){
                    vabas = false;
                }

                // deplacement
                if (vadroite){
                    droite(vitesse);
                }
                if (vagauche){
                    gauche(vitesse);
                }
                if (vahaut){
                    haut(vitesse);
                }
                if (vabas){
                    bas(vitesse);
                }
            }
        };
        timer.start();


    }

    // méthode de deplacement

    public void droite(double nb){
        posx = posx + nb;
        this.setTranslateX(posx);
    }

    public void gauche(double nb){
        posx = posx - nb;
        this.setTranslateX(posx);
    }

    public void haut(double nb){
        posy = posy - nb;
        this.setTranslateY(posy);
    }

    public void bas(double nb){
        posy = posy + nb;
        this.setTranslateY(posy);
    }


    // retour a la position / taille / vitesse du perso
    public void reset(){
        posx = 100;
        posy = 30 + (542-(double)height)/2 +2;
        width = 20;
        height = 20;
        vitesse = 2;

        setTranslateX(posx);
        setTranslateY(posy);
    }

    // setter / getter

    // utile si nb de vie limité
/*
    public int getNbmort() {
        return nbmort;
    }
    public void setNbmort(int nbmort) {
        this.nbmort = nbmort;
    }
 */
}
