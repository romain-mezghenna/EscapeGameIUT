package fr.umontpellier.iut;

import java.io.Serializable;
import java.util.Objects;

public class Score implements Serializable {
    private String pseudo;
    private int nbOfTries;
    private StopWatch stopWatch;
    private transient boolean pause;

    public Score() {
        pseudo = "";
        nbOfTries = 1;
        stopWatch = new StopWatch();
    }

    public void startGame() {
        stopWatch.start();
//        stopWatch.display();
        pause = false;
    }

    public void stopGame() {
        stopWatch.stop();
    }

    public void pauseGame() {
        pause = !pause;
        if (pause)
            stopWatch.pause();
        else
            stopWatch.resume();
    }


    public void tryAgain() {
        nbOfTries++;
    }

    @Override
    public String toString() {
        return "pseudo : " + pseudo + "\nnbOfTries : " + nbOfTries + "\nstopWatch : " + stopWatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return nbOfTries == score.nbOfTries &&
                Objects.equals(stopWatch, score.stopWatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nbOfTries, stopWatch);
    }

    public int compareTo(Score score) {
        int result = 0;
        if (nbOfTries<score.nbOfTries)
            result = -1;
        else if (nbOfTries>score.nbOfTries)
            result = 1;
        else {
            if (stopWatch.getElapsedTimeMillis()<score.stopWatch.getElapsedTimeMillis())
                result = -1;
            else if (stopWatch.getElapsedTimeMillis()>score.stopWatch.getElapsedTimeMillis())
                result = 1;
        }
        return result;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbOfTries() {
        return nbOfTries;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }
}
