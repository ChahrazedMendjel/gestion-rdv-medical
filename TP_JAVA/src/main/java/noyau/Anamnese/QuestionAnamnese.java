package noyau.Anamnese;

import java.io.Serializable;

public abstract class QuestionAnamnese implements Serializable {
    protected String enonce ;
    protected int indicequestanamnese ;

    public boolean equals(Object obj){
        return ((QuestionAnamnese)obj).getEnonce().equals(this.enonce);
    }
    public String getEnonce(){
        return this.enonce ;
    }
    public int hashCode(){return this.enonce.hashCode();}
    public abstract Integer getIndiceQues() ;
}
