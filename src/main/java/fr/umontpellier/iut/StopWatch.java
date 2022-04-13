package fr.umontpellier.iut;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.Objects;

public class StopWatch extends Parent implements Serializable {
    private transient long startTime;
    private transient long stopTime;
    private transient boolean running;
    private long elapsedTimeMillis;

    private transient Text stopwatchText;
    private transient Timeline stopwatchRunning;
    private transient HBox stopwatchDisplay;

    public StopWatch() {
        startTime = 0;
        this.stopTime = 0;
        running = false;
        elapsedTimeMillis = 0;

        stopwatchText = new Text("00:00:00");
        stopwatchText.setFont(Font.font("Agency FB",50));
        stopwatchText.setFill(Color.WHITE);

        stopwatchRunning = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> change()));
        stopwatchRunning.setCycleCount(Timeline.INDEFINITE);
        stopwatchRunning.setAutoReverse(false);
        stopwatchRunning.play();

        stopwatchDisplay = new HBox(30);
        stopwatchDisplay.setAlignment(Pos.CENTER);
        stopwatchDisplay.getChildren().add(stopwatchText);
        stopwatchDisplay.setBackground(new Background(new BackgroundFill(Color.rgb(31,31,31),new CornerRadii(15), Insets.EMPTY)));
        stopwatchDisplay.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(5))));
        stopwatchDisplay.setPrefSize(150,75);

        setTranslateX(Screen.getPrimary().getBounds().getMaxX()/2-75);
        setTranslateY(5);
        getChildren().add(stopwatchDisplay);
    }

    public void start() {
       startTime = System.nanoTime();
       running = true;
    }

    public void stop() {
        stopTime = System.nanoTime();
        elapsedTimeMillis = (stopTime - startTime)/1000000;
        running = false;
        stopwatchRunning.stop();
        stopDisplay();
    }

    public void pause() {
        stop();
    }

    public void resume() {
        startTime = System.nanoTime() - elapsedTimeMillis*1000000;
        running = true;
        stopwatchRunning.play();
    }

    public long getElapsedTimeMillis() {
        return running? elapsedTimeMillis = (System.nanoTime() - startTime)/1000000 : elapsedTimeMillis;
    }

    public long getElapsedTimeSecs() {
        return getElapsedTimeMillis()/1000;
    }

    @Override
    public String toString() {
        return (int)getElapsedTimeSecs()/3600 + " h " + (int)(getElapsedTimeSecs()%3600)/60 + " min " + (int)(getElapsedTimeSecs()%3600)%60 + " sec";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopWatch)) return false;
        StopWatch stopWatch = (StopWatch) o;
        return elapsedTimeMillis == stopWatch.elapsedTimeMillis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(elapsedTimeMillis);
    }

    private void change() {
       stopwatchText.setText((int)getElapsedTimeSecs()/3600 + ":" + (int)(getElapsedTimeSecs()%3600)/60 + ":" + (int)(getElapsedTimeSecs()%3600)%60);
    }

    public void stopDisplay() {
        getChildren().remove(stopwatchDisplay);
    }
}
