package fr.umontpellier.iut.digicode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <b>ToucheInfo est la classe permetant d'afficher les raccourcies clavier des
 * touches à l'écran.</b>
 * <p>
 * Un objet ToucheInfo est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une liste de touches</li> etc.</li>
 * </ul>
 * </p>
 * 
 * @see Touche
 * 
 * @author MathieuSoysal
 * @version 1.0
 */
public class ToucheInfo extends Touche {

    /**
     * L'ensemble des touches que cette classe ToucheInfo peut afficher/cacher les
     * raccourcies clavier.
     * 
     * @see ToucheInfo#afficherRaccourcieClavierDesTouches()
     * @see ToucheInfo#addToucheAInformer(Touche...)
     * @see ToucheInfo#addTouchesAInformer(Collection)
     */
    private ArrayList<Touche> touchesAInformer;

    /**
     * Constructeur ToucheInfo.
     * <p>
     * A la construction d'un objet ToucheInfo, a liste des des touches à informer
     * est créée vide. De plus cette classe extends toute les fonctionnalité de la
     * classe {@link Touche}.
     * </p>
     * 
     * @param positionX position X de la touche
     * @param positionY position Y de la touche
     * 
     * @see Touche
     * @see ToucheInfo#touchesAInformer
     */
    public ToucheInfo(double positionX, double positionY) {
        super("Info", positionX, positionY);
        touchesAInformer = new ArrayList<>();
    }

    /**
     * Afficher à l'écran l'information sur le raccourcie clavier de chacune des
     * touches.
     * 
     * @see ToucheInfo#touchesAInformer
     * @see Touche
     * @see Touche#afficherRaccourcieClavier()
     */
    public void afficherRaccourcieClavierDesTouches() {
        for (Touche touche : touchesAInformer)
            touche.afficherRaccourcieClavier();
    }

    /**
     * Cache pour chacune des touches l'information sur le raccourcie clavier qui a
     * été affiché.
     * 
     * @see ToucheInfo#touchesAInformer
     * @see Touche
     * @see Touche#cacherRaccourcieClavier()
     */
    public void cacherRaccourcieClavierDesTouches() {
        for (Touche touche : touchesAInformer)
            touche.cacherRaccourcieClavier();
    }

    /**
     * Ajoute la/les touche(s) auquelle cette classe ToucheInfo peut afficher les
     * informations sur leur raccourcie clavier.
     * 
     * @param touchesAInformer
     * 
     * @return retourne {@code true} si les touches ont pus âtre ajouter avec
     *         succés.
     * 
     * @see Touche
     * @see ToucheInfo#touchesAInformer
     * @see java.util.AbstractCollection#addAll(java.util.Collection)
     */
    public boolean addTouchesAInformer(Touche... touchesAInformer) {
        return this.touchesAInformer.addAll(Arrays.asList(touchesAInformer));
    }

    /**
     * Ajoute les touches auquelle cette classe ToucheInfo peut afficher les
     * informations sur leur raccourcie clavier.
     * 
     * @param touchesAInformer
     * 
     * @return retourne {@code true} si les touches ont pus âtre ajouter avec
     *         succés.
     * 
     * @see Touche
     * @see ToucheInfo#touchesAInformer
     * @see java.util.AbstractCollection#addAll(java.util.Collection)
     */
    public boolean addTouchesAInformer(Collection<? extends Touche> touchesAInformer) {
        return this.touchesAInformer.addAll(touchesAInformer);
    }

    @Override
    public void appuyer() {
        super.appuyer();
        afficherRaccourcieClavierDesTouches();
    }

    @Override
    public void relacher() {
        super.relacher();
        cacherRaccourcieClavierDesTouches();
    }

}