package noyau.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TestExer extends Test implements Serializable {
    private ArrayList<Exercice> tabQuestExercice;
    private int nbexer;
    private static int  nbrTestExercices=0;

    public TestExer(String nom, int capacite) {
        this.capacite = capacite;
        this.nom = nom;
        tabQuestExercice = new ArrayList<>();
        nbrTestExercices++;
        Exercice.setNbexo(0);
    }
   /* public int findindicebyid(int id){
         int j = -1 ;
        for (int i = 0; i < tabQuestExercice.size(); i++) {
            Exercice element=tabQuestExercice.get(i);
           if(element.getNumexos()==id){
              j = i;
        }
    }return j;
    }*/
   public int findindicebyid(int id){
       for (int i = 0; i < tabQuestExercice.size(); i++) {
           Exercice element=tabQuestExercice.get(i);
           if(element.getNumexos()==id){
               return i;
           }
       }
       return -1;
   }

    public void setTabQuestExercice(ArrayList<Exercice> tabQuestExercice) {
        this.tabQuestExercice = tabQuestExercice;
    }

    public ArrayList<Exercice> getTabQuestExercice() {
        return this.tabQuestExercice;
    }

    public boolean peutAjouter(String Equestion) {
        boolean possible = false;
        if (this.nbexer < this.capacite) {
           possible=true;
        }
        return possible;
    }

    public int trouverEnonce(String Equestion) {
        int questionIndex = -1; // Initialize to -1 (not found)
        for (int i = 0; i < this.tabQuestExercice.size(); i++) {
            if (this.tabQuestExercice.get(i).GetExercice().equals(Equestion)) {
                questionIndex = tabQuestExercice.get(i).getNumexos(); // Found the question, store its index
                break;
            }
        }
        return questionIndex;
    }

    public void ajouterExercice(Exercice ex) {
        this.tabQuestExercice.add(ex);
        this.nbexer++;

    }

    public void supprimerExercice(int indice) {
        if ((indice >= 0) && (indice < this.tabQuestExercice.size())) {
            this.tabQuestExercice.remove(indice);
            this.nbexer--; // Décrémenter le nombre d'exercices
        } else {
            System.out.println("Indice invalide : " + indice);
        }
    }

    public void modifierExercice(int i, Exercice modif) {
        if (i != -1) {
            tabQuestExercice.set(i, modif);
        }
    }
    /*public ArrayList<Integer> sameexo(int indice){
        ArrayList<Integer> listeEntiers = new ArrayList<>();
        Exercice element = this.tabQuestExercice.get(indice);
        String enonce=element.GetExercice();
        for (int i = 0; i < this.tabQuestExercice.size(); i++) {
            if (this.tabQuestExercice.get(i).GetExercice().equals(enonce)) {
               listeEntiers.add(i);
            }
        }
       return listeEntiers;
    }*/
    public ArrayList<Integer> sameexo(int id){
        ArrayList<Integer> listeEntiers = new ArrayList<>();
        int indice =findindicebyid(id);
        Exercice element=this.tabQuestExercice.get(indice);
        String enonce=element.GetExercice();
        for (int i = 0; i < this.tabQuestExercice.size(); i++) {
            if (this.tabQuestExercice.get(i).GetExercice().equals(enonce)) {
                listeEntiers.add(tabQuestExercice.get(i).getNumexos());
            }
        }
        return listeEntiers;
    }

    public int getNbexer() {
        return nbexer;
    }

    public void setNbexer(int nbexer) {
        this.nbexer = nbexer;
    }
    public void afficherTestexo(){
        Iterator<Exercice> it = this.tabQuestExercice.iterator();
        System.out.println("i am in afficher test ");
        while (it.hasNext()){
            System.out.println( it.next().GetExercice());
        }
    }
}



