package fr.umontpellier.iut;



import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Relance extends Niveaux {
    private Text msg;


    public Relance (){
        ImageView lav = new ImageView(new Image(Laverie.class.getResourceAsStream("/images/wasted.png")));
        lav.setFitWidth(Settings.screenwidth);
        lav.setFitHeight(Settings.screenheight);
        getChildren().add(lav);
//        Button button = new Button("Vous allez y arriver");
//        getChildren().add(button);
//        button.setLayoutX(Settings.screenwidth*0.47);
//        button.setLayoutY(Settings.screenheight*0.8);
//        button.setOnMouseClicked(MouseEvent -> {
//            Controller.reset();
//        });
    }

    public void setMessage(String message) {
        msg = new Text();
        msg.setText(message);
        msg.setFont(Font.font(28));
        msg.setFill(Color.RED);
        msg.setTranslateX(Settings.screenwidth/2-msg.getLayoutBounds().getWidth()/2);
        msg.setTranslateY(Settings.screenheight*0.65); //ajout du message de mort

        if (getChildren().size() > 1)
            getChildren().remove(1);
        getChildren().add(msg);
    }
}
