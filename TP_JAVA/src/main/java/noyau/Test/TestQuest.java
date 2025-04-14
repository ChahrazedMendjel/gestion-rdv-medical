package noyau.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TestQuest extends Test implements Serializable {
    private ArrayList<Question> tabQuestSimpl;
    private int nbquest = 0 ;
    private static int nbrTestQ =0;
  // si je met le compteur ici ??
    public TestQuest(String nom, int capacite) {
        this.capacite = capacite;
        this.nom = nom;
        tabQuestSimpl = new ArrayList<>();
        nbrTestQ++;
        Question.setNumeroquest(0);
    }

    public void SetTabQues(ArrayList<Question> tabQuestSimpl) {
        this.tabQuestSimpl = tabQuestSimpl;
    }

    public ArrayList<Question> GetTabQues() {
        return this.tabQuestSimpl;
    }

    public boolean Peutajouter(String Equestion){
        boolean questionExists = false;
        if (this.nbquest < this.capacite) {
            for (Question quest : this.tabQuestSimpl) {
                if (quest.getQuest().equals(Equestion)) {
                    questionExists = true;
                    break;
                }
            }
    }
        return questionExists;
    }
        public int trouverquest(String Equestion){
            int questionIndex = -1; // Initialize to -1 (not found)
            for (int i = 0; i < this.tabQuestSimpl.size(); i++) {
                if (this.tabQuestSimpl.get(i).getQuest().equals(Equestion)) {
                    questionIndex = i; // Found the question, store its index
                    break;
                }
            }
            return questionIndex;
        }


            public void ajouterquest(Question question) {
             this.tabQuestSimpl.add(question);
             this.nbquest++;  }

    public void supprimerQuestion(int indice) {
        if ((indice >= 0) && (indice < this.tabQuestSimpl.size())) {
            this.tabQuestSimpl.remove(indice);
            this.nbquest--; // Décrémenter le nombre de questions
        } else {
            System.out.println("Indice invalide : " + indice);
        }
    }


    public void modifierquest(int i, Question modif) {
        if (i != -1) {
            tabQuestSimpl.set(i,modif);
        }
    }

    public int getNbquest() {
        return nbquest;
    }

    public void setNbquest(int nbquest) {
        this.nbquest = nbquest;
    }
    public void afficherTestquestion(){
        Iterator<Question> it = this.tabQuestSimpl.iterator();
        System.out.println("i am in afficher test ");
        while (it.hasNext()){
            System.out.println( it.next().getQuest());
        }
    }
}
