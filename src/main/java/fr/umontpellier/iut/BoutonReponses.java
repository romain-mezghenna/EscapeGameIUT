package fr.umontpellier.iut;

public class BoutonReponses extends ElementAffichage {

    private boolean isBonneReponse;

    public BoutonReponses(double x, double haut, double larg, String text, boolean bonne) {
        super(x, 700, haut, larg, text);
        isBonneReponse = bonne;

        if(isBonneReponse) {
            super.element.setTranslateX(larg);
        }
        else {
            super.element.setTranslateX(x + larg/2);
        }
    }

    public BoutonReponses(double x, double haut, double larg, String text) {
        super(x, 700, haut, larg, text);

    }

    public boolean isBonneReponse() {
        return isBonneReponse;
    }

}
