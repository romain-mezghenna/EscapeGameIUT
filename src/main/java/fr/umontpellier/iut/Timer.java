package fr.umontpellier.iut;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.sql.Time;

public class Timer extends Parent {

    private int dureeEnSec;
    private double screenheight, screenwidth;
    private Timeline timer;

    public Timer(int sec) {
        dureeEnSec = sec;

        // Get the screen dimensions and assign them to variables
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        screenheight = dimension.getHeight();
        screenwidth  = dimension.getWidth();

        // Create the timer's visual cue
        Rectangle timer = new Rectangle(0.75*screenwidth, 0.025*screenheight);
        timer.setFill(Color.GREEN);
        timer.setX(screenwidth / 8); timer.setY(screenheight / 1.05);

        // Animate the visual cue
        this.timer = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(timer.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(0), new KeyValue(timer.widthProperty(), 0.75*screenwidth)),
                new KeyFrame(Duration.seconds(0), new KeyValue(timer.xProperty(), screenwidth / 8)),
                new KeyFrame(Duration.seconds(dureeEnSec/6.0), new KeyValue(timer.fillProperty(), Color.YELLOWGREEN)),
                new KeyFrame(Duration.seconds(dureeEnSec/2.0), new KeyValue(timer.fillProperty(), Color.YELLOW)),
                new KeyFrame(Duration.seconds(dureeEnSec), new KeyValue(timer.fillProperty(), Color.RED)),
                new KeyFrame(Duration.seconds(dureeEnSec), new KeyValue(timer.widthProperty(), 0)),
                new KeyFrame(Duration.seconds(dureeEnSec), new KeyValue(timer.xProperty(), 0.5*screenwidth))
        );


        this.getChildren().add(timer);
    }

    public void resetTimer() {
        timer.stop();
        timer.play();
    }

    public Timeline getTimer() {
        return timer;
    }

}
