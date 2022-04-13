package fr.umontpellier.iut;

import javafx.scene.input.DataFormat;

public class ToucheDigicode extends ObjetCollectable {
    public static final DataFormat toucheDigicodeDataFormat = new DataFormat("fr.umontpellier.iut.ToucheDigicode");
    public int numero;

    public ToucheDigicode(String objetCollectableResourceLink, int size, double tX, double tY, int numero) {
        super(objetCollectableResourceLink, size, tX, tY);
        this.numero = numero;
        dragMethodDetect(toucheDigicodeDataFormat);
    }

}
