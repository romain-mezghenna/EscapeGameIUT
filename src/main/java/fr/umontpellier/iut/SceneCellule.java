package fr.umontpellier.iut;


import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.sound.sampled.*;
import java.io.IOException;

/*
Classe réalisée par Dylan Pereira,
Elle a pour but de générer l'ensemble de la SceneCellule.

*/

public class SceneCellule extends Niveaux {

    public SceneCellule() {

        //Création Fond Cellule
        ImageView fondCellule = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/fondCellule.jpg")));
        fondCellule.setFitWidth(Settings.screenwidth);fondCellule.setFitHeight(Settings.screenheight);
        getChildren().add(fondCellule);

        //Création du coussin
        Coussin coussin = new Coussin("/images/coussin.png",Settings.screenwidth/2.46,Settings.screenheight/3.85,Settings.screenwidth/11);
        getChildren().add(coussin);

        //création de la fleche
        FlecheDirection fleche = new FlecheDirection(Settings.screenwidth*0.90,Settings.screenheight/2,270);
        fleche.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.cell1,Controller.corridor));
        getChildren().add(fleche);

        //Création de l'évier
        Evier evier = new Evier();
        getChildren().add(evier);

        //Importation de la touche du Digicode
        ToucheDigicode toucheDigicode6 = new ToucheDigicode("/images/bouton6Digicode.png",20,Settings.screenwidth/3.49,Settings.screenheight/4,6);
        toucheDigicode6.setVisible(false);
        this.getChildren().addAll(toucheDigicode6);

        //Création d'un bouton dans les toilettes qui permet de recuperer la touche du digicode
        Button boutonToilette = new Button();
        boutonToilette.setTranslateX(Settings.screenwidth/3.42);boutonToilette.setTranslateY(Settings.screenheight/2.95);
        boutonToilette.setMinSize(30,30);boutonToilette.setOpacity(0);
        this.getChildren().add(boutonToilette);

        //Création d'un bouton temporaire pour le bouchon de l'évier
        Button boutonEvier = new Button();boutonEvier.setMinSize(20,10);boutonEvier.setTranslateX(Settings.screenwidth/4.74);boutonEvier.setTranslateY(Settings.screenheight/1.77);
        boutonEvier.setOpacity(0);
        this.getChildren().add(boutonEvier);

        //Création d'un bouton d'aide
        ImageView boutonAide = new ImageView(new Image(SceneCellule.class.getResourceAsStream("/images/boutonAide.png")));
        boutonAide.setPreserveRatio(true);
        boutonAide.setFitHeight(50);
        this.getChildren().add(boutonAide);
        boutonAide.setX((Settings.screenwidth/2)-(boutonAide.getFitWidth()));boutonAide.setY(Settings.screenheight/1.1);


        //Création Label explicatif
        Label labelAide = new Label();
        labelAide.setText("Vous vous trouvez actuellement dans votre cellule.\n" +
                "Explorez la afin de comprendre l'ensemble des possibilités qu'elle vous offre.\n\n" +
                "Utilisez l'évier pour y confectionner des recettes!\n" +
                "Vous pouvez cacher des objets sous votre coussin. Ainsi, les gardes ne pourront pas vous voir avec.\n\n" +
                "Attention, vous ne pouvez porter qu'un seul objet à la fois ! Montrez-vous astucieux pour sortir de cette prison rapidement !");
        labelAide.setStyle("-fx-background-color:rgba(31,31,31,0.95);-fx-font-size:30px;-fx-padding: 20;-fx-text-fill: white;-fx-border-width: 3px;-fx-border-color: white");
        labelAide.setTranslateX(Settings.screenwidth/19.2);labelAide.setTranslateY(Settings.screenheight/4);
        labelAide.setVisible(false);
        this.getChildren().add(labelAide);

        //  Apparition / Disparition du Label explicatif
        boutonAide.setOnMouseEntered(mouseEvent -> labelAide.setVisible(true));
        boutonAide.setOnMouseExited(mouseEvent -> labelAide.setVisible(false));

        //  Apparition de la touche du digicode + son + désactivation du bouton après 1 utilisation
        boutonToilette.setOnMousePressed(mouseEvent -> {
            Clip sonGoutteEau;
            try {
                sonGoutteEau = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonGoutteEau.wav"));
                sonGoutteEau.open(inputStream);
                sonGoutteEau.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            toucheDigicode6.setVisible(true);
            boutonToilette.setDisable(true);
        });


        toucheDigicode6.setOnMousePressed(mouseEvent -> {
            Clip sonRecupererTouche;
            try {
                sonRecupererTouche = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonRecupererTouche.wav"));
                sonRecupererTouche.open(inputStream);
                sonRecupererTouche.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        });


        boutonEvier.setOnMousePressed(mouseEvent -> evier.setBouchon());






           /* Clip sonFermetureCellule;
            try {
                sonFermetureCellule = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/sonFermetureCellule.wav"));
                sonFermetureCellule.open(inputStream);
                sonFermetureCellule.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        });

        boutontest2.setOnMouseClicked(mouseEvent -> getChildren().addAll(fondCellule,coussin,evier,boutonEvier,boutontest,boutonAide,labelAide,boutonToilette));*/
    }
}