package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Controller;

class ToucheExit extends Touche {


    public ToucheExit(double positionX, double positionY) {
        super("Exit", positionX, positionY);
        super.setOnMousePressed(mouseEvent -> appuyer());
        super.setRaccourcieClavier("SUPPR");
    }

    @Override
    public void appuyer() {
        super.appuyer();
        Controller.transitions(Controller.digicodechiffre,Controller.scenegarde);
        Controller.scenegarde.requestFocus(); // TODO à voir s'il faut focus sceneGarde

        if (InteractionSceneDigicode.digicodeEstValide())
            Controller.scenegarde.setDigiCracked(true);
    }

}