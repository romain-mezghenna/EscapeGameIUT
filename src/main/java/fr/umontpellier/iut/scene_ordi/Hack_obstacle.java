package fr.umontpellier.iut.scene_ordi;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hack_obstacle extends Parent {

    // position / taille de l'obstacle
    private double posx;
    private double posy;
    private int width;
    private int height;

    // corp graphique de l'obstacle
    Rectangle corp;

    public Hack_obstacle(double posx, double posy, int width, int height) {

        // initialisation position / taille
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;

        // création du corp de l'obstacle
        corp = new Rectangle(width,height, Color.GREEN);
        corp.setX(posx);
        corp.setY(posy);
        getChildren().add(corp);
    }

    // renvoie si le perso avec ces propriété est dans la hitbox de l'obstacle
    public boolean isInHitbox(Hack_Perso perso){
        return ((perso.posx < posx + width)&&(perso.posx > posx - perso.width)&&(perso.posy < posy + height)&&(perso.posy > posy - perso.height));
    }
}
