package fr.umontpellier.iut.scene_ordi;

import fr.umontpellier.iut.Controller;
import fr.umontpellier.iut.Porteseule;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.Dimension;
import java.util.ArrayList;


public class Ordi_SceneBureau extends Parent {

    // attribut du bureau
    ImageView btn_back;
    ImageView bg_bureau;
    ImageView barre_ubuntu;

    // icone du bureau (a gauche)
    ImageView icone_terminal;
    ImageView icone_firefox;
    ImageView icone_sublim;
    ImageView icone_papier;
    ImageView icone_doc;
    ImageView icone_discord;
    ImageView icone_aide;

    // attributs pour le mini jeu du pendu
    Group gr_pendu;
    ImageView fenetre_pendu;
    ImageView croix_pendu;
    TextField lettre_pendu;
    Label label_mot_pendu;
    Label label_mot_pendu_deja_trouve;
    Hack_Pendu pendu;
    Hack_Pendu_Mot motpendu;
    ArrayList motAChercher, motDejaTrouver = new ArrayList();
    Label txt_fin_pendu;


    // attributs pour la fenetre du terminal
    Group gr_terminal;
    ImageView terminal;
    ImageView croix_terminal;
    TextField txt_hack;

    // fenetre du hack (apres le terminal)
    Group gr_hack;
    ImageView fenetre_hack;
    ImageView croix_hack;

    //// le mini jeu de hack
    // element pour l'affichage
    Group gr_jeu;
    Rectangle fond_jeu;
    Hack_Perso monperso;
    Hack_finlevel finlevel;
    ImageView aide;
    Label txt_aide;
    Label txt_aide2;
    Label txt_fin;
    TextField txt_cheat;

    // variable dans la gestion des niveau
    private int numlevel = 1;
    private boolean bol_finlevel;
    private boolean go_level1 = false;
    private boolean go_level2 = false;
    private boolean go_level3 = false;
    private boolean go_level4 = false;
    private boolean go_dernier_level = false;
    private boolean fini = false;
    private boolean vraimentfini = false;

    // boolean d'ouverture de la porte (true si hack réussi)
    private boolean ouverture_porte = false;

    // 2 elements du dernier niveau modifiable
    Hack_obstacle_mouvant lv5_mv1;
    Hack_obstacle_mouvant lv5_mv2;


    // variable de transitions
    public boolean go_salleordi;


