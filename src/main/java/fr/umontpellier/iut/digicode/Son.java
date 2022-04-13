package fr.umontpellier.iut.digicode;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * <b>Son est la classe représentant un son jouer pendant la scene du
 * digicode</b>
 * <p>
 * Une instance de Son est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un objet Clip</li>
 * <li>Un nom</li>
 * <li>Un @{code boolean} sonPresent</li>
 * </ul>
 * </p>
 * <p>
 * De plus, un Zéro a une liste d'amis Zéro. Le membre pourra ajouter ou enlever
 * des amis à cette liste.
 * </p>
 * 
 * @see javax.sound.sampled.Clip
 * 
 * @author MathieuSoysal
 * @version 1.0
 */
class Son {
    /**
     * Le son de la class. Ce clip n'est pas modifiable.
     * 
     * @see javax.sound.sampled.Clip
     * @see Son#jouer()
     * @see Son#initSon()
     */
    private Clip clip;

    /**
     * Le nom du Son. Ce nom n'est pas modifiable.
     * 
     * @see Son#Son(String)
     * @see Son#initSon()
     */
    private String nom;

    /**
     * boolean indiquant la présence du fichier contenant le son.
     * 
     * @see Son#jouer()
     * @see Son#initSon()
     */
    private boolean sonPresent;

    /**
     * Initialise l'instance de Son.
     * 
     * @see Son#clip
     * @see Son#sonPresent
     */
    private void initSon() {
        InputStream input = DenicheurDeFichiers.getFichierSon("Son" + nom + ".wav");
        sonPresent = input != null;
        if (sonPresent)
            try {
                clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(input);
                clip.open(inputStream);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                System.err.println("Un problème est survenue lors de la recherche du fichier son");
                e.printStackTrace();
            }
    }

    /**
     * Constructeur Son.
     * 
     * @param nom Le nom (avec extension) du fichier son qui doit être retrouver.
     *            Près-requis extension .wav
     * 
     * @see Son#clip
     * @see Son#nom
     * @see Son#sonPresent
     */
    public Son(String nom) {
        this.nom = nom;
        initSon();
    }

    /**
     * Joue une fois le son dans la scene, si le fichier son est présent.
     * 
     * @see Son#clip
     * @see Son#sonPresent
     * @see javax.sound.sampled.DataLine#start()
     */
    public void jouer() {
        if (sonPresent) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

}