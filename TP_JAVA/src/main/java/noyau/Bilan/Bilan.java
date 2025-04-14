package noyau.Bilan;

import noyau.CompteRenduReponce.CRE;
import noyau.CompteRenduReponce.CRQ;
import noyau.CompteRenduReponce.CompteRendu;
import noyau.Orthophoniste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Bilan implements Serializable {
   // private ArrayList<Test> tabTest;
    protected ArrayList<CompteRendu> tabCompteRendu;
   private String observation;
   private String Projettherapeutique;
   private Diagnostic diagnostic;

   public Bilan(){
       this.tabCompteRendu = new ArrayList<CompteRendu>();

   }

    public void ajoutercr(CompteRendu cr) {
        this.tabCompteRendu.add(cr);

    }
    public CompteRendu isTestcrdexiste(String nomt){
        CompteRendu a = null ;
        Iterator<CompteRendu> it = tabCompteRendu.iterator();
        while (it.hasNext()){
            CompteRendu  cr = it.next();
            if(cr.getNomTest().equals(nomt)){
                a = cr ;
                break;
            }
        }
        return  a ;
    }


    public void supprimercr(int indice) {
        if ((indice >= 0) && (indice < this.tabCompteRendu.size())) {
            this.tabCompteRendu.remove(indice);

        } else {
            System.out.println("Indice invalide : " + indice);
        }
    }

    public void modifiercr(int i, CompteRendu modif) {
        if (i != -1) {
            tabCompteRendu.set(i, modif);
        }
    }
    public Diagnostic getDiagnostic(){
        return this.diagnostic ;
    }
    public void setDiagnostic(Diagnostic d){
        this.diagnostic  = d;
    }
    public ArrayList<CompteRendu> getTabCompteRendu() {
        return tabCompteRendu;
    }

    public void setTabCompteRendu(ArrayList<CompteRendu> tabCompteRendu) {

       this.tabCompteRendu = tabCompteRendu;
    }
    public void afficherCompteRendu(){
        Iterator<CompteRendu> it = tabCompteRendu.iterator();
        while(it.hasNext()){
               CompteRendu cr = it.next();
               if(cr instanceof CRE){
                   ((CRE)cr).afficherCRE();
               }else if(cr instanceof CRQ){
                   ((CRQ)cr).afficherCRQ();
               }
        }
   }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getProjettherapeutique() {
        return Projettherapeutique;
    }

    public void setProjettherapeutique(String projettherapeutique) {
        Projettherapeutique = projettherapeutique;
    }
}
