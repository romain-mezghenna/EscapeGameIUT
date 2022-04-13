package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Controller;

public class ToucheNumerique extends Touche {
    private GestionnaireSaisieMdp gestionnaireSaisieMdp;

    public ToucheNumerique(String nom, double positionX, double positionY,
            GestionnaireSaisieMdp gestionnaireSaisieMdp) {
        super(nom, positionX, positionY);
        super.setOnMousePressed(mouseEvent -> this.appuyer());
        this.gestionnaireSaisieMdp = gestionnaireSaisieMdp;
        if (nom.matches("[0-9]+"))
            super.setRaccourcieClavier("Num " + nom);
    }

    @Override
    public void appuyer() {
        if(!super.isManquant()){
        gestionnaireSaisieMdp.saisie(super.getNom().charAt(0));
        super.appuyer();}
        else
        {
            Controller.resetsoft("Vous vous êtes électrocuté,\n les gardes vous ont déplacer dans votre cellule");
        }

    }

}