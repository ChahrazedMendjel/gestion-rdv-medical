package noyau.CompteRenduReponce;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class CRQ extends CompteRendu implements Serializable {
    private Map<Integer, EntryClass> tabScore = new HashMap<>();

    public void remplirscore(int indice,int score){ // l'indice de la question et son score
        if((score<=10)&&(score>=1)){
            if (tabScore.containsKey(indice)) {
                EntryClass entry = tabScore.get(indice);
                entry.setScore(score);
                tabScore.put(indice, entry);
            }
            else {
                ArrayList<String> rand= new ArrayList<String>();
                EntryClass entry = new EntryClass(score,rand);
                tabScore.put(indice,entry);
            }}}
    public void remplirreponce(int indice,String repe){
        // qu'est ce que cette fonction faire
        System.out.println("indice"+indice);
        if (tabScore.containsKey(indice)) {
            EntryClass entry = tabScore.get(indice);
            entry.ajouterReponce(repe);
            tabScore.put(indice,entry);
        }
        else {
            ArrayList<String> rand= new ArrayList<String>();
            EntryClass entry = new EntryClass(0,rand);
            entry.ajouterReponce(repe);
            tabScore.put(indice,entry);
        }
    }
    public int setscoretotal(){
        int som=0;
        for (Integer key : tabScore.keySet()) {
            EntryClass element = tabScore.get(key);
            som=som+element.getScore();
        }
            return  som ;

    }
    public void affichercrq(){
        Iterator<Map.Entry<Integer, EntryClass>> iterator = tabScore.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, EntryClass> entry = iterator.next();
            Integer key = entry.getKey();
            EntryClass value = entry.getValue();
            System.out.println("Clé : " + key + ", score : " + value.getScore()+"reponces");
            value.afficherReponse();

        }
    }
    public  void afficherCRQ(){
        System.out.println("afficherCRQ");
        for (int key : tabScore.keySet()) {
            System.out.println( key +" : ");
                    tabScore.get(key).afficherReponse();
        }
    }


}




/*package noyau.CompteRenduReponce;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class CRQ extends CompteRendu{
    private Map<Integer,EntryClass> tabScore = new HashMap<>();
    public void remplirscore(int indice,int score){ // l'indice de la question et son score
        if((score<=10)&&(score>=1)){
            if (tabScore.containsKey(indice)) {
                EntryClass entry = tabScore.get(indice);
                entry.setScore(score);
            } else {
                System.out.println("Indice invalide");
            }
        }}
    public void remplirreponce(int indice,String repe){
       // qu'est ce que cette fonction faire
        System.out.println("indice"+indice);
        if (tabScore.containsKey(indice)) {
            EntryClass entry = tabScore.get(indice);

            entry.ajouterReponce(repe);}
        else {
            System.out.println("Indice invalide");
        }
    }
    public int setscoretotal(){
        int som=0;
        for (Integer key : tabScore.keySet()) {
            EntryClass element = tabScore.get(key);
            som=som+element.getScore();
        }
        return som;
    }
    public void affichercrq(){
        Iterator<Map.Entry<Integer, EntryClass>> iterator = tabScore.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, EntryClass> entry = iterator.next();
            Integer key = entry.getKey();
            EntryClass value = entry.getValue();
            System.out.println("Clé : " + key + ", score : " + value.getScore()+"reponces");
            value.afficherReponse();

        }
    }
}
*/

/*
public class CRQ extends CompteRendu {
    private ArrayList<RepScore> tabScore;
    public void remplirscore(int indice,int score){
        if((score<=10)&&(score>=1)){
            if (indice >= 0 && indice < tabScore.size()) {
                RepScore element = tabScore.get(indice);
                element.setScore(score);
            } else {
                System.out.println("Indice invalide");
            }
        }}
    public void remplirreponce(int indice,String rep){

            if (indice >= 0 && indice < tabScore.size()) {
                RepScore element = tabScore.get(indice);
                element.setReponse(rep);
            } else {
                System.out.println("Indice invalide");
            }
       }
       public int setscoretotal(){
        int som=0;
           for (int i = 0; i < tabScore.size(); i++) {
           RepScore element = tabScore.get(i);
           som=som+element.getScore();
       }
           return som;
    }

}
*/