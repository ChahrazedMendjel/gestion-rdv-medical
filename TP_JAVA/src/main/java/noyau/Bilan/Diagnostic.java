package noyau.Bilan;

import java.io.Serializable;

public class Diagnostic implements Serializable {
    private Trouble trouble;
    private String nom;
    public Diagnostic(Trouble trouble) {
        this.trouble = trouble;
    }

    public Trouble getTrouble() {
        return trouble;
    }

    public void setTrouble(Trouble trouble) {
        this.trouble = trouble;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

