package fr.umontpellier.iut;

import fr.umontpellier.iut.digicode.SceneDigicode;
import fr.umontpellier.iut.scene_ordi.SuperSceneOrdi;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.stage.Screen;

import javax.sound.sampled.*;
import java.io.IOException;

public class Controller extends Parent {
    public static Group group = new Group();
    public static SceneCellule cell1 = new SceneCellule();
    public static CouloirPrincipale corridor = new CouloirPrincipale();
    public static Laverie laverie = new Laverie();
    public static Librairie librairie = new Librairie();
    public static Parcking parking = new Parcking();
    public static Hall hall = new Hall();
    public static PorteRenforcee portes = new PorteRenforcee();
    public static SceneGarde scenegarde = new SceneGarde();
    public static SuperSceneOrdi ordi = new SuperSceneOrdi();
    public static SceneDigicode digicodechiffre = new SceneDigicode();
    public static MainCharacter character = new MainCharacter();
    public static Inventory handInv = new Inventory(1,200);

    public static Relance wasted = new Relance();
    public static EcrandeFin fin = new EcrandeFin();

    public static StartMenu startMenu = new StartMenu();

    public static CheatCode cheatCode = new CheatCode();


    public static Montre montre = new Montre();
    public static Horloge horloge = new Horloge(Settings.screenwidth*0.9,Settings.screenheight*0.04,montre);
    public static SceneCellulePrisonnier sceneCellulePrisonnier = new SceneCellulePrisonnier(Settings.screenheight,Settings.screenwidth,horloge);

    public static Score currentScore = new Score();
    public static ScoreFile scoreFile = new ScoreFile();

    //pause
    private static Pause pause = new Pause();

    public static void startGame(Niveaux niveaux) {
        currentScore = new Score();
        currentScore.startGame();
        group.getChildren().remove(niveaux);
        group.getChildren().addAll(character,handInv,horloge,cheatCode);
        placeHandInv();
        group.getChildren().add(0,cell1);
    }

    public static void transitions(Niveaux n1,Niveaux suivant ){

        if (suivant.equals(scenegarde)) {
                if (character.isGuard()) {
                    group.getChildren().remove(n1);
                    group.getChildren().add(0, suivant);
                    SceneGarde.lancertimer();
                } else {
                    reset("Vous n'avez pas l'uniforme de garde !");
                }
            } else {
                group.getChildren().remove(n1);
                group.getChildren().add(0, suivant);
            }
        }


    public static void endGame() {
        group.getChildren().clear();
        group.getChildren().add(fin);
        currentScore.stopGame();
        scoreFile.saveScore(currentScore);
        fin.getLescredits().creditsRoll();
        Clip sonFinduJeu = null;
        try {
            sonFinduJeu = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Controller.class.getResourceAsStream("/sons/sonFinduJeu.wav"));
            sonFinduJeu.open(inputStream);
            sonFinduJeu.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(32500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Clip finalSonFinduJeu = sonFinduJeu;
        sleeper.setOnSucceeded(workerStateEvent -> {
            finalSonFinduJeu.stop();
            group.getChildren().remove(fin);
            group.getChildren().add(startMenu);
        });
        new Thread(sleeper).start();
        resetAttributes();

    }

    private static void placeHandInv() {
        handInv.setTranslateX(Screen.getPrimary().getBounds().getMaxX()-215);
        handInv.setTranslateY(Screen.getPrimary().getBounds().getMaxY()-215);
    }

    public static void pause() {
        Controller.currentScore.pauseGame();
        horloge.pause();

        if (group.getChildren().contains(pause)) {
            group.getChildren().remove(pause);
        } else {
            group.getChildren().add(pause);
        }
    }

    public static void reset(String string){
        group.getChildren().add(wasted);

        try {
            Clip nulgermain = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Controller.class.getResourceAsStream("/sons/wasted.wav"));
            nulgermain.open(inputStream);
            nulgermain.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            wasted.setMessage(string);
        }

        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(workerStateEvent -> {
            resetAttributes();
            currentScore.tryAgain();
            group.getChildren().clear();
            group.getChildren().addAll(character,handInv,horloge,cheatCode);
            group.getChildren().add(0, cell1);
           /* System.out.println(group.getChildren());*/
        });
        new Thread(sleeper).start();
    }

    private static void resetAttributes() {
        cell1 = new SceneCellule();
        corridor = new CouloirPrincipale();
        sceneCellulePrisonnier = new SceneCellulePrisonnier(Settings.screenheight,Settings.screenwidth,horloge);
        hall = new Hall();
        laverie = new Laverie();
        librairie = new Librairie();
        scenegarde = new SceneGarde();
        digicodechiffre= digicodechiffre.reset();
        portes=new PorteRenforcee();
        ordi=new SuperSceneOrdi();
        parking = new Parcking();


        scenegarde.setDigiCracked(false);

//        group.getChildren().removeAll(handInv,character,horloge);
        handInv= new Inventory(1,200);
        character = new MainCharacter();
//        group.getChildren().addAll(handInv,character);
        Controller.placeHandInv();

    }
    public static void resetsoft(String string){
        group.getChildren().add(wasted);

        try {
            Clip nulgermain = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Controller.class.getResourceAsStream("/sons/digicode/SonElectrique.wav"));
            nulgermain.open(inputStream);
            nulgermain.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        if (string != null) {
            wasted.setMessage(string);
        }
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(workerStateEvent -> {
            currentScore.tryAgain();
            group.getChildren().remove(0);
            group.getChildren().remove(wasted);
            group.getChildren().add(0, cell1);
            /* System.out.println(group.getChildren());*/
        });
        new Thread(sleeper).start();
    }
}
