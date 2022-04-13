package fr.umontpellier.iut.scene_ordi;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hack_finlevel extends Parent {

    // position / taille de la fin
    double posx;
    double posy;
    int width;
    int height;

    // corp graphique en 2 partie (pour que le perso puisse passer entre les 2)
    ImageView corp_front;
    ImageView corp_back;

    // corp graphique pour le dernier level
    ImageView corpfinal;

    // nom des fin suivant leur niveau
    Label nom_file1;
    Label nom_file2;
    Label nom_file3;
    Label nom_file4;
    Label nom_filelast;


    public Hack_finlevel(){

        // initialisation pos / taille
        width = 40;
        height = 40;
        posx = 814-width;
        posy = 30 + (542-(double)height)/2;

        // placement de l'objet
        setTranslateX(posx);
        setTranslateY(posy);

        // création / ajout du corp de l'obj
        corp_back = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/hack-file-back.png")));
        corp_back.setTranslateX(posx);
        corp_back.setTranslateY(posy);
        //getChildren().add(corp_back);     // on ne l'ajoute pas car ce sera a la scene principale de le faire pour que le perso puisse etre devant le corp_back et derriere le corp_front
        corp_front = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/hack-file.png")));
        corp_front.setTranslateX(posx);
        corp_front.setTranslateY(posy);
        //getChildren().add(corp_front);    // meme chose que plus haut
        corpfinal  = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/hack-file-final.png")));
        corpfinal.setVisible(false);
        getChildren().add(corpfinal);


        // creation / ajout nom de l'obj
        // level 1
        nom_file1 = new Label("fichier_securite");
        nom_file1.setTranslateY(40);
        nom_file1.setStyle("-fx-text-fill: aliceblue");
        getChildren().add(nom_file1);
        // level 2
        nom_file2 = new Label("fichier_de_controle");
        nom_file2.setTranslateY(40);
        nom_file2.setStyle("-fx-text-fill: aliceblue");
        nom_file2.setVisible(false);
        getChildren().add(nom_file2);
        // level 3
        nom_file3 = new Label("controle_des_sorties");
        nom_file3.setTranslateY(40);
        nom_file3.setStyle("-fx-text-fill: aliceblue");
        nom_file3.setVisible(false);
        getChildren().add(nom_file3);
        // level 4
        nom_file4 = new Label("controle_de_la_porte");
        nom_file4.setTranslateY(40);
        nom_file4.setStyle("-fx-text-fill: aliceblue");
        nom_file4.setVisible(false);
        getChildren().add(nom_file4);
        // dernier level (level 5)
        nom_filelast = new Label("ouverture.exe");
        nom_filelast.setTranslateY(40);
        nom_filelast.setStyle("-fx-text-fill: aliceblue");
        nom_filelast.setVisible(false);
        getChildren().add(nom_filelast);
    }

    ////////// méthodes utiles

    // changement de corp pour le dernier niveau
    public void dernier_skin(){
        corp_front.setVisible(false);
        corp_back.setVisible(false);
        corpfinal.setVisible(true);
    }

    // attribution des noms suivant le level

    public void level1(){
        nom_file1.setVisible(true);
        nom_file2.setVisible(false);
        nom_file3.setVisible(false);
        nom_file4.setVisible(false);
        nom_filelast.setVisible(false);
    }
    public void level2(){
        nom_file2.setVisible(true);
        nom_file1.setVisible(false);
        nom_file3.setVisible(false);
        nom_file4.setVisible(false);
        nom_filelast.setVisible(false);
    }
    public void level3(){
        nom_file3.setVisible(true);
        nom_file1.setVisible(false);
        nom_file2.setVisible(false);
        nom_file4.setVisible(false);
        nom_filelast.setVisible(false);
    }
    public void level4(){
        nom_file4.setVisible(true);
        nom_file1.setVisible(false);
        nom_file2.setVisible(false);
        nom_file3.setVisible(false);
        nom_filelast.setVisible(false);
    }
    public void levellast(){            // dernier level
        dernier_skin();
        nom_filelast.setVisible(true);
        nom_file1.setVisible(false);
        nom_file2.setVisible(false);
        nom_file3.setVisible(false);
        nom_file4.setVisible(false);
    }
}
