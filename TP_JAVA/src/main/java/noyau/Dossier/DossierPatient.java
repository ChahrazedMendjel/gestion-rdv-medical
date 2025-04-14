package noyau.Dossier;

import noyau.Bilan.Bilan;
import noyau.Orthophoniste;
import noyau.RendezVous.RendezVous;

import java.io.Serializable;
import java.util.*;

public class DossierPatient implements Serializable {
    static private int nbpatients=0;
    private int NumDossier ;
    private Patient patient ;
    private Orthophoniste ortho ;
    private Set<RendezVous> RDV ;
    private ArrayList<FicheSuivi> FS ;
    private ArrayList<Bilan>  listBilan ;
    // ajouter la liste des billans
    // ajouter une methode statique pour récupérer un objet dossier a partir de son numero dans la classe systeme
    public DossierPatient(Patient patient , Orthophoniste ortho ){
        this.RDV = new TreeSet<RendezVous>();
        this.FS = new ArrayList<FicheSuivi>() ;
        this.listBilan = new ArrayList<Bilan>();
        this.ortho = ortho ;
        this.patient = patient ;
        nbpatients++;
        this.NumDossier = nbpatients ;
    }
    public int getNumDossier(){
        return this.NumDossier ;
    }
    public boolean ajouterRdv(RendezVous rdv){
        return this.RDV.add(rdv) ;
    }
    public void supprimerRdv(RendezVous rdv){
        this.RDV.remove(rdv) ;
    }

   /* public void ajouterFS(FicheSuivi fs){
        //System.out.println(fs.canAddFichSuivi());
        if(this.FS.size() > 0) {
            System.out.println("this.FS.size() - 1 = "+(this.FS.size() - 1));
            FicheSuivi ls = this.FS.get(this.FS.size() - 1);
            if(ls.canAddFichSuivi()){
                System.out.println(("ajouter avec succee"));
                this.FS.add(fs);
            }
        }else{
            this.FS.add(fs);
        }
    }*/
   public boolean ajouterFS(FicheSuivi fs){
       System.out.println("ikchem1");
       if(this.FS.size() > 0) {
           System.out.println("ikchem");
           System.out.println("this.FS.size() - 1 = "+(this.FS.size() - 1));
           FicheSuivi ls = this.FS.get(this.FS.size() - 1);
           System.out.println(ls.canAddFichSuivi());
           if(ls.canAddFichSuivi()){
               System.out.println("ikchem anda ouylaq ara");
               System.out.println(("ajouter avec succee"));
               this.FS.add(fs);
               return true;
           }
           else{
               System.out.println("ikchem2");
               return false;}
       }else{
           this.FS.add(fs);
           return true;
       }
   }
    public void supprimerFS(FicheSuivi fs) {
        this.FS.remove(fs);
    }
    public List<FicheSuivi> getFicheSuivi(){
        return this.FS ;
    }
    public FicheSuivi getfichenom(String nomf){
        Iterator<FicheSuivi> itf = this.FS.iterator();
        FicheSuivi fs = null ;
        while(itf.hasNext()){
            FicheSuivi f = itf.next() ;
            if(f.getNomFichSuivi().equals(nomf)){
                fs = f ;
                break;
            }
        }
        return  fs ;
    }
    public Set<RendezVous> getRendezVous(){
        return this.RDV ;
    }

    public void ajouterListBilan(Bilan bl) {
        this.listBilan.add(bl);
    }
    public void supprimerListBilan(Bilan bl) {
        this.listBilan.remove(bl);
    }
    public ArrayList<Bilan> getListBilan(){
        return this.listBilan ;
    }
    public Patient getPatient(){
        return this.patient ;
    }
    public int hashCode(){return this.NumDossier;}
    public boolean equals(Object obj){
        return ((DossierPatient)obj).getNumDossier() == (this.NumDossier);
    }

    public Orthophoniste getOrtho() {
        return ortho;
    }

    public void setOrtho(Orthophoniste ortho) {
        this.ortho = ortho;
    }
}





/*package noyau.Dossier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import noyau.Anamnese.QuestionAnamnese;
import noyau.RendezVous.* ;
import noyau.Orthophoniste ;
import noyau.Bilan.* ;

public class DossierPatient {
    private int NumDossier ;
    private Patient patient ;
    private Set<RendezVous> RDV ;
    private ArrayList<FicheSuivi> FS ;
    private ArrayList<Bilan>  listBilan ;
    // ajouter la liste des billans
    // ajouter une methode statique pour récupérer un objet dossier a partir de son numero dans la classe systeme
    public DossierPatient(Patient patient , int NumDossier , Orthophoniste orth  ){
        this.RDV = new TreeSet<RendezVous>();
        this.FS = new ArrayList<FicheSuivi>() ;
        this.patient = patient ;
        this.NumDossier = NumDossier ;
    }
    public int getNumDossier(){

        return this.NumDossier ;
    }
    public boolean ajouterRdv(RendezVous rdv){
        return this.RDV.add(rdv) ;
    }
    public void supprimerRdv(RendezVous rdv){
        this.RDV.remove(rdv) ;
    }

    public void ajouterFS(FicheSuivi fs){
        //System.out.println(fs.canAddFichSuivi());
        if(this.FS.size() > 0) {
            System.out.println("this.FS.size() - 1 = "+(this.FS.size() - 1));
            FicheSuivi ls = this.FS.get(this.FS.size() - 1);
        if(ls.canAddFichSuivi()){
            System.out.println(("ajouter avec succee"));
            this.FS.add(fs);
        }
        }else{
            this.FS.add(fs);
        }
    }
    public void supprimerFS(FicheSuivi fs) {
        this.FS.remove(fs);
    }
    public List<FicheSuivi> getFicheSuivi(){
        return this.FS ;
    }
    public void ajouterListBilan(Bilan bl) {
        this.listBilan.add(bl);
    }
    public void supprimerListBilan(Bilan bl) {
        this.listBilan.remove(bl);
    }
    public ArrayList<Bilan> getListBilan(){
        return this.listBilan ;
    }
    public Patient getPatient(){
        return this.patient ;
    }
    public int hashCode(){return this.NumDossier;}
    public boolean equals(Object obj){
        return ((DossierPatient)obj).getNumDossier() == (this.NumDossier);
    }

}
*/