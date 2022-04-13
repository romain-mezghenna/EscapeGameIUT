package fr.umontpellier.iut.scene_ordi;

import javafx.animation.AnimationTimer;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


import java.awt.Dimension;

public class Ordi_SceneSession extends Parent {

    // déclaration des dimensions de l'écran
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    double screenheight = dimension.getHeight();
    double screenwidth  = dimension.getWidth();

    // btn power
    ImageView btn_back;

    // mot de passe
    TextField mdp;
    int essaie_mdp;
    Button btn_ok;

    // background
    ImageView bg_session;

    // variable de transitions
    public boolean go_bureauordi;
    public boolean go_salleordi;

    public Ordi_SceneSession(){

        // initialisation des variables de transitions
        go_bureauordi = false;
        go_salleordi = false;


        //// création / modifications des objets :

        // background
        bg_session = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/fond-ubuntu.png")));
        bg_session.setFitHeight(screenheight);
        bg_session.setFitWidth(screenwidth);

        // boutons
        btn_ok = new Button();
        btn_ok.setText("se connecter");
        btn_ok.setTranslateX(screenwidth/2 - 25);
        btn_ok.setTranslateY(screenheight/2 +30);

        btn_back = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/bouton_power.png")));
        btn_back.setTranslateX(screenwidth - btn_back.getFitWidth() - 29);

        // mot de passe
        mdp = new TextField();
        mdp.setTranslateX(screenwidth/2 - 60);
        mdp.setTranslateY(screenheight/2 - 25);
        essaie_mdp = 0;

        // ajout des objets a la fenetre
        getChildren().add(bg_session);
        getChildren().addAll(btn_ok, btn_back);
        getChildren().add(mdp);


        // event souris :
        btn_ok.setOnMousePressed(mouseEvent -> test_mdp());
        btn_back.setOnMousePressed(mouseEvent -> vers_salleordi());
        mdp.setOnMousePressed(mouseEvent -> mdp.requestFocus());

        // event clavier
        mdp.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.ENTER){
                test_mdp();
            }
        });


        // animation (teste constamment les conditions qui la composent)
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now)
            {

                if (essaie_mdp == 3){
                    essaie_mdp = 0;
                    //
                    //
                    // a implémenter pour retourner au tout début
                    //
                    //
                }
            }
        };
        timer.start();
    }

    // methodes de transition
    public void vers_bureauordi(){
        go_bureauordi = true;
        mdp.clear(); // netttoie la zone de texte
    }

    public void vers_salleordi(){
        go_salleordi = true;
        System.out.println(mdp.getCharacters().toString());
        mdp.clear(); // netttoie la zone de texte
    }


    // méthode de passage de mdp
    public void test_mdp(){
        if (mdp.getCharacters().toString().equals("1904")){
            vers_bureauordi();
        }
        else {
            essaie_mdp = essaie_mdp + 1;
        }
    }


    // getter et setter
    public void setGo_bureauordi(boolean go_bureauordi) {
        this.go_bureauordi = go_bureauordi;
    }

    public void setGo_salleordi(boolean go_salleordi) {
        this.go_salleordi = go_salleordi;
    }
}
