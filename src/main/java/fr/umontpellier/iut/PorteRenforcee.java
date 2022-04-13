package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;


public class PorteRenforcee extends Niveaux {
    private final double hauteur = Screen.getPrimary().getBounds().getHeight();
    private final double largueur  = Screen.getPrimary().getBounds().getWidth();
    private Porteseule gauche;

    public PorteRenforcee(){
        ImageView porte = new ImageView(new Image(PorteRenforcee.class.getResourceAsStream("/images/Portes.png")));
        porte.setFitHeight(hauteur);
        porte.setFitWidth(largueur);
        this.getChildren().add(porte);
        Porteseule droite = new Porteseule(true);
        Porteseule gauche = new Porteseule(false);
        //création de la fleche du bas
        FlecheDirection flechebas = new FlecheDirection(Settings.screenwidth*0.50,Settings.screenheight*0.95,0);
        flechebas.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.portes,Controller.scenegarde));
        droite.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.portes,Controller.ordi));
        gauche.setOnMouseClicked(mouseEvent -> {
            if (gauche.getOuverte()){
                Controller.transitions(Controller.portes,Controller.parking);
            } else {
                gauche.printClosedMessage();
            }
        });
        this.gauche=gauche;
        this.getChildren().add(droite);
        this.getChildren().add(gauche);
        this.getChildren().add(flechebas);

    }
    public void setOuverte(){
        gauche.setOuverte();
    }

}
