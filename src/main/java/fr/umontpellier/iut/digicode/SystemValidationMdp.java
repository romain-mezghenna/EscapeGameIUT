package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Controller;

class SystemValidationMdp {

    private GestionnaireSaisieMdp gestionMdp;
    private static String[] nombre = { "zéro", "une", "deux" };
    private int nbTenta;
    private LED[] leds = new LED[3];
    private Son sonEchec;
    private Son sonReussite;

    void verifierSaisie() {
        if (!InteractionSceneDigicode.digicodeEstValide()) {
            if (gestionMdp.verifieSaisieCorrespondAuMdpEtResetSaisie())
                succes();
            else
                echec();
        }
    }

    private void echec() {
        if (++nbTenta == 3) {
            Controller.reset("Vous avez déclenché l'alarme !");

        } else {
            leds[0].faireClignoter(nbTenta);
            leds[nbTenta].allumerEnClignotant();
            sonEchec.jouer();
            SceneDigicode.notifierLeJoueur("Un bruit sourd retentie, vous sentez qu'il ne reste plus que "
                    + nombre[3 - nbTenta] + " tentative" + ((3 - nbTenta) > 1 ? "s" : ""));
        }
    }

    private void succes() {
        InteractionSceneDigicode.validerLeDigicode();
        if (nbTenta > 0)
            leds[1].etteindre();
        if (nbTenta > 1)
            leds[2].etteindre();
        leds[0].allumer();
        sonReussite.jouer();
        SceneDigicode.notifierLeJoueur("Un son mélodieu retentie à travers toute la prison\n Appuyez sur SUPPR pour quitter le digicode.");
    }

    private void initAndSetLeds(double positionX, double positionY) {
        leds[0] = new LED("Verte", positionX + 68, positionY + 55);
        leds[1] = new LED("Orange", positionX + 68 + 60, positionY + 55);
        leds[2] = new LED("Rouge", positionX + 68 + 60 * 2, positionY + 55);
        leds[0].faireClignoter(0);
    }


    public SystemValidationMdp(double positionX, double positionY, GestionnaireSaisieMdp gestionMdp) {
        this.gestionMdp = gestionMdp;
        initAndSetLeds(positionX, positionY);
        nbTenta = 0;
        sonEchec = new Son("Echec");
        sonReussite = new Son("Reussite");
    }

    /**
     * @return the leds
     */
    public LED[] getLeds() {
        return leds;
    }

}