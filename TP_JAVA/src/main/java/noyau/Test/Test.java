package noyau.Test;

import noyau.Anamnese.QuestionAnamnese;

import java.io.Serializable;

public abstract class Test implements Serializable {
    protected String nom;
    protected int capacite;
    public int hashCode(){return this.nom.hashCode();}
    public boolean equals(Object obj){
        return ((Test)obj).getNom().equals(this.nom);
    }

    public String Getnomtest(){
      return this.nom;
    }
    public void Setnomtest(String nom){
        this.nom=nom;
    }
    public int Getcapacite(){
        return this.capacite;
    }
    public void Setcapacite(int capacite){
        this.capacite=capacite;
    }
    public String getNom(){ return nom ;}
}
