package fr.umontpellier.iut.scene_ordi;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hack_obstacle_mouvant extends Parent {

    // attribut de position / taille / vitesse de l'obstacle
    private double posx;
    private double posy;
    private int width;
    private int height;
    private double vitesse;

    // attribut de direction
    private int direction;      // 1 = haut / 2 = droite / 3 = bas / 4 = gauche

    // attribut de sauvegarde de position / direction (utile pour le reset)
    private double posx_sauve;
    private double posy_sauve;
    private int direction_sauve;

    // attribut symbolisant les limites de deplacement de l'obstacle
    private double limite_haut;
    private double limite_droite;
    private double limite_bas;
    private double limite_gauche;

    // type de mouvement de l'objet
    private int mv_ligne;   // 1 = fait un mouvement en ligne / 2 = fait un mouvement en cercle vers la droite / 3 = faite un mouvement en cercle vers la gauche

    // symbolise le départ du mouvement
    private boolean go;

    // corp graphique de l'obstacle
    Rectangle corp;

    public Hack_obstacle_mouvant(double posx, double posy, int width, int height, double vitesse, int direction, double limite_haut, double limite_droite, double limite_bas, double limite_gauche, int mv_ligne) {

        // initialisation
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.vitesse = vitesse;
        this.direction = direction;
        this.limite_haut = limite_haut;
        this.limite_droite = limite_droite;
        this.limite_bas = limite_bas;
        this.limite_gauche = limite_gauche;
        this.mv_ligne = mv_ligne;
        go = false;

        // initialisation des sauvegardes
        posx_sauve = this.posx;
        posy_sauve = this.posy;
        direction_sauve = this.direction;

        // création du corp
        corp = new Rectangle(width,height, Color.RED);
        setTranslateX(posx);
        setTranslateY(posy);
        getChildren().add(corp);

        // Animation (test constammment les condition qui la composent)
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now)
            {

                // fait bouger l'obstacle si go == true
                if (go){
                    si_rencontre_limite();      // vérification / changement de direction si limite rencontré
                    avancer();                  // avance
                }
            }
        };
        timer.start();
    }


    // renvoie si l'objet avec ces propriété est dans la hitbox de l'obstacle
    public boolean isInHitbox(Hack_Perso perso){
        return ((perso.posx < posx + width)&&(perso.posx > posx - perso.width)&&(perso.posy < posy + height)&&(perso.posy > posy - perso.height));
    }

    // avance suivant sa direction
    public void avancer(){

        if (direction == 1){
            posy = posy - vitesse;
        }
        else if (direction == 2){
            posx = posx + vitesse;
        }
        else if (direction == 3){
            posy = posy + vitesse;
        }
        else if (direction == 4){
            posx = posx - vitesse;
        }
        setTranslateX(posx);
        setTranslateY(posy);
    }

    // verifie si rencontre une limite et change de direction si oui suivant son mode de deplacement
    public void si_rencontre_limite(){
        // si en ligne
        if (mv_ligne == 1){
            if (direction == 1 || direction == 3){
                if (posy <= limite_haut || posy >= limite_bas){
                    direction_oppose();
                }
            }
            else if (direction == 2 || direction == 4){
                if (posx <= limite_gauche || posx >= limite_droite) {
                    direction_oppose();
                }
            }
        }
        // si cercle montre
        else if (mv_ligne == 2) {
            if (direction == 1){
                if (posy <= limite_haut){
                    tourner_droite();
                }
            }
            if (direction == 2){
                if (posx >= limite_droite){
                    tourner_droite();
                }
            }
            if (direction == 3){
                if (posy >= limite_bas){
                    tourner_droite();
                }
            }
            if (direction == 4){
                if (posx <= limite_gauche){
                    tourner_droite();
                }
            }
        }
        // si cercle contraire montre
        else if (mv_ligne == 3){
            if (direction == 1){
                if (posy <= limite_haut){
                    tourner_gauche();
                }
            }
            if (direction == 2){
                if (posx >= limite_droite){
                    tourner_gauche();
                }
            }
            if (direction == 3){
                if (posy >= limite_bas){
                    tourner_gauche();
                }
            }
            if (direction == 4){
                if (posx <= limite_gauche){
                    tourner_gauche();
                }
            }
        }
    }

    //// fait changer l'objet de direction
    public void tourner_droite(){
        if (direction == 4){
            direction = 1;
        }
        else {
            direction = direction + 1;
        }
    }
    public void tourner_gauche(){
        if (direction == 1){
            direction = 4;
        }
        else {
            direction = direction - 1;
        }
    }
    public void direction_oppose(){
        if (direction == 1){
            direction = 3;
        }
        else if (direction == 2){
            direction = 4;
        }
        else if (direction == 3){
            direction = 1;
        }
        else if (direction == 4){
            direction = 2;
        }
    }

    // reset position et direction
    public void reset(){
        posx = posx_sauve;
        posy = posy_sauve;
        direction = direction_sauve;
        setTranslateX(posx);
        setTranslateY(posy);
    }

    // setter go

    public void setGo(boolean go) {
        this.go = go;
    }

    // setter vitesse (pour cheatcode)

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
}
