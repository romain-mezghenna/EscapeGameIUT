package fr.umontpellier.iut.digicode;

import fr.umontpellier.iut.Settings;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * <b>Notification est la classe représentant une notification au sien de la
 * scene du Digicode</b>
 * <p>
 * Une instance de Notification est caractérisé par l'information suivante :
 * <ul>
 * <li>Une rectangle</li>
 * </ul>
 * </p>
 * <p>
 * De plus cette classe extends les fonctionnalités de la classe Parent
 * </p>
 * 
 * @see javafx.scene.Parent
 * @see javafx.scene.shape.Rectangle
 * 
 * @author MathieuSoysal
 * @version 1.0
 */
public class Notification extends StackPane {

    /**
     * Le rectangle dans laquel s'affiche la notification. Ce rectangle n'est pas
     * modifiable.
     * 
     * @see Notification#Notification()
     * @see javafx.scene.shape.Rectangle
     */
    private Rectangle rectangle;

    /**
     * Constructeur Notification.
     * <p>
     * A la construction d'un objet Zéro, la notification n'est pas visible.
     * </p>
     * 
     * @param pseudo Le rectangle dans laquel s'affiche la notification
     * 
     * @see Notification#rectangle
     * @see javafx.scene.shape.Rectangle
     */
    public Notification() {
        setLayoutX(Settings.screenwidth * 0.1);
        setLayoutY(Settings.screenheight);
        rectangle = new Rectangle(Settings.screenwidth * 0.8, Settings.screenheight * 0.1);
        rectangle.setArcHeight(35);
        rectangle.setArcWidth(35);
        rectangle.setFill(Color.DARKGRAY);
    }

    /**
     * Affiche l'information en l'animant à l'écran sous forme de notification.
     * 
     * @param information l'information à afficher à l'écran
     * 
     * @see Notification#rectangle
     * @see Notification#afficher()
     */
    public void afficherInformation(String information) {
        getChildren().clear();
        getChildren().add(rectangle);
        Text textTemporaire = new Text(information);
        textTemporaire.autosize();
        textTemporaire.setFont(new Font(25));
        getChildren().add(textTemporaire);
        afficher();
    }

    /**
     * Annime l'affichage de la notification
     * 
     * @see javafx.animation.Timeline#Timeline(KeyFrame...)
     * @see javafx.animation.KeyFrame#KeyFrame(Duration, KeyValue...)
     * @see javafx.util.Duration#Duration(double)
     */
    private void afficher() {
        Timeline timeline = new Timeline(//
                new KeyFrame(Duration.ZERO, new KeyValue(opacityProperty(), 0), new KeyValue(translateYProperty(), 0)),
                new KeyFrame(new Duration(600), new KeyValue(opacityProperty(), 0.85)),
                new KeyFrame(new Duration(2000), new KeyValue(translateYProperty(), (rectangle.getHeight() + 10) * -1)),
                new KeyFrame(new Duration(10000), new KeyValue(translateYProperty(), (rectangle.getHeight() + 10) * -1),
                        new KeyValue(opacityProperty(), 0.86)),
                new KeyFrame(new Duration(12000), new KeyValue(translateYProperty(), 0),
                        new KeyValue(opacityProperty(), 0)));
        timeline.play();
    }

}