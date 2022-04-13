package fr.umontpellier.iut;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Inventory extends Parent {
    private String invName;
    private boolean withName;
    private int size;
    private int slotWidth;
    private ArrayList<SingleSpaceInv> slots;

    public Inventory(String invName, int size, int slotWidth) {
        this.invName = invName;
        this.size = size;
        this.slotWidth = slotWidth;
        slots = new ArrayList<>();
        withName = true;

        drop();
        invDesign();
    }

    public Inventory(int size, int slotWidth) {
        this.size = size;
        this.slotWidth = slotWidth;
        slots = new ArrayList<>();
        withName = false;

        drop();
        invDesign();
    }

    private void invDesign() {
        GridPane invBackgroundGroup = new GridPane();
        invBackgroundGroup.setBackground(new Background(new BackgroundFill(Color.rgb(31,31,31), new CornerRadii(15), Insets.EMPTY)));
        invBackgroundGroup.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));

        //Create and add each inventory slots
        for (int i = 0; i<size; i++) {
            for (int j = 0; j<size; j++) {
                SingleSpaceInv tempObject = new SingleSpaceInv(slotWidth);
                slots.add(tempObject);
                invBackgroundGroup.add(tempObject,j,i+1);
                GridPane.setHalignment(tempObject, HPos.LEFT);
                GridPane.setValignment(tempObject, VPos.TOP);
            }
        }

        //Title of inventory
        if (withName) {
            Text invNameText = new Text(invName);
            invNameText.setFont(Font.font(30));
            invNameText.setFill(Color.WHITE);

            StackPane titleInvCase = new StackPane();
            titleInvCase.setPrefSize(slotWidth,slotWidth);
            titleInvCase.setBackground(new Background(new BackgroundFill(Color.rgb(31,31,31), CornerRadii.EMPTY, Insets.EMPTY)));
            titleInvCase.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(2))));
            titleInvCase.getChildren().add(invNameText);

            invBackgroundGroup.add(titleInvCase,0,0,invBackgroundGroup.getColumnCount(),1);
        }

        getChildren().add(invBackgroundGroup);
    }

    public boolean ajouterObjet(ObjetCollectable objetCollectable) {
        ObjetCollectable objet = new ObjetCollectable(objetCollectable.getObjetCollectableResourcesLink(), slotWidth-10,0,0);

        boolean success = false;
        for (int i=0; i<slots.size() && !success; i++) {
            success = slots.get(i).stockItem(objet);
        }
        return success;
    }

    public boolean ajouterObjet(Gouache gouache) {
        Gouache objet = new Gouache(gouache.getObjetCollectableResourcesLink(), slotWidth-10,0,0);

        boolean success = false;
        for (int i=0; i<slots.size() && !success; i++) {
            success = slots.get(i).stockItem(objet);
        }
        return success;
    }

    public boolean ajouterObjet(Uniforme uniforme) {
        Uniforme uniforme1;
        if (uniforme.noir)
            uniforme1 = new Uniforme("/images/UniformeNoir.png", slotWidth-10,0,0, true);
        else
            uniforme1 = new Uniforme("/images/uniformeOrange.png", slotWidth-10,0,0, false);


        boolean success = false;
        for (int i=0; i<slots.size() && !success; i++) {
            success = slots.get(i).stockItem(uniforme1);
        }
        return success;
    }

    public boolean ajouterObjet(ToucheDigicode toucheDigicode) {
        ToucheDigicode toucheDigicode1 = toucheDigicode;
        if (toucheDigicode.numero==6)
            toucheDigicode1 = new ToucheDigicode("/images/bouton6Digicode.png", slotWidth-10,0,0, toucheDigicode.numero);
        else if (toucheDigicode.numero==9)
            toucheDigicode1 = new ToucheDigicode("/images/bouton9Digicode.png", slotWidth-10,0,0, toucheDigicode.numero);


        boolean success = false;
        for (int i=0; i<slots.size() && !success; i++) {
            success = slots.get(i).stockItem(toucheDigicode1);
        }
        return success;
    }

    public void drop() {
        setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() != this && (dragEvent.getDragboard().hasContent(ObjetCollectable.objetCollectableDataFormat))
                    || dragEvent.getDragboard().hasContent(ToucheDigicode.toucheDigicodeDataFormat)
                    || dragEvent.getDragboard().hasContent(Uniforme.uniformeDataFormat)
                    || dragEvent.getDragboard().hasContent(Gouache.gouacheDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(ObjetCollectable.objetCollectableDataFormat))
                success = ajouterObjet((ObjetCollectable) db.getContent(ObjetCollectable.objetCollectableDataFormat));
            else if (db.hasContent(Gouache.gouacheDataFormat))
                success = ajouterObjet((Gouache) db.getContent(Gouache.gouacheDataFormat));
            else if (db.hasContent(Uniforme.uniformeDataFormat))
                success = ajouterObjet((Uniforme) db.getContent(Uniforme.uniformeDataFormat));
            else if (db.hasContent(ToucheDigicode.toucheDigicodeDataFormat))
                success = ajouterObjet((ToucheDigicode) db.getContent(ToucheDigicode.toucheDigicodeDataFormat));
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
    }
    public void vidermain(){
        slots = new ArrayList<>();
    }
}
