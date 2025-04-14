package noyau.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Suivi extends RendezVous implements Serializable {
    private int NDossier ;
    private String typeSuivi ; // presentielle ou en ligne

    public Suivi(LocalTime heureDebut , LocalDate date , int NDossier , String typeSuivi  ){
        this.heureDebut = heureDebut ;
        this.NDossier = NDossier ;
        this.typeSuivi = ""; // presentielle ou en ligne
        if(typeSuivi.equals("presentielle") || typeSuivi.equals("en ligne")){
            this.typeSuivi = typeSuivi ;
        }

        this.date = date ;
        this.duree = Duration.ofHours(1) ;
    }

    public int getNDossier() {
        return NDossier;
    }

    public void setNDossier(int NDossier) {
        this.NDossier = NDossier;
    }

    public String getTypeSuivi() {
        return typeSuivi;
    }

    public void setTypeSuivi(String typeSuivi) {
        this.typeSuivi = typeSuivi;
    }
}
