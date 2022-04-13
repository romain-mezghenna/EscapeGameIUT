package fr.umontpellier.iut;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class Livre extends Parent {
    private boolean eta;
    private ImageView livre;
    private ImageView img;
    private final double screenheight = Screen.getPrimary().getBounds().getHeight();
    private final double screenwidth = Screen.getPrimary().getBounds().getWidth();

    public Livre(double x, double y, double taille) {
        this.eta = true;
        this.livre = new ImageView(new Image(Livre.class.getResourceAsStream("/livre.png")));
        livre.setPreserveRatio(true);
        livre.setFitWidth(taille);
        livre.setX(x);
        livre.setY(y);
        this.getChildren().add(livre);
        setOnMousePressed(mouseEvent -> interagir());

    }

    public void interagir() {
        if (eta) {
            openBouquin();
            eta = !eta;
        }else{
            eta = !eta;
            closeBouquin();
        }


    }

    public void openBouquin() {
        img = new ImageView(new Image(getClass().getResourceAsStream("/livre_ouvert.png")));
        img.setFitWidth(screenwidth / 1.5);
        img.setFitHeight(screenheight / 1.5);
        img.setX(333);
        img.setY(233);
        this.getChildren().add(img);
        setOnMousePressed(mouseEvent -> interagir());
    }
    public void closeBouquin(){
        this.getChildren().remove(img);
    }
}


