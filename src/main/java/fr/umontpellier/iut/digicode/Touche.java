package fr.umontpellier.iut.digicode;

import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Touche extends StackPane {
    static {
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-45.0);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        EFFET_APPUYER = lighting;
    }
    private static final Lighting EFFET_APPUYER;
    private static final Lighting SANS_EFFET = null;
    private ImageView image;
    private ImageView image1;
    private String nom;
    private double positionY;
    private Son son;
    private Text raccourcieClavier;
    private boolean manquant;

    public Touche(String nom, double positionX, double positionY) {
        this.nom = nom;
        this.positionY = positionY;
        if(nom.equals("6")||nom.equals("9")){
            image1 = new ImageView(DenicheurDeFichiers.getImage("bouton" + nom + ".png"));
            getChildren().add(image1);
            image = new ImageView(DenicheurDeFichiers.getImage("boutonManquant.png"));
            getChildren().add(image);
            manquant=true;
        }
        else
        {image = new ImageView(DenicheurDeFichiers.getImage("bouton" + nom + ".png"));
        getChildren().add(image);
        manquant=false;}

        raccourcieClavier = new Text(nom);
        raccourcieClavier.setVisible(false);
        raccourcieClavier.setFont(new Font(19));
        raccourcieClavier.setFill(Color.WHITESMOKE);
        getChildren().add(raccourcieClavier);

        setTranslateX(positionX);
        setTranslateY(positionY);

        setOnMousePressed(mouseEvent -> appuyer());
        setOnMouseReleased(mouseEvent -> relacher());
        son = new Son("Bouton" + nom);
    }

    public void appuyer() {
        son.jouer();
        setTranslateY(positionY + 2);
        image.setEffect(EFFET_APPUYER);
    }

    public void relacher() {
        setTranslateY(positionY);
        image.setEffect(SANS_EFFET);
    }

    public void afficherRaccourcieClavier() {
        image.setEffect(new ColorAdjust(0, 0, -0.6, 0));
        raccourcieClavier.setVisible(true);
    }

    public void cacherRaccourcieClavier() {
        raccourcieClavier.setVisible(false);
        image.setEffect(SANS_EFFET);
    }

    public void setRaccourcieClavier(String raccourcieClavier) {
        this.raccourcieClavier.setText(raccourcieClavier);
    }

    public String getNom() {
        return nom;
    }

    public boolean isManquant() {
        return manquant;
    }

    public void setManquant() {
        this.manquant =false;
        getChildren().remove(1);
    }
}
