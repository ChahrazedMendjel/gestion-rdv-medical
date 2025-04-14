package noyau.Dossier;

import java.io.Serializable;

public class Adulte extends Patient implements Serializable {
    private String diplome ;
    private String profession ;
    private String Npersonnel ;
    public Adulte(String nom ,String prenom, String lieuNaissance , String adresse ,String dateNaissance ,String diplome , String profession,String Npersonnel){
        this.nom = nom ;
        this.prenom = prenom ;
        this.adresse = adresse ;
        this.dateNaissance = dateNaissance ;
        this.lieuNaissance = lieuNaissance ;
        this.diplome = diplome;
        this.Npersonnel = Npersonnel;
        this.profession = profession;
    }
}
