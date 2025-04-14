package noyau.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


class TypepersonException extends Exception {

}
public class Consultation extends RendezVous implements Serializable {
    private String nom ;
    private String prenom ;
    private int age ;

    public Consultation(String nom , String prenom  , int age , LocalTime heureDebut , LocalDate date ,String type ) /*throws TypepersonException*/ {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.heureDebut = heureDebut;
        this.date = date;
        Duration duree = Duration.ofHours(0);
        if (type.equals("enfant")) {
            duree = Duration.ofHours(2).plusMinutes(30);
        } else if (type.equals("adulte")) {
            duree = Duration.ofHours(1).plusMinutes(30);
        } /*else {
            throw new TypepersonException();
        }*/
        this.duree = duree;
    }
    public String getNom(){
        return this.nom ;
    }

}
