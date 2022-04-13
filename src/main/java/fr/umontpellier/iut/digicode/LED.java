package fr.umontpellier.iut.digicode;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

class LED extends Parent {
    private ImageView luminosite;
    private Timeline timeline = null;

    /**
     * 
     */
    public LED(String nom, double positionX, double positionY) {
        luminosite = new ImageView(DenicheurDeFichiers.getImage("led" + nom + ".png"));
        luminosite.setOpacity(0);
        getChildren().add(luminosite);

        setTranslateX(positionX);
        setTranslateY(positionY);
    }

    public void allumerEnClignotant() {
        stopActuelleTimeline();
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(//
                new KeyFrame(Duration.ZERO, new KeyValue(luminosite.opacityProperty(), 0)),
                new KeyFrame(new Duration(50), new KeyValue(luminosite.opacityProperty(), 0)),
                new KeyFrame(new Duration(60), new KeyValue(luminosite.opacityProperty(), 1)));
        timeline.setAutoReverse(false);
        timeline.setCycleCount(22);
        timeline.play();
    }

    public void faireClignoter(int lenteur) {
        stopActuelleTimeline();
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(//
                new KeyFrame(Duration.ZERO, new KeyValue(luminosite.opacityProperty(), 0.1)),
                new KeyFrame(new Duration(900. / (lenteur + 1)), new KeyValue(luminosite.opacityProperty(), 0.1)),
                new KeyFrame(new Duration(1000. / (lenteur + 1)), new KeyValue(luminosite.opacityProperty(), 1)));
        timeline.setAutoReverse(false);
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void etteindre() {
        stopActuelleTimeline();
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(//
                new KeyFrame(Duration.ZERO, new KeyValue(luminosite.opacityProperty(), 1)),
                new KeyFrame(new Duration(1000), new KeyValue(luminosite.opacityProperty(), 0)));
        timeline.setAutoReverse(false);
        timeline.setCycleCount(0);
        timeline.play();
    }

    public void allumer() {
        stopActuelleTimeline();
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(//
                new KeyFrame(Duration.ZERO, new KeyValue(luminosite.opacityProperty(), 0)),
                new KeyFrame(new Duration(1000), new KeyValue(luminosite.opacityProperty(), 1)));
        timeline.setAutoReverse(false);
        timeline.setCycleCount(0);
        timeline.play();
    }

    private void stopActuelleTimeline() {
        if (timeline != null)
            timeline.stop();
    }
}