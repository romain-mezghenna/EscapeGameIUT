package fr.umontpellier.iut.digicode;

class ToucheValidation extends Touche {
    private SystemValidationMdp systemValidationMdp;

    public ToucheValidation(double positionX, double positionY, SystemValidationMdp systemValidationMdp) {
        super("Cloche", positionX + 59 + 67 / 2 + 12, positionY + 101 + 49 * 4 + 12);
        this.systemValidationMdp = systemValidationMdp;
        super.setOnMousePressed(mouseEvent -> this.appuyer());
        super.setRaccourcieClavier("Entrée/Espace");
    }

    @Override
    public void appuyer() {
        super.appuyer();
        systemValidationMdp.verifierSaisie();
    }

}