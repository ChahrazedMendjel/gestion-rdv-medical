package com.example.testjavafx.donneetab;

import java.time.LocalDate;
import java.time.LocalTime;

public class rdvtable {
   private LocalDate jour;
   private LocalTime heure;
   private tipe tipe;
   public rdvtable(LocalDate jour,LocalTime heure,tipe tip){
       this.heure=heure;
       this.jour=jour;
       this.tipe=tip;
   }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public com.example.testjavafx.donneetab.tipe getTipe() {
        return tipe;
    }

    public void setTipe(com.example.testjavafx.donneetab.tipe tipe) {
        this.tipe = tipe;
    }
}
