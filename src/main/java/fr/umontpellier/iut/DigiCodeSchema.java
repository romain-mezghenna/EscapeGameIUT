package fr.umontpellier.iut;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class DigiCodeSchema extends Parent {
    static boolean correct=false;
    private static ArrayList<Integer> listCode2=new ArrayList<>();
    private ArrayList<ToucheCode> listTouche = new ArrayList<>();
    BoutonValider valide=new BoutonValider();
    public DigiCodeSchema() {
        Image digicodeImage = new Image(getClass().getResourceAsStream("/Images/digicodeschema.png"));
        ImageView digicodeView = new ImageView(digicodeImage);
        Group cadre=new Group();
       cadre.getChildren().addAll(digicodeView);
        int X= 120;
        int Y =100;
        int code=1;//Code  numerique correspendand a chaque touche
        for (int i = 0; i < 5; i++) {//Remplisage de la matrice
            for (int j = 0; j < 4; j++) {//...
                ToucheCode toucheCode = new ToucheCode(X,Y,code);//...
                listTouche.add(toucheCode);//...
                if (j == 3) {//...
                    X = 120;//...
                } else {//...
                    X = X + 50;//...
                }//...
                code++;//...
            }//...

            Y = Y + 50;//...
        }//...

        for (ToucheCode i : listTouche) { //ajoute de chaque touche a Digicode
            cadre.getChildren().add(i);
        }
        cadre.getChildren().add(valide);
        this.getChildren().add(cadre);

    }
    public static boolean verifierCode(ArrayList<Integer> listCode) {//Verifier si le code saisie est correcte
        int i=0;
        boolean correct=true;
        if (listCode.size()!=listCode2.size()){
            return false;
        }else {
            for (Integer e:listCode2){
                if (!listCode.contains(e)){
                    correct=false;
                }
                    i++;
            }
        }
        return correct;
    }//

    public static void ajouter(Integer a){
        listCode2.add(a);
    }
    public static void enlever(Integer a){
        listCode2.remove(a);
    }
    public boolean isCorrect() {
        return correct;
    }
}


