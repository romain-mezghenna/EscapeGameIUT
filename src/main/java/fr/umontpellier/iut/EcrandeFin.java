package fr.umontpellier.iut;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

import javax.sound.sampled.*;
import java.io.IOException;

public class EcrandeFin extends Niveaux {
    private Credits lescredits = new Credits();

    public EcrandeFin (){
        ImageView lav = new ImageView(new Image(Laverie.class.getResourceAsStream("/images/ecrandefin.png")));
        lav.setFitWidth(Settings.screenwidth);
        lav.setFitHeight(Settings.screenheight);
        getChildren().add(lav);


        getChildren().add(lescredits);

    }

    public Credits getLescredits() {
        return lescredits;
    }
}