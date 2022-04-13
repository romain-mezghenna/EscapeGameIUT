package fr.umontpellier.iut;

import javafx.scene.Parent;

public class Montre extends Parent {
    private boolean hasMontre = false;

    public void findMontre(){ //Si il a trouve la montre change hasMontre a vrai
        hasMontre = true;
    }

    public boolean isHasMontre() { //Get hasMontre
        return hasMontre;
    }
}
