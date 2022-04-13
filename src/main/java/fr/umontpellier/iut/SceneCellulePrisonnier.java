package fr.umontpellier.iut;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;


public class SceneCellulePrisonnier extends Niveaux {


    private double screenheight;
    private double screenwidth;

    public SceneCellulePrisonnier(double scH, double scW,Horloge h){
        screenwidth = scW;
        screenheight = scH;

        Group cellulePri = new Group();

        //Scene
        ImageView scenePri = new ImageView(new Image(SceneCellulePrisonnier.class.getResourceAsStream("/Images/scene2.png")));
        scenePri.setFitHeight(screenheight);
        scenePri.setFitWidth(screenwidth);
        scenePri.setRotate(scenePri.getRotate() + 180);

        //Gouache
        Gouache gouache = new Gouache("/images/gouache.png",150,(Settings.screenwidth/2)-30, Settings.screenheight-500);

        //Coussin
        CoussinPrisonnier coussinPrisonnier = new CoussinPrisonnier((screenwidth/2)-60, screenheight-530);

        //Prisonnier
        Prisonnier prisonnier = new Prisonnier( h,screenwidth-800, 50);

        //création de la flèche
        FlecheDirection fleche = new FlecheDirection(Settings.screenwidth*0.1,Settings.screenheight*0.5,90); //fleche qui revient au couloir principal
        fleche.setOnMousePressed(mouseEvent -> Controller.transitions(Controller.sceneCellulePrisonnier,Controller.corridor));




        //Change la position du coussin suelement si le prisonnier est absent sinon avoir ce qui se passe
        coussinPrisonnier.setOnMouseClicked(mouseEvent -> {
            if(!Prisonnier.isHere) {
                coussinPrisonnier.actionner();
                gouache.setVisible(!coussinPrisonnier.position.equals("right"));
            }else{
                Prisonnier.showRejetDialogue();
            }
        });
        /*
        //Test Montre avec boutton a changer (avec la scene de la laverie)
        Button button = new Button();
        button.setText("get montre");

        button.setOnMouseClicked(mouseEvent -> h.montre.findMontre());


         */



        cellulePri.getChildren().add(scenePri);
        cellulePri.getChildren().add(gouache);
        cellulePri.getChildren().add(coussinPrisonnier);
        cellulePri.getChildren().add(prisonnier);
       // cellulePri.getChildren().add(button);  //add le bouton de cheat

        this.getChildren().add(cellulePri);
        getChildren().add(fleche);
    }
}
