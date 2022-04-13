package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Laverie;
import fr.umontpellier.iut.Niveaux;
import fr.umontpellier.iut.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneDigicode extends Niveaux {
    private static Notification notification = new Notification();
    private Digicode digicode;

    public SceneDigicode() {
        ImageView digicodesansgarde = new ImageView(
                new Image(Laverie.class.getResourceAsStream("/images/digicode/fond_sans_garde.png")));
        digicodesansgarde.setFitWidth(Settings.screenwidth);
        digicodesansgarde.setFitHeight(Settings.screenheight);
        digicode = new Digicode();
        digicode.setLayoutY((Settings.screenheight * 0.3));
        digicode.setLayoutX((Settings.screenwidth * 0.42));

        digicode.requestFocus();
        this.getChildren().add(digicodesansgarde);
        getChildren().add(digicode);
        getChildren().add(notification);

    }

     public static void notifierLeJoueur(String informationAAfficher) {
        notification.afficherInformation(informationAAfficher);
    }

    public SceneDigicode reset(){
        InteractionSceneDigicode.reset();
        return new SceneDigicode();
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        digicode.requestFocus();
    }
}