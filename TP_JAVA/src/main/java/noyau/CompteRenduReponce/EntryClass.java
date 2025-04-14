package noyau.CompteRenduReponce;

import java.io.Serializable;
import java.util.ArrayList;

public class EntryClass implements Serializable {
    private int score;
    private ArrayList<String> tabReponce;

    public EntryClass(int score, ArrayList<String> tabReponce) {
        this.tabReponce=tabReponce;
        this.score=score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> getReponce() {
        return tabReponce;
    }

    public void ajouterReponce(String reponce) {
        this.tabReponce.add(reponce);
    }
    public void afficherReponse(){
        for( String str : tabReponce){
            System.out.println(str);
        }
    }
}


/*package noyau.CompteRenduReponce;

import java.util.ArrayList;

public class EntryClass {
    private int score;
    private ArrayList<String> tabReponce;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> getReponce() {
        return tabReponce;
    }

    public void ajouterReponce(String reponce) {
        this.tabReponce.add(reponce);
    }
    public void afficherReponse(){
        for( String str : tabReponce){
            System.out.println(str);
        }
    }
}*/