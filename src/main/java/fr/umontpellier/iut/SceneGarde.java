package fr.umontpellier.iut;

import fr.umontpellier.iut.digicode.InteractionSceneDigicode;
import fr.umontpellier.iut.digicode.SceneDigicode;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.HashMap;


public class SceneGarde extends Niveaux {
    private static Timer timer = new Timer(15);
    private static boolean lancer=false;
    private static boolean dedans=false;


    private double screenheight, screenwidth;
    private boolean garde = true;
    private int etape = 1;
    private ImageView background_scene, digicode;
    private boolean digiCracked = false;
    /*private FlecheDirection avancer, retour;*/

    public SceneGarde() {
        // Get the screen dimensions and assign them to variables
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        screenheight = dimension.getHeight();
        screenwidth  = dimension.getWidth();

        // Create an image view of the background
        digicode = new ImageView(
                new Image(SceneGarde.class.getResourceAsStream("/images/digicode_grille.png"))
        );
        digicode.setOnMousePressed(mouseEvent -> {
            if(!garde)
            {Controller.transitions(Controller.scenegarde,Controller.digicodechiffre);
            SceneDigicode.notifierLeJoueur("Appuyez sur I ou H pour afficher les raccourcies clavier");
            Controller.digicodechiffre.requestFocus();
            }});
        //création de la fleche du bas
        FlecheDirection flechebas = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.90,0);
        flechebas.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.scenegarde,Controller.hall));
        //création de la fleche du haut
        FlecheDirection flechehaut = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.70,180);
        flechehaut.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.scenegarde,Controller.portes));
        flechehaut.setVisible(false);

        // Default background image
        background_scene = new ImageView(
                new Image(SceneGarde.class.getResourceAsStream("/images/fond_garde.png"))
        );


        // Make the image the size of the screen
        background_scene.setFitHeight(screenheight);
        background_scene.setFitWidth(screenwidth);


        // Store the texts for the dialogue and buttons
        HashMap<Integer, String> reponsesGardes = new HashMap<>();
        reponsesGardes.put(-1, "Ne me fais pas perdre mon temps");
        reponsesGardes.put(1, "Je ne te reconnais pas");
        reponsesGardes.put(-2, "Je connais tous les anciens je suis là depuis 40 ans retourne en cellule");
        reponsesGardes.put(2, "Je ne savais pas qu'on recrutait");
        reponsesGardes.put(-3, "J'aurais été mis au courant");
        reponsesGardes.put(3, "Le secrétariat aurait pu me prévenir...");
        reponsesGardes.put(-4, "Tu le saurais si tu été vraiment garde, bouge de là");
        reponsesGardes.put(4, "Ce n'est pas la première fois, ils m'ont fait travailler un 19 Avril une fois," +
                "c'est l'anniversaire de ma fille bon sang...");


        HashMap<Integer, String> msgBouton1 = new HashMap<>();
        msgBouton1.put(2, "Je suis nouveau");
        msgBouton1.put(3, "J'ai postulé il y a 3 jours, c'était rapide");
        msgBouton1.put(4, "Il ne vous respecte pas trop");


        HashMap<Integer, String> msgBouton2 = new HashMap<>();
        msgBouton2.put(2, "J'étais en congé");
        msgBouton2.put(3, "J'ai été embauché il y a 2 mois, j'avais des problèmes de santé");
        msgBouton2.put(4, "Vous êtes un chef ?");


        // 15s timer to answer
        //Timer timer = new Timer(15);


        // Create the elements
        ElementAffichage repGarde = new DialogueGarde(screenwidth / 2, 0.1 * screenheight, 0.6 * screenwidth, "Oui ? Vous voulez passer ?");
        ElementAffichage btn2 = new BoutonReponses(screenwidth / 2, 0.1 * screenheight, 0.2 * screenwidth, "Non", false);
        ElementAffichage btn1 = new BoutonReponses(screenwidth / 2, 0.1 * screenheight, 0.2 * screenwidth, "Oui", true);
        ElementAffichage btnFinal = new BoutonReponses(screenwidth / 2, 0.1 * screenheight, 0.2 * screenwidth,
                "c'est vraiment vache, au fait il y a des détenus qui se battent cellule 14");

       /* retour = new FlecheDirection(screenwidth/2.8, 600, 90);
        avancer = new FlecheDirection(screenwidth/2.8, 200, -90);
        avancer.setVisible(false);*/


        // When a false answer is clicked
        btn2.setOnMouseClicked(mouseEvent -> {
            repGarde.setTexte(reponsesGardes.get(-etape));
            Controller.reset("Le garde a compris que vous êtes un prisonnier !");
        });

        // When a good answer is clicked
        btn1.setOnMouseClicked(mouseEvent -> {
            timer.resetTimer();
            repGarde.setTexte(reponsesGardes.get(etape));
            etape++;
            btn1.setTexte(msgBouton1.get(etape));
            btn2.setTexte(msgBouton2.get(etape));

            if (etape == 5) {
                this.getChildren().removeAll(btn1, btn2);
                this.getChildren().add(btnFinal);
            }

        });

        // When the final button is clicked
        btnFinal.setOnMouseClicked(mouseEvent -> {

            repGarde.setTexte("Ok je vais y aller merci");

            this.getChildren().removeAll(timer, btnFinal);

            // 2s timer for a delay
            Timer timerFinal = new Timer(2);
            timerFinal.getTimer().play();

            // When this timer is finished change the background and the boolean
            timerFinal.getTimer().setOnFinished(event -> {
                gardePart();
                this.getChildren().remove(repGarde);
                background_scene.setImage(
                        new Image(SceneGarde.class.getResourceAsStream("../../../images/fond_sans_garde.png"))
                );

                //digiCracked = true;
                //this.getChildren().addAll(flechehaut, flechebas);
            });

            this.getChildren().addAll(timerFinal);

        });


        AnimationTimer digi = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(digiCracked) {
                    flechehaut.setVisible(true);
                }
            }
        };
        digi.start();




        // Quand le timer est fini
        timer.getTimer().setOnFinished(event -> repGarde.setTexte("Ah tu sais pas répondre ? Retourne dans ta cellule"));


        // Add it to the scene
        this.getChildren().addAll(background_scene, digicode, timer, repGarde, btn1, btn2,flechebas,flechehaut);
    }

    public void gardePart() {
        garde = false;
    }

    public boolean getPosGarde() {
        return garde;
    }

    /*public FlecheDirection getAvancer() {
        return avancer;
    }*/

    /*public FlecheDirection getRetour() {
        return retour;
    }*/

    public ImageView getDigicode() {
        return digicode;
    }

    public void setDigiCracked(boolean b) {
        digiCracked = b;
    }


    public static void lancertimer(){
        if(lancer)
        {
         timer.resetTimer();
        }
        else
        {
            timer.getTimer().play();
            lancer=true;
        }

    }

}

