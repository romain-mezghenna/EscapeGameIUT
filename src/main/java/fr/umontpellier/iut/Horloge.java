package fr.umontpellier.iut;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Horloge extends Parent {
    private double translateX;
    private double translateY;
    private int heures;
    private int minutes;
    private Text horlogeAffiche;
    private Timeline heure;
    public Montre montre;


    public Horloge(double tX, double tY, Montre m){
        heures = 0;
        minutes = 0;
        horlogeAffiche = new Text();
        montre = m;

        translateX = tX;
        translateY = tY;


        //Timeline Horloge 1 sec -> 5 minutes dans le jeu
        heure = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            if(minutes < 55){
                minutes+=5;
            }else{
                heures+=1;
                minutes = 0;
            }

            if(heures == 23 && minutes == 55){
                heures = 0;
                minutes = 0;
            }
            if(m.isHasMontre()) { // Affiche si suelement il a trouve la montre
                horlogeAffiche.setText((heures < 10 ? "0" + heures : heures) + " : " + (minutes < 10 ? "0" + minutes : minutes));

                Prisonnier.estPresent();
            }
        }));
        heure.setCycleCount(Animation.INDEFINITE);
        heure.play();

        horlogeAffiche.setFont(new Font(32));
        horlogeAffiche.setFill(Color.RED);
        horlogeAffiche.setX(25);
        horlogeAffiche.setY(45);


        this.getChildren().add(horlogeAffiche);
        this.setTranslateX(tX);
        this.setTranslateY(tY);


    }

    public int getHeures() {
        return heures;
    }

    public void pause() {
        if (heure.getStatus() == Animation.Status.RUNNING)
            heure.stop();
        else
            heure.play();
    }
}
