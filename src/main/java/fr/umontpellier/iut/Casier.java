package fr.umontpellier.iut;

import com.sun.javafx.font.directwrite.RECT;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.awt.*;
import java.util.ArrayList;

public class Casier extends Parent {
    private boolean clique;
    static boolean correct;
    private ImageView casier;
    private Label message=new Label("" );//a voir si on garde les comentaire

    public Casier(){
        casier=new ImageView(new Image(Casier.class.getResourceAsStream("/Images/Casier-Fermer.png")));

        casier.setY(Screen.getPrimary().getBounds().getMaxY() *0.698);
        casier.setX(Screen.getPrimary().getBounds().getMaxX() *0.746);

        clique=false;
        message.setTextFill(Color.WHITE);
        message.setStyle("-fx-font-size: 200%");
        message.setTranslateY(Screen.getPrimary().getBounds().getMaxY() *0.30);
        message.setTranslateX(Screen.getPrimary().getBounds().getMaxX() *0.31);
        this.getChildren().addAll(casier,message);
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
               appuyer();
               clique=true;
            }
        });
    }
    public void appuyer() {
        if (!clique) {
            Label messageAttention =new Label("Vous avez un seul essai, ATTENTION!!!!!!");
            messageAttention.setTextFill(Color.WHITE);
            messageAttention.setStyle("-fx-font-size: 200%");
            messageAttention.setTranslateY(Screen.getPrimary().getBounds().getMaxY() *0.30);
            messageAttention.setTranslateX(Screen.getPrimary().getBounds().getMaxX() *0.31);
            this.getChildren().addAll(messageAttention);
            DigiCodeSchema digiCodeSchema = new DigiCodeSchema();
            this.getChildren().add(digiCodeSchema);
            this.getChildren().removeAll(casier,message);
        }

    }



}
