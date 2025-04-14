package noyau.Anamnese;

import java.io.Serializable;

public class QuestionAdulte extends QuestionAnamnese implements Serializable {
    private CatQuestionAdulte typecat ;
    public QuestionAdulte (String enonce , CatQuestionAdulte typeCat,int indicequestanamnese){
        this.enonce = enonce ;
        this.typecat = typeCat ;
        this.indicequestanamnese = indicequestanamnese ;
    }
    public CatQuestionAdulte getTypecat(){
        return this.typecat ;
    }
    public Integer getIndiceQues(){
        return this.indicequestanamnese ;
    }
}
