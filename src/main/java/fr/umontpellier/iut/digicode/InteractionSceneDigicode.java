package fr.umontpellier.iut.digicode;

/**
 * <b>InteractionSceneDigicode est la classe permetant d'intéragir avec la scène
 * du Digicode</b>
 * 
 * @see Digicode
 * 
 * @author MathieuSoysal
 */
public class InteractionSceneDigicode {
    /**
     * boolean qui indique si le Digicode a été validé ou non
     * 
     * @see InteractionSceneDigicode#digicodeEstValide()
     * @see InteractionSceneDigicode#validerLeDigicode()
     */
    private static boolean digicodeEstValide;

    /**
     * Retourne un boolean qui indique si le Digicode a été validé ou non
     * 
     * @return true si le Digicode a été validé, sinon false
     */
    public static boolean digicodeEstValide() {
        return digicodeEstValide;
    }

    /**
     * Cette méthode raméne à true {@value #digicodeEstValide} Cette méthode est
     * utilisé une fois la scène du Digicode finis.
     * 
     * @see InteractionSceneDigicode#digicodeEstValide
     */
    static void validerLeDigicode() {
        digicodeEstValide = true;
    }

    /**
     * Réinitialise toute les interactions eu avec la scène Digicode
     * 
     * @see InteractionSceneDigicode#digicodeEstValide
     */
    public static void reset(){
        digicodeEstValide = false;
    }

    private InteractionSceneDigicode() {
        throw new IllegalStateException("Class utilitaire");
    }
}