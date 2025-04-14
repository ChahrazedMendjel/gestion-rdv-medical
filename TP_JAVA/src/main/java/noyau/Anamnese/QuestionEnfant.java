package noyau.Anamnese;

import java.io.Serializable;

public class QuestionEnfant extends QuestionAnamnese implements Serializable {
    private CatQuestionEnfant typecat ;
    public QuestionEnfant(String enonce, CatQuestionEnfant typeCat ,int indicequestanamnese){
        this.enonce = enonce ;
        this.typecat = typeCat ;
        this.indicequestanamnese = indicequestanamnese ;
    }
    public CatQuestionEnfant getTypecat(){
        return this.typecat ;
    }
    public Integer getIndiceQues(){
        return this.indicequestanamnese ;
    }
}
