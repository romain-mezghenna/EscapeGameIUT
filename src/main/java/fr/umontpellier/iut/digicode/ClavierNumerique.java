package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Gouache;
import fr.umontpellier.iut.ToucheDigicode;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Collection;
import java.util.IdentityHashMap;

public class ClavierNumerique {
    private IdentityHashMap<Character, Touche> touchesClavier = new IdentityHashMap<>();

    /**
     * @param touchesClavier
     */
    public ClavierNumerique(double positionX, double positionY, GestionnaireSaisieMdp gestionnaireSaisieMdp){
        touchesClavier.put('1', new ToucheNumerique("1", positionX + 59, positionY + 101, gestionnaireSaisieMdp));
        touchesClavier.put('2', new ToucheNumerique("2", positionX + 59 + 67, positionY + 101, gestionnaireSaisieMdp));
        touchesClavier.put('3', new ToucheNumerique("3", positionX + 59 + 67 * 2, positionY + 101, gestionnaireSaisieMdp));
        touchesClavier.put('4', new ToucheNumerique("4", positionX + 59, positionY + 101 + 48, gestionnaireSaisieMdp));
        touchesClavier.put('5', new ToucheNumerique("5", positionX + 59 + 67, positionY + 101 + 49, gestionnaireSaisieMdp));
        touchesClavier.put('6', new ToucheNumerique("6", positionX + 59 + 67 * 2, positionY + 101 + 48, gestionnaireSaisieMdp));
        touchesClavier.put('7', new ToucheNumerique("7", positionX + 59, positionY + 101 + 48 * 2 + 2, gestionnaireSaisieMdp));
        touchesClavier.put('8', new ToucheNumerique("8", positionX + 59 + 67, positionY + 101 + 48 * 2 + 1, gestionnaireSaisieMdp));
        touchesClavier.put('9', new ToucheNumerique("9", positionX + 58 + 67 * 2, positionY + 101 + 48 * 2 + 1, gestionnaireSaisieMdp));
        touchesClavier.put('*', new ToucheNumerique("Etoile", positionX + 59, positionY + 101 + 50 * 3 + 1, gestionnaireSaisieMdp));
        touchesClavier.put('0', new ToucheNumerique("0", positionX + 59 + 67, positionY + 101 + 49 * 3 + 1, gestionnaireSaisieMdp));
        touchesClavier.put('#', new ToucheNumerique("Sharp", positionX + 59 + 67 * 2 + 1, positionY + 101 + 49 * 3 + 1, gestionnaireSaisieMdp));
        touchesClavier.get('6').setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof ToucheDigicode && dragEvent.getDragboard().hasContent(ToucheDigicode.toucheDigicodeDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        touchesClavier.get('6').setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(ToucheDigicode.toucheDigicodeDataFormat)) {
                System.out.println("test");
                touchesClavier.get('6').setManquant();
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
        touchesClavier.get('9').setOnDragOver(dragEvent -> {
            if (dragEvent.getGestureSource() instanceof ToucheDigicode && dragEvent.getDragboard().hasContent(ToucheDigicode.toucheDigicodeDataFormat)) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        touchesClavier.get('9').setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasContent(ToucheDigicode.toucheDigicodeDataFormat)) {
                System.out.println("test 2");
                touchesClavier.get('9').setManquant();
                success = true;
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
    }

    /**
     * @param key
     * @return
     * @see java.util.IdentityHashMap#get(java.lang.Object)
     */

    public Touche identifieTouche(char key) {
        return touchesClavier.get(key);
    }

    /**
     * @return
     * @see java.util.IdentityHashMap#values()
     */

    public Collection<Touche> getTouches() {
        return touchesClavier.values();
    }

}