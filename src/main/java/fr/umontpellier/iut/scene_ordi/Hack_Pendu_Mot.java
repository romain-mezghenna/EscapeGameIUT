package fr.umontpellier.iut.scene_ordi;

import javafx.scene.Parent;

import java.util.ArrayList;

public class Hack_Pendu_Mot extends Parent {
    private String mot;
    public Hack_Pendu_Mot(String mot) {
        this.mot = mot;
    }

    public String getMot() {
        return mot;
    }
}


