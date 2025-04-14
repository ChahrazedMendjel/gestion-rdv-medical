package noyau.Test;

import java.io.Serializable;

public class Exercice implements Serializable {
    protected String consigne;
    private int numexos;
    private static int nbexo =0;
    public Exercice(String consigne){
        this.consigne=consigne;
        nbexo++;
        this.numexos=nbexo;
    }

    public static int getNbexo() {
        return nbexo;
    }

    public static void setNbexo(int nbexo) {
        Exercice.nbexo = nbexo;
    }

    public void SetExercice(String consigne){
        this.consigne=consigne;

    }
    public String GetExercice(){
        return this.consigne;

    }


    public int getNumexos() {
        return numexos;
    }

    public void setNumexos(int numexos) {
        this.numexos = numexos;
    }
}
