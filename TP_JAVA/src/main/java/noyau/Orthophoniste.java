package noyau;

import noyau.Anamnese.Anamnese;
import noyau.Bilan.Bilan;
import noyau.Bilan.Trouble;
import noyau.Dossier.DossierPatient;
import noyau.RendezVous.*;
import noyau.Test.Test;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Orthophoniste implements Serializable {
    private int idO   ;
    private String nom ;
    private String prenom ;
    private String adresse ;
    private String adresseEmail ;
    private String numeroTele   ;
    private String motPasse   ;
    private static int sommeOrtho = 0  ;
    private Agenda agenda ;
    private Anamnese anamnese ;
    private HashSet<Test > listTest ;
    private HashSet<DossierPatient> listDocPatient ;

    // tableau des test + reponce anamnese et compte rendue dans billan
    public Orthophoniste (String nom ,String prenom,String adresse,String adresseEmail,String numeroTele,String motPasse){
        this.nom = nom;
        this.idO = sommeOrtho;
        this.prenom = prenom;
        this.adresse = adresse;
        this.adresseEmail = adresseEmail;
        this.numeroTele = numeroTele;
        this.motPasse = motPasse;
        sommeOrtho++;
        this.agenda = new Agenda();
        this.anamnese = new Anamnese();
        this.listDocPatient = new HashSet<DossierPatient>();
        this.listTest = new HashSet<>();
    }
    public Agenda getAgenda(){
        return this.agenda ;
    }
    public Anamnese getAnamnese(){
        return this.anamnese ;
    }
    public String getNom(){
        return this.nom ;
    }
    public HashSet<Test> getListtest(){
        return this.listTest ;
    }
    public HashSet<DossierPatient> getListDocPatient(){
        return this.listDocPatient ;
    }
    public void ajouterTest(Test e){
        this.listTest.add(e) ;
    }
    public int hashCode(){
        String key = this.nom  ;
        key.concat(this.motPasse) ;
        return  key.hashCode() ;
    }
    public boolean equals(Object o){
        boolean a ;
       if(((Orthophoniste)o).getNom().equals(this.nom)){
           if(((Orthophoniste)o).getMotPasse().equals(this.nom)){
               a = true ;
           }else{
               a = false ;
           }
       }else{ a = false ;}
       return  a ;
    }
   /* public int findindicetest(String nomt) {
        int Index = -1; // Initialize to -1 (not found)
        for (int i = 0; i < this.listTest.size(); i++) {
            if (this.listTest.get(i).Getnomtest().equals(nomt)) {
                Index = i; // Found the test, store its index
                break;
            }
        }
        return Index;
    }*/
    public void supprimerTest(String nomtest) {
        Iterator<Test> it = this.listTest.iterator();
        Test t2 = null ;
        System.out.println("i am in modifier test ");
        while (it.hasNext()){
            Test t = it.next();
            if(t.getNom().equals(nomtest)){
                t2 = t ;
                break;
            }
        }
        this.listTest.remove(t2) ;

    }
    public void modifierTest(String nomtest, Test modif) {
        Iterator<Test> it = this.listTest.iterator();
        Test t2 = null ;
        System.out.println("i am in modifier test ");
        while (it.hasNext()){
            Test t = it.next();
            if(t.getNom().equals(nomtest)){
                t2 = t ;
                this.listTest.add(modif);
                break;
            }
        }
        this.listTest.remove(t2) ;

    }
    public RendezVous findobjetRDV(LocalTime time , LocalDate date) {
        Set<RendezVous> listRdv = this.agenda.getRDV() ;
        Iterator<RendezVous> it = listRdv.iterator();
        RendezVous t2 = null ;
        System.out.println("i am in modifier test ");
        while (it.hasNext()){
            RendezVous t = it.next();
            if(t.getHeure().equals(time) && t.getJour().equals(date)){
                t2 = t ;
                break;
            }
        }
        return t2 ;

    }
    public Test findobjetTest(String nomtest) {
        Iterator<Test> it = this.listTest.iterator();
        Test t2 = null ;
        System.out.println("i am in modifier test ");
        while (it.hasNext()){
            Test t = it.next();
            if(t.getNom().equals(nomtest)){
                t2 = t ;
                break;
            }
        }
        return t2 ;

    }
    public HashSet<Test> getListTest() {
        return listTest;
    }

    public void setListTest(HashSet<Test> tabTest) {
        this.listTest = tabTest;
    }

    public void ajouterDocPatient(DossierPatient e){
        this.listDocPatient.add(e) ;
    }
    public void supprimerDocPatient(DossierPatient e){
        this.listDocPatient.remove(e) ;
    }
    public DossierPatient getDossierDeNumPatient(int numPatient){
        Iterator<DossierPatient> it = listDocPatient.iterator() ;
        DossierPatient m2 = null ;
        while(it.hasNext()){
            DossierPatient m = it.next() ;
            if(m.getNumDossier() == numPatient ){
                m2 = m ;
                break;
            }
        }  return m2 ;
    }
    public ArrayList<Integer> patientSoufreTrouble(Trouble trouble){
            Iterator<DossierPatient> it2 =  listDocPatient.iterator() ;
            ArrayList<Integer> tabpatient = new ArrayList<Integer>() ;
            while(it2.hasNext()){
                DossierPatient doc = it2.next() ;
                ArrayList<Bilan> bilan = doc.getListBilan();
                Iterator<Bilan> it =  bilan.iterator() ;
                while(it.hasNext()) {
                    if(it.next().getDiagnostic().getTrouble().equals(trouble)){// je dois recuperer tableau de bilans puis chercher dans chaque bilan ou il y a ce trouble et stocker le patient ;
                        tabpatient.add(doc.getNumDossier());
                    }
               }

            }
            return tabpatient ;
    }
    public float[] pourcentagePatientParTrouble() {
        Iterator<DossierPatient> it2 = listDocPatient.iterator();
        float[] tabPourcent = {0, 0, 0, 0}; // par convention on prend la case 0 pour deglutition, la case 1 pour cognitif, la case 2 pour celle qui reste, la case 3 est pour la somme

        while (it2.hasNext()) {
            DossierPatient doc = it2.next();
            ArrayList<Bilan> bilan = doc.getListBilan();
            Iterator<Bilan> it = bilan.iterator();
            while (it.hasNext()) {
                Bilan bil = it.next();
                System.out.println("troublename " + bil.getDiagnostic().getTrouble().getTroubleName() + " trouble cache " + Trouble.degultition.getTroubleName());
                if (bil.getDiagnostic().getTrouble().getTroubleName().equals(Trouble.degultition.getTroubleName())) {
                    System.out.println("hi");
                    tabPourcent[0] = tabPourcent[0] + 1;
                    tabPourcent[3] += 1;
                } else if (bil.getDiagnostic().getTrouble().getTroubleName().equals(Trouble.cognitif.getTroubleName())) {
                    tabPourcent[1] = tabPourcent[1] + 1;
                    tabPourcent[3] += 1;
                } else {
                    tabPourcent[2] = tabPourcent[2] + 1;
                    tabPourcent[3] += 1;
                }
            }
        }

        if (tabPourcent[3] != 0) {
            tabPourcent[0] = (tabPourcent[0] / tabPourcent[3]) * 100;
            tabPourcent[1] = (tabPourcent[1] / tabPourcent[3]) * 100;
            tabPourcent[2] = (tabPourcent[2] / tabPourcent[3]) * 100;
        } else {
            tabPourcent[0] = 0;
            tabPourcent[1] = 0;
            tabPourcent[2] = 0;
        }

        return tabPourcent;
    }

    /* public Integer[] pourcentagePatientParTrouble(){
        Iterator<DossierPatient> it2 =  listDocPatient.iterator() ;
        Integer[] tabPourcent = {0,0,0,0} ; // par convention on prend la case 0 pour degultition
      //  la case 1 pour cognitif et la case 2 pour celle qui reste
      // la case 3 est pour la somme
        while(it2.hasNext()){
            DossierPatient doc = it2.next();
            ArrayList<Bilan> bilan = doc.getListBilan();
            Iterator<Bilan> it =  bilan.iterator() ;
            while(it.hasNext()) {
                Bilan bil = it.next() ;
                System.out.println("troublename "+bil.getDiagnostic().getTrouble().getTroubleName()+"trouble cache "+Trouble.degultition.getTroubleName());
                if(bil.getDiagnostic().getTrouble().getTroubleName().equals(Trouble.degultition.getTroubleName())){// je dois recuperer tableau de bilans puis chercher dans chaque bilan ou il y a ce trouble et stocker le patient ;
                    System.out.println("hi");
                    tabPourcent[0] = tabPourcent[0] + 1  ;
                    tabPourcent[3] += 1 ;

                }else if(bil.getDiagnostic().getTrouble().getTroubleName().equals(Trouble.cognitif.getTroubleName())){
                    tabPourcent[1] = tabPourcent[1] + 1 ;
                    tabPourcent[3] += 1 ;

                }else{
                    tabPourcent[2] = tabPourcent[2] + 1;
                    tabPourcent[3] += 1 ;

                }
            }

        }
        if(tabPourcent[3] != 0) {
            tabPourcent[0] = (tabPourcent[0] / tabPourcent[3]) * 100;
            tabPourcent[1] = (tabPourcent[1] / tabPourcent[3]) * 100;
            tabPourcent[2] = (tabPourcent[2] / tabPourcent[3]) * 100;
        }else {
            tabPourcent[0] = 0 ; tabPourcent[1] = 0 ; tabPourcent[2] = 0 ;
        }
        System.out.println("tabPourcent");
        for (int i = 0; i < tabPourcent.length; i++) {
            System.out.println(tabPourcent[i]);
        }
        return tabPourcent ;
    }*/
    public void afficherTest(){
        Iterator<Test> it = this.listTest.iterator();
        System.out.println("i am in afficher test ");
        while (it.hasNext()){
           System.out.println( it.next().getNom());
        }
    }
    public void afficherdocs(){
        Iterator<DossierPatient> it = this.listDocPatient.iterator();
        System.out.println("i am in afficher test ");
        while (it.hasNext()){
            System.out.println( it.next().getNumDossier());
        }
    }
  /*  public DossierPatient recupererDossier(int numPatient){  // lire tous les information d'un patient appartir à son numero
        Iterator<DossierPatient> it =  listDocPatient.iterator() ;
        DossierPatient m2 = null ;
        while(it.hasNext()){
            DossierPatient doc = it.next() ;
            if(doc.getDossierDeNumPatient(numPatient) != null){
                m2 = orth.getDossierDeNumPatient(numPatient);
                break;
            }
        }
        return m2 ;
    }*/
    public void ajouterRdvPatient(int numPatient, RendezVous rdv ){
        if(this.getDossierDeNumPatient(numPatient) != null){
            this.getDossierDeNumPatient(numPatient).ajouterRdv(rdv);
        }else{
            System.out.println("le patient n existe pas on doit creer un nouveau dossier , je suis besoin de plus d'info ");
        }
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
}
