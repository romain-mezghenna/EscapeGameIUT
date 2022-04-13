package fr.umontpellier.iut.scene_ordi;

import fr.umontpellier.iut.Niveaux;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;

public class SuperSceneOrdi extends Niveaux {

    Ordi_SceneSession sessionordi;
    Ordi_SceneBureau bureauOrdi;
    Ordi_SceneSalle salleordi;

    public SuperSceneOrdi() {

        // création de toutes les sous scene
        sessionordi = new Ordi_SceneSession();
        bureauOrdi = new Ordi_SceneBureau();
        salleordi = new Ordi_SceneSalle();

        //modification de la visibilité des sous scenes
        bureauOrdi.setVisible(false);
        sessionordi.setVisible(false);
        //salleordi.setVisible(false);

        // ajout des sous scenes a la fenetre
        getChildren().addAll(salleordi, sessionordi, bureauOrdi);


        // gestion des transitions
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now)
            {

                // session ordi direction bureau ordi
                if (sessionordi.go_bureauordi){
                    sessionordi.setGo_bureauordi(false);
                    bureauOrdi.setVisible(true);
                    sessionordi.setVisible(false);
                }

                // bureau ordi direction salle ordi
                if (bureauOrdi.go_salleordi){
                    bureauOrdi.setGo_salleordi(false);
                    salleordi.setVisible(true);
                    bureauOrdi.setVisible(false);
                }

                // salle ordi direction session ordi
                if (salleordi.go_sessionordi){
                    salleordi.setGo_sessionordi(false);
                    sessionordi.setVisible(true);
                    salleordi.setVisible(false);
                }

                // session ordi direction salle ordi
                if (sessionordi.go_salleordi){
                    sessionordi.setGo_salleordi(false);
                    salleordi.setVisible(true);
                    sessionordi.setVisible(false);
                }
            }
        };
        timer.start();
    }
}
