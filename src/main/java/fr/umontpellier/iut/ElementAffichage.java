package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public abstract class ElementAffichage  extends Parent {

    StackPane element;
    Text texte;

    public ElementAffichage(double x, double y, double haut, double larg, String text) {

        element = new StackPane();
        element.setTranslateX(x - larg/2); element.setTranslateY(y);

        Rectangle rct = new Rectangle(larg, haut);
        rct.setFill(Color.WHITESMOKE);

        texte = new Text(text);
        texte.setFont(new Font(20));
        texte.setWrappingWidth(larg);
        texte.setTextAlignment(TextAlignment.CENTER);

        element.getChildren().addAll(rct, texte);

        this.getChildren().addAll(element);
    }

    public void setTexte(String newText) {
        texte.setText(newText);
    }
}