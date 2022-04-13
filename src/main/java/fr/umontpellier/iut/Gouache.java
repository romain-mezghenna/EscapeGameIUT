package fr.umontpellier.iut;

import javafx.scene.input.DataFormat;

public class Gouache extends ObjetCollectable{
    public static final DataFormat gouacheDataFormat = new DataFormat("fr.umontpellier.iut.Gouache");

    public Gouache(String objetCollectableResourceLink, int size, double tX, double tY) {
        super(objetCollectableResourceLink, size, tX, tY);
        dragMethodDetect(gouacheDataFormat);
    }
}
