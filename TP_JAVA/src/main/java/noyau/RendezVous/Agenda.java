package noyau.RendezVous;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;


public class Agenda implements Serializable {
    private Set<RendezVous> RDV;
    public Agenda() {
        this.RDV = new TreeSet<RendezVous>();
    }
    public boolean ajouterRdv(RendezVous rdv){
     return RDV.add(rdv) ;
    }
    public void supprimerRdv(RendezVous rdv){
         RDV.remove(rdv) ;
    }

    public Set<RendezVous> getRDV() {
        return RDV;
    }
    public void afficherRdvs() {
        System.out.println("//////////////////////////////");
        Iterator<RendezVous> it = RDV.iterator();

        while (it.hasNext()) {
            RendezVous rdv = it.next();
            if (rdv instanceof Consultation) {
                Consultation consultation = (Consultation) rdv;
                System.out.println("that = " + consultation.getNom());
            } else if (rdv instanceof Atelier) {
                Atelier atelier = (Atelier) rdv;
                System.out.println("those = ");
                atelier.afficherlistPatients();
            }
            System.out.println(rdv.date);
            System.out.println(rdv.heureDebut);
            System.out.println(rdv.duree);
            System.out.println("//////////////////////////////");
        }
    }
        public RendezVous getRdv(LocalTime heureDebut, LocalDate date ){
            System.out.println("//////////////////////////////") ;
            Iterator<RendezVous> itv = RDV.iterator() ;
            RendezVous r = null ;
            while(itv.hasNext()){
                RendezVous rdv = itv.next() ;
                if(rdv.getJour().equals(date) && rdv.getHeure().equals(heureDebut)){
                    r = rdv ;
                    break;
                }
            }
            return r ;
    }
   /* public RendezVous rechercherRDV(String
                                        nom){
        Compte c=null;
        Iterator<Compte> it =
                lesComptes.iterator();
        while (it.hasNext()){
            c=it.next();
            if
            (nom.equalsIgnoreCase(c.getNom()))
                return c;
        }
        return null;
    }*/


}
