package fr.umontpellier.iut.scene_ordi;

import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Random;

public class Hack_Pendu extends Parent {

    ArrayList<Hack_Pendu_Mot> showMot;

    ArrayList<Character> changeMot;
    ArrayList<Character> lettreDejaRentrer;


    public static int NB_ERREURS_MAX=10; //Le nombre d'erreur maximale toélérées
    public Hack_Pendu() {
        changeMot=new ArrayList<>();
        showMot = new ArrayList<>();
        lettreDejaRentrer = new ArrayList<>();

    }
    public void ajouter(Hack_Pendu_Mot p){
        showMot.add(p);
    }

    public String getval() {
        int nb=0;
        return showMot.get(nb).getMot();
    }

    public ArrayList<Character> getLettreDejaRentrer() {
        return lettreDejaRentrer;
    }

    public void setChangeMot(ArrayList<Character> changeMot1) {
        changeMot1=motAchercher();
        this.changeMot = changeMot1;
    }

    public ArrayList<Character> getChangeMot() {
        return changeMot;
    }

    public boolean lettreCorrecteur(char l) {
        boolean lc =false;
        for (int i=0;i<this.getval().length()-1;i++){
            if (l==this.getval().charAt(i)){
                lc = true;
                changeMot.set(i,l);
            }
        }
        if (!lc) {
            for (int i = 0; i < this.getval().length() - 1; i++) {
                if (!lettreDejaRentrer.contains(l)) {
                    lettreDejaRentrer.add(l);
                }
            }
        }

        return lc;
    }

    public boolean gagner(){

        for (int i=0;i<changeMot.size();i++){
            if (changeMot.get(i)!=getval().charAt(i)){
                return false;
            }
        }
        return true;
    }

    public boolean perdu(){
        return lettreDejaRentrer.size() != NB_ERREURS_MAX;
    }

    public ArrayList<Character> motAchercher() {



       // System.out.println("Voici le mot a trouver: ");

        String motADeviner = this.getval(); //On insère dans la variable motAdeviner

        int longueurMotAChercher = motADeviner.length(); //On stock dans cette variable la longueur du mot a Deviner.

        ArrayList<Character> tabMotAChercher= new ArrayList(longueurMotAChercher); //On créer un tableau de caractère de même taille que la longueur du mot a chercher

        for (int i = 0; i < motADeviner.length(); i++) {

            if (i == 0) { //On insére la première lettre du mot dans la case 0

                tabMotAChercher.add(motADeviner.charAt(i)) ;
            } else if (i == motADeviner.length() - 1) { //On insère la dernière lettres du mot dans la dernière case

                tabMotAChercher.add(motADeviner.charAt(i));


            } else {

                tabMotAChercher.add('-');//Pour le reste entre on insère des tirets
            }
        }
        return tabMotAChercher;

    }
}
