package noyau.Test;

import java.io.Serializable;

public class ExerciceM extends Exercice implements Serializable {
    private String materiel;
    public ExerciceM(String materiel,String consigne){
        super(consigne);
        this.materiel=materiel;
    }
    public void SetExerciceM(String consigne){
        this.materiel=materiel;
    }
    public String GetExerciceM(){
        return this.materiel;
    }
}
