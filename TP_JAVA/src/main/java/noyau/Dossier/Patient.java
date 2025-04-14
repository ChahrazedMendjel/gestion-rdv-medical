package noyau.Dossier;

import java.io.Serializable;

public abstract class Patient implements Serializable {
    protected String nom ;
    protected String prenom ;
    protected String lieuNaissance ;
    protected String adresse ;
    protected String dateNaissance ;

    public String getNom(){
        return this.nom ;
    }
    public String getLieuNaissance(){
        return this.lieuNaissance;
    }
    public String getDateNaissance(){
        return this.dateNaissance ;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public String getPrenom(){
        return this.prenom ;
    }

}
