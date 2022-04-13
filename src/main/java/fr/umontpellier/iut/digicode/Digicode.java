package fr.umontpellier.iut.digicode;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Digicode extends Parent {

    private ClavierNumerique clavierNumerique;
    private Touche toucheValider;
    private ToucheInfo toucheInfo;
    private Touche toucheExit;

    public Digicode() {
        ImageView digicodeNumerique = new ImageView(DenicheurDeFichiers.getImage("Door_Access_Code_Lock.png"));
        getChildren().add(digicodeNumerique);

        setTouchesEtLed();
        setEvenementAppuitTouche();
        setEvenementRelachementTouche();
    }

    private void setEvenementRelachementTouche() {
        setOnKeyReleased(k -> {
            char toucheRelache = k.getText().length() > 0 ? k.getText().toLowerCase().charAt(0) : '\0';
            Touche toucheIdentifier = clavierNumerique.identifieTouche(toucheRelache);
            if (toucheIdentifier != null)
                toucheIdentifier.relacher();
            else if (toucheRelache == ' ' || k.getCode().equals(KeyCode.ENTER))
                toucheValider.relacher();
            else if (toucheRelache == 'h' || toucheRelache == 'i')
                toucheInfo.relacher();
            else if (toucheRelache == 'e' || k.getCode().equals(KeyCode.DELETE) || k.getCode().equals(KeyCode.ESCAPE)) {
                toucheExit.relacher();
            }
        });
    }

    private void setEvenementAppuitTouche() {
        setOnKeyPressed(k -> {
            char toucheAppuye = k.getText().length() > 0 ? k.getText().toLowerCase().charAt(0) : '\0';
            Touche toucheIdentifie = clavierNumerique.identifieTouche(toucheAppuye);
            if (toucheIdentifie != null)
                toucheIdentifie.appuyer();
            else if (toucheAppuye == ' ' || k.getCode().equals(KeyCode.ENTER))
                toucheValider.appuyer();
            else if (toucheAppuye == 'h' || toucheAppuye == 'i')
                toucheInfo.appuyer();
            else if (toucheAppuye == 'e' || k.getCode().equals(KeyCode.DELETE) || k.getCode().equals(KeyCode.ESCAPE))
                toucheExit.appuyer();
            // else if (keyEvent.getCode() == KeyCode.F11) TODO rajouter la possibilité de quitte le plein écran
            // primaryStage.setFullScreen(!primaryStage.isFullScreen());
        });
    }



    private void setTouchesEtLed() {
        GestionnaireSaisieMdp gestionMdp = new GestionnaireSaisieMdp();
        SystemValidationMdp systemValidationMdp = new SystemValidationMdp(getLayoutX(), getLayoutY(), gestionMdp);

        getChildren().addAll(systemValidationMdp.getLeds());
        setTouches(gestionMdp, systemValidationMdp);
    }

    private void setTouches(GestionnaireSaisieMdp gestionMdp, SystemValidationMdp systemValidationMdp) {
        clavierNumerique = new ClavierNumerique(getLayoutX(), getLayoutY(), gestionMdp);
        toucheValider = new ToucheValidation(getLayoutX(), getLayoutY(), systemValidationMdp);
        toucheInfo = new ToucheInfo(290, 0);
        toucheInfo.addTouchesAInformer(clavierNumerique.getTouches());
        toucheExit = new ToucheExit(-96, 350);
        toucheInfo.addTouchesAInformer(toucheExit, toucheValider);
        getChildren().add(toucheExit);
        getChildren().add(toucheInfo);
        getChildren().addAll(clavierNumerique.getTouches());
        getChildren().add(toucheValider);
    }
}
