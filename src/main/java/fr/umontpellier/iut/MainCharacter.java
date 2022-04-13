package fr.umontpellier.iut;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class MainCharacter extends Parent {
    private ImageView charImagePrisonner;
    private ImageView charImageGuard;
    private boolean isGuard;

    public MainCharacter() {
        StackPane invBackgroundGroup = new StackPane();
        invBackgroundGroup.setBackground(new Background(new BackgroundFill(Color.rgb(31,31,31), new CornerRadii(15), Insets.EMPTY)));
        invBackgroundGroup.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        charImagePrisonner = new ImageView(new Image(getClass().getResourceAsStream("/images/charImagePrisonUniform.png")));
        charImagePrisonner.setFitHeight(400);
        charImagePrisonner.setPreserveRatio(true);
        charImagePrisonner.setVisible(true);

        charImageGuard = new ImageView(new Image(getClass().getResourceAsStream("/images/charImageGuardUniform.png")));
        charImageGuard.setFitHeight(400);
        charImageGuard.setPreserveRatio(true);
        charImageGuard.setVisible(false);

        isGuard = false;

        invBackgroundGroup.getChildren().add(charImagePrisonner);
        invBackgroundGroup.getChildren().add(charImageGuard);

        getChildren().add(invBackgroundGroup);
        setTranslateX(5);
        setTranslateY(Screen.getPrimary().getBounds().getMaxY()-charImagePrisonner.getLayoutBounds().getMaxY()-15);

        setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof Uniforme && dragEvent.getDragboard().hasContent(Uniforme.uniformeDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(Uniforme.uniformeDataFormat)) {
                success = Controller.character.switchUniform((Uniforme) db.getContent(Uniforme.uniformeDataFormat));
                if (success && ((Uniforme) db.getContent(Uniforme.uniformeDataFormat)).noir) {
                    Uniforme uniforme = new Uniforme("/images/uniformeOrange.png",150,250,850, false);
                    if (!Controller.handInv.ajouterObjet(uniforme))
                        Controller.group.getChildren().add(uniforme);
                } else if (success && !((Uniforme) db.getContent(Uniforme.uniformeDataFormat)).noir) {
                    Uniforme uniforme = new Uniforme("/images/uniformeNoir.png",150,250,850, true);
                    if (!Controller.handInv.ajouterObjet(uniforme))
                        Controller.group.getChildren().add(uniforme);
                }
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
    }

    public boolean switchUniform(Uniforme uniforme) {
        boolean success = false;
        if (uniforme.noir && charImagePrisonner.isVisible()) {
            switchUniform();
            success = true;
        } else if (!uniforme.noir && charImageGuard.isVisible()) {
            switchUniform();
            success = true;
        }
        return success;
    }

    public void switchUniform() {
        charImageGuard.setVisible(!charImageGuard.isVisible());
        charImagePrisonner.setVisible(!charImagePrisonner.isVisible());
        isGuard = charImageGuard.isVisible();
    }

    public boolean isGuard() {
        return isGuard;
    }
}
