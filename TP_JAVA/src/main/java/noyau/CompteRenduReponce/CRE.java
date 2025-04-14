package noyau.CompteRenduReponce;



import noyau.Test.TestExer;

import java.io.Serializable;
import java.util.*;

public class CRE extends CompteRendu implements Serializable {
   // private Map<Integer, Integer> tabScore = new HashMap<>();
    private Map<Integer, Integer> tabScore ;
    public CRE (){
        tabScore=new HashMap<>();
    }
    public void setscore(int indice, int score) {
        if ((score <= 10) && (score >= 1)) {

            tabScore.put(indice,score);

        }
    }
    public Map<Integer, Integer> copierTabScore() {
        // Création d'une copie de la map originale
        Map<Integer, Integer> tabScoreCopie = new HashMap<>(tabScore);
        return tabScoreCopie;
    }
    public float calculMoyenne(ArrayList<Integer> lis) {
        int som = 0;
        int cpt = 0;

        for (int key : tabScore.keySet()) {// Iterate over the keys of tabScore
            if (lis.contains(key)) { // Check if the key exists in liste
                som += tabScore.get(key);
                cpt++;
            }
        }

        float moy = (cpt > 0) ? ((float) som / cpt) : 0; // Check if cpt is greater than 0 to avoid division by zero
        return moy;
    }

   /* public float setscoretotal(TestExer test) {
        float som = 0;
        for (int key : tabScore.keySet()) {
            int i = test.findindicebyid(key);
            if (key != -1 && i!= -1) { // j'ai ajouter i!= -1
                ArrayList<Integer> nouvellist = test.sameexo(i);//HOW TO LINK EACH TEST EXO WITH LE COMPTE RENDU ADEQUAT
                som = som + this.calculMoyenne(nouvellist);
            }
        }
        System.out.println("som"+som);
        return som;
    }*/
   public float setscoretotal(TestExer test){

       Map<Integer, Integer> mapCopie = this.copierTabScore();
       float som=0;
       ArrayList<Map.Entry<Integer, Integer>> entriesList = new ArrayList<>(mapCopie.entrySet());
       ArrayList<Float> vrailist = new ArrayList<>();
       Set<Integer> keySet = mapCopie.keySet();
       Iterator<Integer> iterator = keySet.iterator();
       int k=0;
       while (entriesList.size()>0) {

           int key = entriesList.get(0).getKey();

           System.out.println("avant");
           test.sameexo(key);
           System.out.println("apres");
           ArrayList<Integer> malist = test.sameexo(key);
           System.out.println("listsameexo" + malist);

           for (int i = 0; i < malist.size(); i++) {
               if (!this.getTabScore().containsKey(malist.get(i))) {
                   malist.remove(i);
               }
           }

           System.out.println("listesameexo after filter");
           System.out.println(malist);
           float moy = this.calculMoyenne(malist);
           System.out.println("la moyenne" + moy);
           for (int i = 0; i < malist.size(); i++) {
               if (mapCopie.containsKey(malist.get(i))) {

                   mapCopie.remove(malist.get(i));
                   Iterator<Map.Entry<Integer, Integer>> iteratorr = entriesList.iterator();
                   while (iteratorr.hasNext()) {
                       Map.Entry<Integer, Integer> entry = iteratorr.next();
                       if (entry.getKey().equals(malist.get(i))) {
                           iteratorr.remove();
                           mapCopie.remove(entry.getKey());
                           break;
                       }
                   }
               }
           }

           vrailist.add(moy);
           som=som+moy;
           System.out.println("vrailist" + vrailist);
           System.out.println("Map Copiée: " + mapCopie);
           System.out.println("Entries: " + entriesList);


       }
       return som;
   }
    public  void afficherCRE(){
        System.out.println("afficherCRE"); int som = 0 ;
        for (int key : tabScore.keySet()) {
            System.out.println( key +" : "+tabScore.get(key));
            som = som +tabScore.get(key);
        }
        System.out.println("som CRE"+som);
    }
     public Map<Integer, Integer> getTabScore(){
       return tabScore;
     }
}

/*package noyau.CompteRenduReponce;

import noyau.Test.TestExer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CRE extends CompteRendu {
    private Map<Integer, Integer> tabScore = new HashMap<>();

    public void setscore(int indice, int score) {
        if ((score <= 10) && (score >= 1)) {
            if (tabScore.containsKey(indice)) {
                tabScore.put(indice,score);}
            else {
                System.out.println("Indice invalide");
            }
        }
    }

    public float calculMoyenne(ArrayList<Integer> lis) {
        int som = 0;
        int cpt = 0;

        for (int key : tabScore.keySet()) { // Iterate over the keys of tabScore
            if (lis.contains(key)) { // Check if the key exists in liste
                som += tabScore.get(key);
                cpt++;
            }
        }

        float moy = (cpt > 0) ? ((float) som / cpt) : 0; // Check if cpt is greater than 0 to avoid division by zero
        return moy;
    }


    public float setscoretotal(TestExer test) {
        float som=0;
        for (int key : tabScore.keySet()) {
          int i=test.findindicebyid(key);
                  if (key!=-1){
            ArrayList<Integer> nouvellist = test.sameexo(i);//HOW TO LINK EACH TEST EXO WITH LE COMPTE RENDU ADEQUAT
            som = som + this.calculMoyenne(nouvellist);
        }}
        return som;
    }

}*/

/*
public class CRE extends CompteRendu {
    private ArrayList<Integer> tabScore;

    public void setscore(int indice, int score) {
        if ((score <= 10) && (score >= 1)) {
            if (indice >= 0 && indice < tabScore.size()) {
                tabScore.set(indice, score);
            } else {
                System.out.println("Indice invalide");
            }
        }
    }

    public float calculmoyenne(ArrayList<Integer> liste) {
        int som = 0;
        int cpt = 0;
        for (int i = 0; i < tabScore.size(); i++) {
            if (liste.contains(i)) {
                som = som + tabScore.get(i);
                cpt++;
            }
        }
        float moy = som / cpt;
        return moy;
    }

    public float setscoretotal(TestExer test) {
       float som=0;
        for (int i = 0; i < tabScore.size(); i++) {
            ArrayList<Integer> nouvellist=test.sameexo(i);//HOW TO LINK EACH TEST EXO WITH LE COMPTE RENDU ADEQUAT
           som=som+this.calculmoyenne(nouvellist);
        }
        return som;
    }

}*/