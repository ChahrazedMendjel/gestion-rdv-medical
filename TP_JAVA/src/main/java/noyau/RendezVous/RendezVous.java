package noyau.RendezVous;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RendezVous implements Comparable<RendezVous> , Serializable {
    protected LocalTime heureDebut ;
    protected Duration duree;
    protected LocalDate date ;
    // est ce que on ajoute observation
    // Création des deux dates
    //LocalDate date1 = LocalDate.of(2024, 5, 10);
    //LocalDate date2 = LocalDate.of(2024, 5, 11);

    // Création des deux heures
    //LocalTime time1 = LocalTime.of(12, 30);
    //LocalTime time2 = LocalTime.of(13, 45);


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RendezVous that = (RendezVous) o;
        LocalTime endTime1 = this.heureDebut.plus(this.duree) ; System.out.print("endTime1 = ");
        System.out.println(endTime1);
        System.out.print("heureDebut2 = ");System.out.println(that.heureDebut);
        LocalTime endTime2 = that.heureDebut.plus(that.duree) ;
        //System.out.print("endTime2 = ");System.out.println(endTime2);

        return !(endTime1.isBefore(that.heureDebut) || this.heureDebut.isAfter(endTime2)|| this.heureDebut.equals(endTime2) || that.heureDebut.equals(endTime1));
    }
    public LocalDate getJour() {
        return date;
    }

    public void setJour(LocalDate jour) {
        this.date = jour;
    }

    public LocalTime getHeure() {
        return heureDebut;
    }

    public void setHeure(LocalTime heure) {
        this.heureDebut = heure;
    }

   public int compareTo(RendezVous o) {
        // Comparaison des dates

        RendezVous that = o;
        LocalTime time1 = LocalTime.of(21, 30);
        int comparaisonDate = this.date.compareTo(that.date);
        if (comparaisonDate != 0) {
            return comparaisonDate;
        }// si c'est le meme jour
        LocalTime endTime1 = this.heureDebut.plus(this.duree) ; System.out.print("endTime1 = ");
        System.out.println(endTime1);
        LocalTime endTime2 = that.heureDebut.plus(that.duree) ;  System.out.print("endTime2 = ");
        System.out.println(endTime2);
        System.out.print("heureDebut2 = ");System.out.println(that.heureDebut);

        int cmp = 0;
       if(endTime1.isBefore(that.heureDebut) || that.heureDebut.equals(endTime1)){
           cmp = -1 ;//endTime1.compareTo(that.heureDebut);
       }else if(this.heureDebut.isAfter(endTime2) || this.heureDebut.equals(endTime2) ){
           System.out.println("hii ");
           cmp = 1 ;// this.heureDebut.compareTo(endTime2) ;
        }
       if((this.heureDebut.isAfter(time1) || this.heureDebut.equals(time1))&& (that.heureDebut.isBefore(time1) || that.heureDebut.equals(time1))){

          cmp = 1 ;// traitement du 22 heure
       }
       return cmp ;
    }
  /*public int compareTo(RendezVous o) {
      // Comparaison des dates

      RendezVous that = o;
      int comparaisonDate = this.date.compareTo(that.date);
      if (comparaisonDate != 0) {
          return comparaisonDate;
      }// si c'est le meme jour
      LocalTime endTime1 = this.heureDebut.plus(this.duree) ; System.out.print("endTime1 = ");
      System.out.println(endTime1);
      LocalTime endTime2 = that.heureDebut.plus(that.duree) ;  System.out.print("endTime2 = ");
      System.out.println(endTime2);
      System.out.print("heureDebut2 = ");System.out.println(that.heureDebut);
      System.out.print("heureDebut2 = ");System.out.println(that.heureDebut);

      int cmp = 0;
      if(endTime1.isBefore(that.heureDebut) || that.heureDebut.equals(endTime1)){
          cmp = -1 ;//endTime1.compareTo(that.heureDebut);
      }else if(this.heureDebut.isAfter(endTime2) || this.heureDebut.equals(endTime2) ){
          System.out.println("hii ");
          cmp = 1 ;// this.heureDebut.compareTo(endTime2) ;
      }
      return cmp ;
  }*/

}
