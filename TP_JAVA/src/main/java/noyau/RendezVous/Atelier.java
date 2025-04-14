package noyau.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Atelier extends RendezVous implements Serializable {
    private String thematique ;
    private List<Integer> listPatients ;
   // private int nbrPatientAtelier ;

    public Atelier(String thematique,LocalTime heureDebut , LocalDate date , ArrayList<Integer> listPatients){
        this.listPatients = new ArrayList<Integer>(listPatients) ;
        this.thematique = thematique ;
        this.date = date ;
        this.heureDebut = heureDebut;
        this.duree = Duration.ofHours(1) ;
    }
    public List<Integer> getListPatients(){
        return this.listPatients ;
    }
    public void afficherlistPatients(){
        System.out.println("here 1") ;
        Iterator<Integer> it = this.listPatients.iterator() ;

        while(it.hasNext()){

            System.out.println(it.next() );

        }
    }
}
