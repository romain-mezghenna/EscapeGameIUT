package fr.umontpellier.iut;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

public class CleVoiture extends ObjetCollectable {
    public static final DataFormat cleVoitureDataFormat = new DataFormat("fr.umontpellier.iut.CleVoiture");

    public CleVoiture(String objetCollectableResourceLink, int size, double tX, double tY) {
        super(objetCollectableResourceLink, size, tX, tY);
        dragMethodDetect(cleVoitureDataFormat);
    }

}