package fr.umontpellier.iut;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.*;
import java.util.ArrayList;

public class ToucheCode extends Parent {
    private boolean active;
    private Integer  code;
    public ToucheCode(int x, int y,Integer code) {

            Rectangle rect=new Rectangle(50,50,Color.BLACK);
            rect.setX(x);
            rect.setY(y);
            this.code=code;
            rect.setStroke(Color.WHITE);
            this.getChildren().add(rect);
                this.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                         if(!isActive()) {
                             rect.setFill(Color.LIGHTGREY);
                             active = true;
                             DigiCodeSchema.ajouter(code);
                         }
                         else
                         {
                             rect.setFill(Color.BLACK);
                             active = false;
                             DigiCodeSchema.enlever(code);
                         }

                    }
                });
    }

    public boolean isActive() {
        return active;
    }

    public int getCode() {
        return code;
    }
}