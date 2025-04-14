package noyau.Dossier;

import java.io.Serializable;

public class Enfant extends Patient implements Serializable {
    private String classeEtude ;
    private String NtelephoneParent ;
    public Enfant(String nom ,String prenom, String lieuNaissance , String adresse ,String dateNaissance , String classeEtude, String NtelephoneParent){
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.dateNaissance = dateNaissance ;
        this.lieuNaissance = lieuNaissance ;
        this.classeEtude = classeEtude ;
        this.NtelephoneParent = NtelephoneParent ;
    }
}
