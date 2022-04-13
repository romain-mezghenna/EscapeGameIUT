package fr.umontpellier.iut;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import javax.sound.sampled.*;
import java.io.IOException;

/*
Classe réalisée par Dylan Pereira,
Elle a pour but de rendre l'évier intéragissable.
Possibilité d'ajout du bouchon d'évier, et des differents etat de l'eau :

        - Evier rempli d'eau bleue
        - Evier rempli d'eau noire
        - Evier avec seulement un point central d'eau.
*/

public class Evier extends Parent {

    private boolean rempli;
    public boolean bouchon;//avec ou sans

    private final Robinet robinet1;
    private final Robinet robinet2;

    private final ImageView eauCentrale;
    private final ImageView bouchonEvier;
    private final ImageView eauRempli;
    private final ImageView eauRempliNoire;

    public Evier(){
        //Initialisation des variables
        rempli=false;bouchon=false;

        //Création des deux robinets
        robinet1 = new Robinet("/images/robinetCellule1.png","/images/eaurobinet1.png",Settings.screenwidth/7.33,Settings.screenheight/1.81,30);
        robinet2 = new Robinet("/images/robinetCellule2.png","/images/eaurobinet2.png",Settings.screenwidth/7.33,Settings.screenheight/1.67,30);

        //Importation du bouchon d'évier
        bouchonEvier = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/bouchon évier.png")));
        bouchonEvier.setFitWidth(Settings.screenwidth);bouchonEvier.setFitHeight(Settings.screenheight);bouchonEvier.setVisible(false);

        //Importation de l'eau centrale
        eauCentrale = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/eaucentrale.png")));
        eauCentrale.setFitWidth(Settings.screenwidth);eauCentrale.setFitHeight(Settings.screenheight);eauCentrale.setVisible(false);

        //Importation de l'eau rempli
        eauRempli = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/eaubleue.png")));
        eauRempli.setFitWidth(Settings.screenwidth);eauRempli.setFitHeight(Settings.screenheight);eauRempli.setVisible(false);

        //Importation de l'eau rempli noire
        eauRempliNoire = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/eaunoire.png")));
        eauRempliNoire.setFitWidth(Settings.screenwidth);eauRempliNoire.setFitHeight(Settings.screenheight);eauRempliNoire.setVisible(false);

        this.getChildren().addAll(robinet1,robinet2,bouchonEvier,eauCentrale,eauRempli,eauRempliNoire);


        eauRempli.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof Gouache && dragEvent.getDragboard().hasContent(Gouache.gouacheDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        eauRempli.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(Gouache.gouacheDataFormat)) {
                Clip sonAjoutGouache;
                try {
                    sonAjoutGouache = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonAjoutGouache.wav"));
                    sonAjoutGouache.open(inputStream);
                    sonAjoutGouache.start();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
                eauRempli.setVisible(false);
                eauRempliNoire.setVisible(true);
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        eauRempliNoire.setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof Uniforme && dragEvent.getDragboard().hasContent(Uniforme.uniformeDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        eauRempliNoire.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(Uniforme.uniformeDataFormat)) {
                Uniforme uniforme = new Uniforme("/images/UniformeNoir.png",150,Settings.screenwidth/4.51,Settings.screenheight/1.66, true);
                getChildren().add(uniforme);
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                eauCentrale.setVisible(robinet2.getEtat() || robinet1.getEtat());
                if(robinet1.getEtat() && robinet2.getEtat() && bouchon){
                    remplir();
                }
            }
        };
        timer.start();


    }

    //Fonction qui va choisir en fonction de son etat actuel d'ouvrir ou de fermer l'evier, et qui par la suite inversera son etat.
    public void setBouchon(){
        if(!bouchon){
           ajoutBouchon();
        }else{
            retirerBouchon();
        }
        bouchon=!bouchon;
    }

    //Fonction d'ajout du bouchon : affichage bouchon ; Son
    public void ajoutBouchon(){
        bouchonEvier.setVisible(true);

        Clip sonAjoutBouchon;
        try {
            sonAjoutBouchon = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonAjoutBouchon.wav"));
            sonAjoutBouchon.open(inputStream);
            sonAjoutBouchon.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    //Fonction de retirement du bouchon : désaffichage bouchon ; Son ; Gestion cas rempli
    public void retirerBouchon(){
        bouchonEvier.setVisible(false);

        Clip sonRetirerBouchon;
        try {
            sonRetirerBouchon = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonRetirerBouchon.wav"));
            sonRetirerBouchon.open(inputStream);
            sonRetirerBouchon.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        //Si rempli, on vide l'évier (visuel+boolean),  puis  on re donne la réactiver leur actionnement
        if(rempli){
            rempli=false;
            eauRempli.setVisible(false);
            eauRempliNoire.setVisible(false);
            robinet1.setDisable(false);
            robinet2.setDisable(false);
        }
    }

    //Fonction qui va remplir l'evier (visuel+boolean) ; fermer les robinets ; et désactiver leur actionnement
    public void remplir(){
        rempli =!rempli;
        eauRempli.setVisible(true);
        robinet1.actionner();robinet2.actionner();
        robinet1.setDisable(true);robinet2.setDisable(true);
    }
}
