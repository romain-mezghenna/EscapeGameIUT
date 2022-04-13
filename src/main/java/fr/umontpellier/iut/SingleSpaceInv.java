package fr.umontpellier.iut;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SingleSpaceInv extends Parent {
    private ObjetCollectable objetCollectable;
    private StackPane oneInvCase;
    private int width;

    public SingleSpaceInv(int width) {
        this.width=width;
        oneInvCase = new StackPane();
        oneInvCase.setPrefSize(width,width);
        oneInvCase.setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        oneInvCase.setBackground(new Background(new BackgroundFill(Color.rgb(31,31,31), CornerRadii.EMPTY, Insets.EMPTY)));
        oneInvCase.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(2))));

        getChildren().add(oneInvCase);
    }

    public Boolean stockItem(ObjetCollectable objetCollectable) {
        boolean success = false;
        if (isEmpty()) {
            this.objetCollectable = objetCollectable;
            oneInvCase.getChildren().add(this.objetCollectable);
            success = true;
        }
        return success;
    }

    public boolean isEmpty() {
        return objetCollectable == null;
    }

    public void emptyInvSlot() {
        oneInvCase.getChildren().remove(0);
        objetCollectable = null;
    }
}
