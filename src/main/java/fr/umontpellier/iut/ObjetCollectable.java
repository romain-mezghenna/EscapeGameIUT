package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.io.Serializable;

public class ObjetCollectable extends Parent implements Serializable {
    public static final DataFormat objetCollectableDataFormat = new DataFormat("fr.umontpellier.iut.ObjetCollectable");

    private final String objetCollectableResourcesLink;
    private transient ImageView objetCollectableImage;
    private boolean pickedUp;

    public ObjetCollectable(String objetCollectableResourceLink, int size, double tX, double tY) {
        this.objetCollectableResourcesLink = objetCollectableResourceLink;
        objetCollectableImage = new ImageView(new Image(getClass().getResourceAsStream(objetCollectableResourcesLink)));
        objetCollectableImage.setFitHeight(size);
        objetCollectableImage.setPreserveRatio(true);

        getChildren().add(objetCollectableImage);
        setTranslateX(tX);
        setTranslateY(tY);

        dragMethodDetect(objetCollectableDataFormat);
        setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                if (getParent().getParent() instanceof SingleSpaceInv)
                    ((SingleSpaceInv) getParent().getParent()).emptyInvSlot();
                getChildren().remove(objetCollectableImage);
                pickedUp = true;
            }
        });
    }

    public void dragMethodDetect(DataFormat dataFormat) {
        setOnDragDetected(mouseEvent -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.put(dataFormat, this);
            db.setContent(clipboardContent);

            mouseEvent.consume();
        });
    }

    public String getObjetCollectableResourcesLink() {
        return objetCollectableResourcesLink;
    }
}