    // constructeur de ma scene
    public Ordi_SceneBureau(){

        // déclaration des dimensions de l'écran
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double screenheight = dimension.getHeight();
        double screenwidth  = dimension.getWidth();

        // initialisation des variables de transitions
        go_salleordi = false;

        ////// création objets / modif des objets :

        //// pour bureau :

        // bouton de transition vers salle ordi
        btn_back = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/bouton_power.png")));
        btn_back.setTranslateX(screenwidth - btn_back.getFitWidth() - 29);

        // barre de tâche a gauche
        barre_ubuntu = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/barre_droite_ubuntu.png")));
        barre_ubuntu.setFitHeight(screenheight);

        // icones
        icone_terminal = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-terminal.png")));
        icone_terminal.setTranslateY(4);
        icone_firefox = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-firefox.png")));
        icone_firefox.setTranslateY(60);
        icone_papier = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-papier.png")));
        icone_papier.setTranslateY(180);
        icone_doc = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-doc.png")));
        icone_doc.setTranslateY(240);
        icone_sublim = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-sublim.png")));
        icone_sublim.setTranslateY(300);
        icone_discord = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-discord.png")));
        icone_discord.setTranslateY(120);
        icone_aide = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/icone-aide.png")));
        icone_aide.setTranslateY(360);

        // background
        bg_bureau = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/fond-ubuntu.png")));
        bg_bureau.setFitHeight(screenheight);
        bg_bureau.setFitWidth(screenwidth);


        //// pour les autres fenetre :

        // c'est la billal       // ici  // ici       // ici  // ici       // ici  // ici       // ici  // ici       // ici  // ici       // ici  // ici       // ici  // ici       // ici  // ici
        // fenetre pendu
        gr_pendu = new Group();
        gr_pendu.setVisible(false);
        gr_pendu.setTranslateX(screenwidth/2 - 250);
        gr_pendu.setTranslateY(screenheight/2/2/2/2);

        pendu = new Hack_Pendu();
        motpendu = new Hack_Pendu_Mot("Cheatcode");
        pendu.ajouter(motpendu);

        ArrayList ui = new ArrayList<>();
        pendu.setChangeMot(ui);
        motAChercher = pendu.motAchercher();
        motDejaTrouver = pendu.getLettreDejaRentrer();

        fenetre_pendu = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/fenetre-hack.png")));
        croix_pendu = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/croix-terminal.png")));
        croix_pendu.setTranslateY(3);
        croix_pendu.setTranslateX(890);
        fond_jeu = new Rectangle(914, 542, Color.BLACK);
        fond_jeu.setTranslateY(30);

        lettre_pendu = new TextField();
        lettre_pendu.setTranslateX(450);
        lettre_pendu.setTranslateY(150);
        lettre_pendu.setStyle("-fx-background-color:TRANSPARENT; -fx-text-fill: white; -fx-font-family: monospace; -fx-font-size: 16pt;");
        label_mot_pendu = new Label(motAChercher.toString());
        label_mot_pendu.setTranslateX(300);
        label_mot_pendu.setTranslateY(50);
        label_mot_pendu.setStyle("-fx-background-color:TRANSPARENT; -fx-text-fill: green; -fx-font-family: monospace; -fx-font-size: 16pt;");
        label_mot_pendu_deja_trouve = new Label(motDejaTrouver.toString());
        label_mot_pendu_deja_trouve.setTranslateX(450);
        label_mot_pendu_deja_trouve.setTranslateY(300);
        label_mot_pendu_deja_trouve.setStyle("-fx-background-color:TRANSPARENT; -fx-text-fill: red; -fx-font-family: monospace; -fx-font-size: 16pt;");

        txt_fin_pendu = new Label("code:\n    Cheatcode");
        txt_fin_pendu.setTranslateX(150);
        txt_fin_pendu.setTranslateY(20);
        txt_fin_pendu.setStyle("-fx-text-fill: aliceblue; -fx-font-size: 16pt; -fx-font-family: monospace");
        txt_fin_pendu.setVisible(false);



        // jusque la    // jusque la        // jusque la    // jusque la        // jusque la    // jusque la        // jusque la    // jusque la        // jusque la    // jusque la        // jusque la
        // ajout des objet a la fenetre :
        gr_pendu.getChildren().addAll(fenetre_pendu,fond_jeu,croix_pendu, lettre_pendu, label_mot_pendu, label_mot_pendu_deja_trouve,txt_fin_pendu);


        // fenetre terminal
        gr_terminal = new Group();
        gr_terminal.setTranslateX(screenwidth/2 - 367);
        gr_terminal.setTranslateY(screenheight/2 - 232);
        gr_terminal.setVisible(false);

        terminal = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/terminal.png")));

        croix_terminal = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/croix-terminal.png")));
        croix_terminal.setTranslateY(3);
        croix_terminal.setTranslateX(710);

        txt_hack = new TextField();
        txt_hack.setTranslateY(194);
        txt_hack.setTranslateX(392);
        txt_hack.setStyle("-fx-background-color:TRANSPARENT; -fx-text-fill: white; -fx-font-family: monospace; -fx-font-size: 11pt;");
        txt_hack.setPrefWidth(350);

        gr_terminal.getChildren().addAll(terminal, croix_terminal, txt_hack);

        //// hack:

        // fenetre hack:
        gr_hack = new Group();
        gr_hack.setTranslateX(screenwidth/2 - 457);
        gr_hack.setTranslateY(screenheight/2 - 286);
        gr_hack.setVisible(false);

        fenetre_hack = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/fenetre-hack.png")));
        croix_hack = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/croix-terminal.png")));
        croix_hack.setTranslateY(3);
        croix_hack.setTranslateX(890);

        // jeu:
        gr_jeu = new Group();
        fond_jeu = new Rectangle(914, 542, Color.BLACK);
        fond_jeu.setTranslateY(30);
        monperso = new Hack_Perso();
        finlevel = new Hack_finlevel();

        aide = new ImageView(new Image(Ordi_SceneBureau.class.getResourceAsStream("/img/aide-hack.png")));
        aide.setTranslateX(900);
        aide.setTranslateY(35);

        txt_aide = new Label("Allez vers le fichier pour le hacker et enchainez les niveaux...");
        txt_aide.setTranslateX(500);
        txt_aide.setTranslateY(32);
        txt_aide.setStyle("-fx-text-fill: aliceblue;");
        txt_aide.setVisible(false);

        txt_aide2 = new Label("      VOUS      \n(el famoso virus)\n        |        \n        V");
        txt_aide2.setTranslateX(70);
        txt_aide2.setTranslateY(200);
        txt_aide2.setStyle("-fx-text-fill: aliceblue;");
        txt_aide2.setVisible(false);

        txt_cheat = new TextField();
        txt_cheat.setTranslateY(32);
        txt_cheat.setTranslateX(700);
        txt_cheat.setPromptText("Cheat...");
        txt_cheat.setStyle("-fx-background-color:WHITE; -fx-text-fill: black; -fx-font-family: monospace; -fx-font-size: 11pt;");
        txt_cheat.setVisible(false);

        txt_fin = new Label("GG la porte a été piratée !!! \n      Elle s'est ouverte...");
        txt_fin.setTranslateX(30);
        txt_fin.setTranslateY(130);
        txt_fin.setStyle("-fx-text-fill: aliceblue; -fx-font-size: 50px; -fx-font-family: monospace");
        txt_fin.setVisible(false);


        // ajout des elements au groupe du hack
        gr_jeu.getChildren().addAll(fond_jeu, finlevel.corp_back, monperso,finlevel.corp_front, txt_fin);
        gr_jeu.getChildren().add(finlevel);


        //// niveau :
        // level 1
        Group lv1 = new Group();
        lv1.setVisible(false);

        Hack_obstacle lv1_m1 = new Hack_obstacle(300,30,20,442);
        lv1.getChildren().add(lv1_m1);
        Hack_obstacle lv1_m2 = new Hack_obstacle(400,130,20,442);
        lv1.getChildren().add(lv1_m2);
        Hack_obstacle lv1_m3 = new Hack_obstacle(500,30,20,442);
        lv1.getChildren().add(lv1_m3);
        Hack_obstacle lv1_m4 = new Hack_obstacle(600,130,20,442);
        lv1.getChildren().add(lv1_m4);
        Hack_obstacle lv1_m5 = new Hack_obstacle(700,30,20,442);
        lv1.getChildren().add(lv1_m5);

        //// level 2
        Group lv2 = new Group();
        lv2.setVisible(false);

        Hack_obstacle lv2_m1 = new Hack_obstacle(300,130,20,282);
        lv2.getChildren().add(lv2_m1);
        Hack_obstacle lv2_m2 = new Hack_obstacle(360,190,20,282);
        lv2.getChildren().add(lv2_m2);
        Hack_obstacle lv2_m3 = new Hack_obstacle(420,130,20,282);
        lv2.getChildren().add(lv2_m3);
        Hack_obstacle lv2_m4 = new Hack_obstacle(480,190,20,282);
        lv2.getChildren().add(lv2_m4);
        Hack_obstacle lv2_m5 = new Hack_obstacle(540,130,20,282);
        lv2.getChildren().add(lv2_m5);
        Hack_obstacle lv2_m8 = new Hack_obstacle(600,190,20,282);
        lv2.getChildren().add(lv2_m8);
        Hack_obstacle lv2_m9 = new Hack_obstacle(660,130,20,282);
        lv2.getChildren().add(lv2_m9);
        Hack_obstacle lv2_m6 = new Hack_obstacle(300,130,614,20);
        lv2.getChildren().add(lv2_m6);
        Hack_obstacle lv2_m7 = new Hack_obstacle(300,452,614,20);
        lv2.getChildren().add(lv2_m7);

        //// level 3
        Group lv3 = new Group();
        lv3.setVisible(false);

        // mur de base (3 cercles concentriques + deux barriere en haut et en bas)
        Hack_obstacle lv3_m1 = new Hack_obstacle(237,81,440-20,20);        // exterieur haut
        lv3.getChildren().add(lv3_m1);
        Hack_obstacle lv3_m2 = new Hack_obstacle(237,501,440-20,20);       // exterieur bas
        lv3.getChildren().add(lv3_m2);
        Hack_obstacle lv3_m3 = new Hack_obstacle(237,81,20,180);        // exterieur gauche haut
        lv3.getChildren().add(lv3_m3);
        Hack_obstacle lv3_m4 = new Hack_obstacle(237,341,20,180);       // exterieur gauche bas
        lv3.getChildren().add(lv3_m4);
        Hack_obstacle lv3_m5 = new Hack_obstacle(657-20,81,20,180);        // exterieur droite haut
        lv3.getChildren().add(lv3_m5);
        Hack_obstacle lv3_m6 = new Hack_obstacle(657-20,341,20,180);       // exterieur droite bas
        lv3.getChildren().add(lv3_m6);
        Hack_obstacle lv3_m7 = new Hack_obstacle(447,30,20,51);         // empeche de gruger en haut
        lv3.getChildren().add(lv3_m7);
        Hack_obstacle lv3_m8 = new Hack_obstacle(447,521,20,51);        // empeche de gruger en bas
        lv3.getChildren().add(lv3_m8);
        Hack_obstacle lv3_m11 = new Hack_obstacle(237+80+180-20,81+20+60,100,20);      // milieu haut
        lv3.getChildren().add(lv3_m11);
        Hack_obstacle lv3_m12 = new Hack_obstacle(237+80,81+180+180-20,280-20,20); // milieu bas
        lv3.getChildren().add(lv3_m12);
        Hack_obstacle lv3_m13 = new Hack_obstacle(237+80,81+20+60,20,280-100);          // milieu gauche
        lv3.getChildren().add(lv3_m13);
        Hack_obstacle lv3_m14 = new Hack_obstacle(237+80+280-20-20,81+20+60+100,20,180);   // milieu droite
        lv3.getChildren().add(lv3_m14);
        Hack_obstacle lv3_m9 = new Hack_obstacle(237+20+60+20+60,81+20+60+20+60,280-160-20,20);      // interieur haut
        lv3.getChildren().add(lv3_m9);
        Hack_obstacle lv3_m15 = new Hack_obstacle(237+20+60+20+60,81+20+60+20+60+80,20,120);      // interieur gauche
        lv3.getChildren().add(lv3_m15);
        Hack_obstacle lv3_m16 = new Hack_obstacle(237+20+60+20+60+100-20,81+20+60+20+60,20,280-160);     // interieur droite
        lv3.getChildren().add(lv3_m16);

        // mur de blockage
        Hack_obstacle lv3_m17 = new Hack_obstacle(237,341,100,20);       // bloc gauche ext. 1
        lv3.getChildren().add(lv3_m17);
        Hack_obstacle lv3_m18 = new Hack_obstacle(237+20+60+20+60,81,20,100);      // bloc haut ext. 1
        lv3.getChildren().add(lv3_m18);
        Hack_obstacle lv3_m19 = new Hack_obstacle(237+20+60,81+20+60+20+60,100,20);      // bloc milieu gauche 1
        lv3.getChildren().add(lv3_m19);
        Hack_obstacle lv3_m20 = new Hack_obstacle(237+20+60+80+100-20,81+20+60,20,100);      // bloc milieu haut 1
        lv3.getChildren().add(lv3_m20);
        Hack_obstacle lv3_m21 = new Hack_obstacle(237+20+60+20+60+80+100-20,81+20+60+20+60,100,20);      // bloc ext droit 1
        lv3.getChildren().add(lv3_m21);


        //// level 4 :
        Group lv4 = new Group();
        lv4.setVisible(false);

        // murs mouvants
        Hack_obstacle_mouvant lv4_mv1 = new Hack_obstacle_mouvant(237,216,20,20,2,2,216,357,336,237,2);
        lv4.getChildren().add(lv4_mv1);
        Hack_obstacle_mouvant lv4_mv2 = new Hack_obstacle_mouvant(277,256,20,20,2,2,256,317,296,277,2);
        lv4.getChildren().add(lv4_mv2);
        Hack_obstacle_mouvant lv4_mv3 = new Hack_obstacle_mouvant(257,236,20,20,2,2,236,337,316,257,2);
        lv4.getChildren().add(lv4_mv3);

        Hack_obstacle_mouvant lv4_mv4 = new Hack_obstacle_mouvant(407,246,60,20,2,3,216,900,336,0,1);
        lv4.getChildren().add(lv4_mv4);

        Hack_obstacle_mouvant lv4_mv5 = new Hack_obstacle_mouvant(507,306,60,20,2,1,216,900,336,0,1);
        lv4.getChildren().add(lv4_mv5);

        // murs normaux
        Hack_obstacle lv4_m1 = new Hack_obstacle(237,196,677,20);
        lv4.getChildren().add(lv4_m1);
        Hack_obstacle lv4_m2 = new Hack_obstacle(237,356,677,20);
        lv4.getChildren().add(lv4_m2);


        //// level 5 :
        Group lv5 = new Group();
        lv5.setVisible(false);

        // murs mouvants
         lv5_mv1 = new Hack_obstacle_mouvant(300,30,20,300,12,2,0,700,0,200,1);
        lv5.getChildren().add(lv5_mv1);
         lv5_mv2 = new Hack_obstacle_mouvant(600,272,20,300,12,4,0,700,0,200,1);
        lv5.getChildren().add(lv5_mv2);

        // ajout des elements du jeu au sur-groupe gr_jeu
        gr_jeu.getChildren().addAll(lv1, lv2, lv3, lv4, lv5);
        gr_jeu.getChildren().addAll(txt_aide2, txt_cheat, aide,txt_aide);

        //ajout du gr jeu au gr hack
        gr_hack.getChildren().addAll(fenetre_hack, croix_hack);
        gr_hack.getChildren().add(gr_jeu);

        // ajout des objets a la fenetre
        getChildren().add(bg_bureau);
        getChildren().add(barre_ubuntu);
        getChildren().addAll(icone_doc, icone_firefox, icone_papier, icone_sublim, icone_discord, icone_aide);
        getChildren().add(icone_terminal);
        getChildren().add(gr_pendu);
        getChildren().add(gr_terminal);
        getChildren().add(gr_hack);
        getChildren().add(btn_back);


        //////////////////////////////////////////////////////////////////////////// event souris :

        btn_back.setOnMousePressed(mouseEvent -> vers_salleordi());         //si on clique sur power on est dirigé vers la salle ordi
        icone_terminal.setOnMousePressed(mouseEvent -> vers_terminal());    // si on clique sur le terminal on ouvre sa fenetre
        croix_terminal.setOnMousePressed(mouseEvent -> quitte_terminal());  // si on clique sur la croix du terminal on le quitte
        croix_hack.setOnMousePressed(mouseEvent -> quitte_hack());          // si on clique sur la croix du hack on quitte sa fenetre

        icone_papier.setOnMousePressed(mouseEvent -> vers_pendu());     // si on clique sur l'icone papier on ouvre la fenetre du pendu
        croix_pendu.setOnMousePressed(mouseEvent -> quitte_pendu());

        aide.setOnMousePressed(mouseEvent -> {      // quand on clique sur le bouton aide
                if(numlevel == 5){                      // si on est au level 5 un textfield apparait
                    cheat_code();
                }
                else{                                   // sinon des aides nous sont proposé
                    aff_aide();
                }
        });

        // requisition du focus sur le perso a chaque clic sur la fenetre du hack
        fond_jeu.setOnMousePressed(mouseEvent ->  monperso.requestFocus() );
        monperso.setOnMousePressed(mouseEvent ->  monperso.requestFocus() );
        finlevel.setOnMousePressed(mouseEvent ->  monperso.requestFocus() );

        // requisition du focus par le textfield
        txt_cheat.setOnMousePressed(MouseEvent -> txt_cheat.requestFocus());

        //////////////////////////////////////////////////////////////////////////// event clavier
        // valider la cmd ./hack.sh
        txt_hack.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.ENTER){      // quand on clique sur ENTRER
                test_code_hack();
            }
            if (txt_hack.getLength() > 30){         // permet de pas écrire trop de lettre
                txt_hack.deleteText(30,31);
            }
        });

        // valider le cheat
        txt_cheat.setOnKeyPressed(ke -> {
            if(ke.getCode() == KeyCode.ENTER){
                test_code_cheat();
            }
        });

        // lettre pour pendu
        lettre_pendu.setOnKeyPressed(ke -> {
            if (lettre_pendu.getLength() == 1){
                if(ke.getCode() == KeyCode.ENTER){

                    if (!pendu.lettreDejaRentrer.contains(lettre_pendu.getCharacters().charAt(0)) && !pendu.showMot.get(0).getMot().contains(lettre_pendu.getCharacters()) ){
                        label_mot_pendu_deja_trouve.setTranslateX(label_mot_pendu_deja_trouve.getTranslateX() - 10);
                    }
                    pendu.lettreCorrecteur(lettre_pendu.getCharacters().charAt(0));
                    label_mot_pendu.setText(pendu.getChangeMot().toString()); //Affiche mot à trouvé
                    label_mot_pendu_deja_trouve.setText(pendu.getLettreDejaRentrer().toString());  //Affiche lettre déja tapé

                }
            }
            if (lettre_pendu.getLength() <= 1){         // permet de pas écrire trop de lettre (limité a une)
                lettre_pendu.deletePreviousChar();
            }

        });


        // Animation / conception des levels  (cette partie tourne en boucle et vérifie donc a chaque instant les conditions qui la composent)
        AnimationTimer timer = new AnimationTimer() {       // une suite de condition qui sont testé a tout moment (la vitesse de test dépend de la puissance du PC)
            public void handle(long now)
            {
                //fin pendu
                if (pendu.gagner()){
                    go_fin_pendu();
                }

                // finlevel (hitbox)
                if ((monperso.posx <= finlevel.posx + finlevel.width - 20) && (monperso.posx >= finlevel.posx - monperso.width + 20) && (monperso.posy <= finlevel.posy + finlevel.height - 20) && (monperso.posy >= finlevel.posy - monperso.height + 20) ){
                    bol_finlevel = true;
                }
                // actions de fin de level
                if (bol_finlevel){
                    if (numlevel != 5){
                        txt_aide.setVisible(false);
                        txt_aide2.setVisible(false);
                        bol_finlevel = false;
                        monperso.reset();
                    }
                    numlevel += 1;
                }


                ////////////////////// constructions des level
                // gestion des hitbox et lanceur des constructeur graphique de level
                if (numlevel == 1){
                    go_level1 = true;
                    if (lv1_m1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv1_m2.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv1_m3.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv1_m4.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv1_m5.isInHitbox(monperso)){
                        monperso.reset();
                    }

                    // les obj mouvant se reset si on passe dans le level 1
                    lv4_mv1.reset();
                    lv4_mv2.reset();
                    lv4_mv3.reset();
                    lv4_mv4.reset();
                    lv4_mv5.reset();
                    lv5_mv1.reset();
                    lv5_mv2.reset();
                }
                if (numlevel == 2){

                    go_level2 = true;


                    if (lv2_m1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m2.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m3.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m4.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m5.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m6.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m7.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m8.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv2_m9.isInHitbox(monperso)){
                        monperso.reset();
                    }
                }
                if (numlevel == 3){
                    go_level3 = true;
                    if (lv3_m1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m2.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m3.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m4.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m5.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m6.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m7.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m8.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m9.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m11.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m12.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m13.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m14.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m15.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m16.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m17.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m18.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m19.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m20.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv3_m21.isInHitbox(monperso)){
                        monperso.reset();
                    }
                }
                if (numlevel == 4){
                    go_level4 = true;

                    // on fait partir les obj mouvant
                    lv4_mv1.setGo(true);
                    lv4_mv2.setGo(true);
                    lv4_mv3.setGo(true);
                    lv4_mv4.setGo(true);
                    lv4_mv5.setGo(true);

                    if (lv4_mv1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_mv2.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_mv3.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_mv4.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_mv5.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_m1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv4_m2.isInHitbox(monperso)){
                        monperso.reset();
                    }

                }
                if (numlevel == 5){
                    go_dernier_level = true;

                    lv5_mv1.setGo(true);
                    lv5_mv2.setGo(true);

                    if (lv5_mv1.isInHitbox(monperso)){
                        monperso.reset();
                    }
                    if (lv5_mv2.isInHitbox(monperso)){
                        monperso.reset();
                    }
                }
                // fin du jeu
                if (numlevel == 6){
                    fini = true;
                }

                // constructions des level (gestion de l'apparition des elements graphique des != level)
                if (go_level1){
                    go_level1 = false; // on reset les lanceurs
                    finlevel.level1(); // on demande a la fin du level de s'adapter au num du level

                    if (!vraimentfini){
                        lv1.setVisible(true);
                    }
                    lv2.setVisible(false);
                    lv3.setVisible(false);
                    lv4.setVisible(false);
                    lv5.setVisible(false);
                }
                if (go_level2){
                    go_level2 = false;
                    finlevel.level2();
                    lv1.setVisible(false);
                    lv2.setVisible(true);

                }
                if (go_level3){
                    go_level3 = false;
                    finlevel.level3();
                    lv2.setVisible(false);
                    lv3.setVisible(true);
                }
                if (go_level4){
                    go_level4 = false;
                    finlevel.level4();
                    lv3.setVisible(false);
                    lv4.setVisible(true);
                }
                if (go_dernier_level){
                    go_dernier_level = false;
                    finlevel.levellast();
                    lv5.setVisible(true);
                    lv4.setVisible(false);
                }
                // fin du jeu
                if (fini){
                    lv5.setVisible(false);
                    fini = false;
                    vraimentfini = true;
                    monperso.reset();
                    go_fin();
                    Controller.portes.setOuverte();
                }
            }
        };
        timer.start();
    }


    ////////////////////////////////////////////////////////// méthodes utiles

    // méthodes de clic dans le bureau
    public void vers_terminal(){
        gr_terminal.setVisible(true);
        txt_hack.requestFocus();
    }
    public void quitte_terminal(){
        gr_terminal.setVisible(false);
    }

    public void vers_pendu(){
        gr_pendu.setVisible(true);
        lettre_pendu.requestFocus();

    }
    public void quitte_pendu(){
        gr_pendu.setVisible(false);
    }


    // methodes lancement / fermeture hack
    public void test_code_hack(){
        if (txt_hack.getText().equals("./hack.sh")){
            vers_hack();
            txt_hack.clear();
        }
    }
    public void vers_hack(){
        gr_hack.setVisible(true);
        monperso.requestFocus();
    }
    public void quitte_hack(){
        gr_hack.setVisible(false);
        numlevel = 1;
        monperso.reset();
        reset_cheat();
    }

    // méthode affichage aide :
    public void aff_aide(){
        if (txt_aide.isVisible()){
            txt_aide.setVisible(false);
            txt_aide2.setVisible(false);
        }
        else{
            txt_aide.setVisible(true);
            txt_aide2.setVisible(true);
        }

    }

    // méthode cheat-code
    public void cheat_code(){
        txt_cheat.setVisible(!txt_cheat.isVisible());
        if (!txt_cheat.isVisible()){
            monperso.requestFocus();
        }
    }
    public void test_code_cheat(){
        if (txt_cheat.getCharacters().toString().equals("Cheatcode")||txt_cheat.getCharacters().toString().equals("cheatcode")){      // depend du mot de billal
            monperso.requestFocus();
            txt_cheat.setDisable(true);

            lv5_mv1.setVitesse(1);
            lv5_mv2.setVitesse(1);
        }
    }

    // méthode de fin de jeu
    public void go_fin(){
        // chose a désactiver a la fin
        monperso.setVisible(false);
        finlevel.setVisible(false);
        aide.setVisible(false);


        // chose a activer a la fin
        txt_fin.setVisible(true);
    }

    public void go_fin_pendu(){
        // chose a désactiver a la fin
        label_mot_pendu.setVisible(false);
        label_mot_pendu_deja_trouve.setVisible(false);
        lettre_pendu.setVisible(false);


        // chose a activer a la fin
        txt_fin_pendu.setVisible(true);
    }



    // méthodes de transitions de scene
    public void vers_salleordi(){
        go_salleordi = true;
        quitte_terminal();
        quitte_hack();
    }

    // méthode reset du cheat code (si mort entre cheatcode et arrivé par exemple)
    public void reset_cheat(){
        txt_cheat.setDisable(false);
        txt_cheat.clear();
    }

    //// setter et getter

    public void setGo_salleordi(boolean go_salleordi) {
        this.go_salleordi = go_salleordi;
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // permet de savoir si la porte doit etre ouverte ou pas
    public boolean isOuverture_porte() {
        return ouverture_porte;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
}
