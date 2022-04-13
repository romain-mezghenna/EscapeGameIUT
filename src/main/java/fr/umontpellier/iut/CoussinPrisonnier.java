package fr.umontpellier.iut;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CoussinPrisonnier extends Parent implements ElementInteractif {

    private double translateX;
    private double translateY;
    public String position;

    public CoussinPrisonnier(double tX, double tY){

        translateX = tX;
        translateY = tY;
        position = "right";

        ImageView coussin = new ImageView(new Image(CoussinPrisonnier.class.getResourceAsStream("/Images/coussinPrisonnier.png")));
        coussin.setFitHeight(250);
        coussin.setPreserveRatio(true);

        coussin.setRotate(coussin.getRotate() + 180);

        //Text t = new Text("Coussin");

        this.getChildren().add(coussin);
        this.setTranslateX(translateX);
        this.setTranslateY(translateY);
    }

    //Change la position du coussin
    @Override
    public void actionner() {
        if(position == "right"){
            this.setTranslateX(translateX-300);
            position = "left";
        }else{
            this.setTranslateX(translateX);
            position = "right";
        }
    }
}
