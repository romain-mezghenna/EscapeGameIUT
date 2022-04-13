package fr.umontpellier.iut.digicode;

class GestionnaireSaisieMdp {
    private static final int MOT_DE_PASSE = 1538340736;
    private String saisie = "";

    public void saisie(char touche) {
        saisie += touche;
    }

    public boolean verifieSaisieCorrespondAuMdpEtResetSaisie() {
        boolean correspond = saisie.hashCode() == MOT_DE_PASSE;
        saisie = "";
        return correspond;
    }
}