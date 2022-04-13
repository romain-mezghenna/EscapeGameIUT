package fr.umontpellier.iut;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Porteseule extends Parent {
    private boolean ouverte;
    private boolean droite;
    private ImageView degauche;


    public Porteseule(boolean droite) {
        if(!droite) {
            this.ouverte = false;
            this.droite=false;
            degauche = new ImageView(new Image(Hall.class.getResourceAsStream("/images/portegauche.png")));
            this.getChildren().add(degauche);
            degauche.setX(Settings.screenwidth * 0.141);
            degauche.setY(Settings.screenheight * 0.153);
        }
        else
        {
            this.ouverte=true;
            this.droite=true;
            ImageView dedroite = new ImageView(new Image(Hall.class.getResourceAsStream("/images/portedroite.png")));
            this.getChildren().add(dedroite);
            dedroite.setX(Settings.screenwidth * 0.615);
            dedroite.setY(Settings.screenheight * 0.15);
        }

    }
    public void setOuverte() {
        this.ouverte = true;
    }
    public boolean getOuverte(){
        return ouverte;
    }

    public void printClosedMessage() {
        Text text = new Text("Porte Fermé");
        text.setFill(Color.BLACK);
        text.setFont(Font.font(35));
        getChildren().add(text);
        text.setX(Settings.screenwidth * 0.141+degauche.getLayoutBounds().getMaxX()/2-text.getLayoutBounds().getWidth());
        text.setY(Settings.screenheight * 0.153+degauche.getLayoutBounds().getMaxY()/2-text.getLayoutBounds().getHeight());
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(workerStateEvent -> this.getChildren().remove(text));
        new Thread(sleeper).start();
    }

}
