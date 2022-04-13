package fr.umontpellier.iut.digicode;

import java.io.InputStream;

import javafx.scene.image.Image;

public class DenicheurDeFichiers {

    /**
     * Retourne une image en fonction du nom de fichier donné.
     * 
     * @param nomFichier nom du fichier qui doit être renvoyé
     * 
     * @return L'image, qui correspond au nom donnée.
     * 
     * @see javafx.scene.image.Image
     */
    public static Image getImage(String nomFichier) {
        return new Image(Digicode.class.getResourceAsStream("../../../../images/digicode/" + nomFichier));
    }

    /**
     * Retourne le fichier son qui correspon au nom de fichier donné.
     * 
     * @param nomFichier nom du fichier qui doit être renvoyé
     * 
     * @return Une instance de InputStream, qui correspond au nom du fichier son
     *         donné en paramètre.
     * 
     * @see InputStream
     */
    public static InputStream getFichierSon(String nomFichier) {
        return Digicode.class.getResourceAsStream("../../../../sons/digicode/" + nomFichier);
    }

    private DenicheurDeFichiers() {
        throw new IllegalStateException("Class utilitaire");
    }
}