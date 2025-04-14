package noyau.Test;

import java.io.Serializable;

public class Question implements Serializable { // questions à réponce libre
    protected String enonce ;
    static private int  nbrquest=0;
    protected int numQuest ;
    public Question(String quest){
        this.enonce=quest;
        nbrquest++;
        this.numQuest=nbrquest;
    }

    public static int getNumeroquest() {
        return nbrquest;
    }

    public static void setNumeroquest(int numeroquest) {
        Question.nbrquest = numeroquest;
    }


    public String getQuest(){
        return this.enonce;
    }
    public void setQuest(String ques){
        this.enonce=ques;
    }
    public void setNumQuest(int i ){
        this.numQuest=i;
    }
    public int getNumQuest(){
      return this.numQuest;
    }

}
