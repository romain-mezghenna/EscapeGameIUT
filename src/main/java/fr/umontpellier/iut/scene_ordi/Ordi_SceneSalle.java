package fr.umontpellier.iut.scene_ordi;

import fr.umontpellier.iut.Controller;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Dimension;

public class Ordi_SceneSalle extends Parent {

    // déclaration des dimensions de l'écran
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    double screenheight = dimension.getHeight();
    double screenwidth  = dimension.getWidth();

    // acces vers la session
    ImageView btn_start;
    // background
    ImageView salle_bg;

    // post-it
    ImageView postit_code_petit;
    ImageView postit_code_grand;
    ImageView postit_medor_petit;
    ImageView postit_medor_grand;
    ImageView postit_ordi_petit;
    ImageView postit_ordi_grand;

    // fleche transition de scene hors de la salle
    ImageView fleche_sorsordi;

    // variable de transition
    public boolean go_sessionordi;

    public Ordi_SceneSalle(){

        // initialisation des variables de transitions
        go_sessionordi = false;

        //// création / modifications des objets

        // acces vers la session
        btn_start = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/salle-ecran.png")));

        // background
        salle_bg = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/salle-bg.png")));
        salle_bg.setFitHeight(screenheight);
        salle_bg.setFitWidth(screenwidth);

        ////////// post-it :

        // postit code dans le fond
        postit_code_petit = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit2.png")));
        postit_code_petit.setFitWidth(40);
        postit_code_petit.setFitHeight(40);
        postit_code_petit.setTranslateX(screenwidth/4 );
        postit_code_petit.setTranslateY(screenheight/4);
        // postit code zoomer
        postit_code_grand = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit2.png")));
        postit_code_grand.setFitWidth(screenwidth/4);
        postit_code_grand.setFitHeight(screenheight/2);
        postit_code_grand.setTranslateX(screenwidth/4 *1.5);
        postit_code_grand.setTranslateY(screenheight/4);
        postit_code_grand.setVisible(false);

        // postit medor dans le fond
        postit_medor_petit = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit1.png")));
        postit_medor_petit.setFitWidth(40);
        postit_medor_petit.setFitHeight(40);
        postit_medor_petit.setTranslateX(screenwidth/6);
        postit_medor_petit.setTranslateY(screenheight/3);
        postit_medor_petit.setRotate(10);
        // postit medor zoomer
        postit_medor_grand = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit1.png")));
        postit_medor_grand.setFitWidth(screenwidth/4);
        postit_medor_grand.setFitHeight(screenheight/2);
        postit_medor_grand.setTranslateX(screenwidth/4 *1.5);
        postit_medor_grand.setTranslateY(screenheight/4);
        postit_medor_grand.setVisible(false);

        // postit ordi dans le fond
        postit_ordi_petit = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit3.png")));
        postit_ordi_petit.setFitWidth(40);
        postit_ordi_petit.setPreserveRatio(true);
        postit_ordi_petit.setTranslateX(screenwidth/8);
        postit_ordi_petit.setTranslateY(screenheight/6);
        postit_ordi_petit.setRotate(-15);
        // postit ordi zoomer
        postit_ordi_grand = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/postit3.png")));
        postit_ordi_grand.setFitWidth(screenwidth/4);
        postit_ordi_grand.setFitHeight(screenheight/2);
        postit_ordi_grand.setTranslateX(screenwidth/4 *1.5);
        postit_ordi_grand.setTranslateY(screenheight/4);
        postit_ordi_grand.setVisible(false);


        // fleche de sortie
        fleche_sorsordi = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/fleche.png")));
        fleche_sorsordi.setFitWidth(screenwidth/10);
        fleche_sorsordi.setFitHeight(screenheight/10);
        fleche_sorsordi.setTranslateX(screenwidth/1.15);
        fleche_sorsordi.setTranslateY(screenheight/1.9);
        fleche_sorsordi.setRotate(10);


        //// ajout des objets a la fenetre
        getChildren().add(salle_bg);
        getChildren().add(btn_start);
        getChildren().add(fleche_sorsordi);
        getChildren().add(postit_code_petit);
        getChildren().add(postit_code_grand);
        getChildren().add(postit_medor_petit);
        getChildren().add(postit_medor_grand);
        getChildren().add(postit_ordi_petit);
        getChildren().add(postit_ordi_grand);



        ///////////////////////////// event souris

        btn_start.setOnMousePressed(mouseEvent -> vers_session());      // clic sur l'ordi pour aller vers la session

        postit_code_petit.setOnMouseEntered(mouseEvent -> zoom(postit_code_grand));      // survol du petit postit -> affiche le grand postit
        postit_code_petit.setOnMouseExited(mouseEvent -> zoom(postit_code_grand));       // sortie de survol du petit postit -> enleve le grand postit

        postit_medor_petit.setOnMouseEntered(mouseEvent -> zoom(postit_medor_grand));
        postit_medor_petit.setOnMouseExited(mouseEvent -> zoom(postit_medor_grand));

        postit_ordi_petit.setOnMouseEntered(mouseEvent -> zoom(postit_ordi_grand));
        postit_ordi_petit.setOnMouseExited(mouseEvent -> zoom(postit_ordi_grand));

        fleche_sorsordi.setOnMousePressed(mouseEvent -> sortie_salle_ordi());

    }

    // méthode de transition
    public void vers_session(){
        go_sessionordi = true;
    }

    // méthode de zoom / dezoom sur le postit
    public void zoom(ImageView pg){
        pg.setVisible(!pg.isVisible());
    }

    // getter et setter
    public void setGo_sessionordi(boolean go_sessionordi) {
        this.go_sessionordi = go_sessionordi;
    }


    ///////////////////////////////////////////////////////////////////////////
    // méthode de sortie de la salle

    public void sortie_salle_ordi(){
            Controller.transitions(Controller.ordi,Controller.portes);
    }
    /////////////////////////////////////////////////////////////////////////////
}
