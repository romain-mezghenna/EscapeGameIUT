package fr.umontpellier.iut;

import javafx.scene.input.DataFormat;

public class Uniforme extends ObjetCollectable {
    public static final DataFormat uniformeDataFormat = new DataFormat("fr.umontpellier.iut.Uniforme");
    public boolean noir;

    public Uniforme(String objetCollectableResourceLink, int size, double tX, double tY, boolean noir) {
        super(objetCollectableResourceLink, size, tX, tY);
        this.noir = noir;
        dragMethodDetect(uniformeDataFormat);
    }
}
