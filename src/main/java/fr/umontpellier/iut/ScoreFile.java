package fr.umontpellier.iut;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class ScoreFile extends Parent {
    private final String PATH_ROOT = System.getProperty("user.home") + "\\Documents" + File.separator + "EscapeGame S5" + File.separator + "gamescore";
    private final String PATH_FINAL = PATH_ROOT + File.separator + "score.txt";

    private StackPane scoreBoardGroup;

    public ScoreFile() {
        File f = new File(PATH_ROOT);
        if (!f.exists()){
           f.mkdirs();
        }
    }

    public void saveScore(Score score) {
        TreeSet<Score> allSavedScore = new TreeSet<>(Score::compareTo);
        File f = new File(PATH_FINAL);
        if (f.exists()) {
            for (Score score1 : readScore())
                if (score1 != null)
                    allSavedScore.add(score1);
            f.delete();
        }
        allSavedScore.add(score);

        ObjectOutputStream scoreFile = null;
        try {
            if (!new File(PATH_FINAL).exists()) {
                scoreFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(PATH_FINAL, true)));
            }
            else {
                scoreFile = new AppendingObjectOutputStream(new BufferedOutputStream(new FileOutputStream(PATH_FINAL, true)));
            }
            for (int i=0; i<10; i++) {
                scoreFile.writeObject(allSavedScore.pollFirst());
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (scoreFile != null) {
                try {
                    scoreFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ArrayList<Score> readScore() {
        ArrayList<Score> scoreArrayList = new ArrayList<>();
        ObjectInputStream scoreFile = null;
        try {
            scoreFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(PATH_FINAL)));
            while (true)
                scoreArrayList.add((Score) scoreFile.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            if (scoreFile != null) {
                try {
                    scoreFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return scoreArrayList;
    }

    public void display() {
        ArrayList<Score> scoreBoard = readScore();
        String scoreTextString = "";
        int i = 1;
        for (Score score : scoreBoard) {
            if (score != null)
                scoreTextString += i++ + "# " + score.getPseudo() + " : " + score.getNbOfTries() + " essaies, " + score.getStopWatch() + "\n";
        }

        Text scoreBoardTitle = new Text("Score Board");
        scoreBoardTitle.setFill(Color.WHITE);
        scoreBoardTitle.setFont(Font.font(50));

        Text scoreBoardScores = new Text(scoreTextString);
        scoreBoardScores.setFill(Color.WHITE);
        scoreBoardScores.setFont(Font.font(35));

        TilePane scoreBoardTilePane = new TilePane();
        scoreBoardTilePane.getChildren().addAll(scoreBoardTitle,scoreBoardScores);
        scoreBoardTilePane.setMaxWidth(Math.max(scoreBoardScores.getLayoutBounds().getMaxX(),scoreBoardTitle.getLayoutBounds().getMaxX()));

        scoreBoardGroup = new StackPane();
        scoreBoardGroup.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        scoreBoardGroup.setPrefSize(Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
        scoreBoardGroup.getChildren().addAll(scoreBoardTilePane);

        getChildren().add(scoreBoardGroup);
    }

    public void stopDisplay() {
        getChildren().remove(scoreBoardGroup);
    }
}
