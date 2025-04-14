package noyau.Anamnese;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Anamnese implements Serializable {
    private  Set<QuestionAnamnese> question ;
    private static int nombreQuestions = 0 ;
    public Anamnese(){
        this.question = new HashSet<QuestionAnamnese>();
    }
    public Set<QuestionAnamnese> getQuestion(){
        return  question ;
    }
    public int AjouterQuestion(String enonce , CatQuestionAdulte typeCat ){
        int i = nombreQuestions ;
        nombreQuestions ++ ;
        question.add( new QuestionAdulte(enonce,typeCat,i) );
        return i ;
    }
    public int AjouterQuestion(String enonce , CatQuestionEnfant typeCat ){
        int i = nombreQuestions ;
        nombreQuestions ++ ;
        question.add( new QuestionEnfant(enonce,typeCat,i) );
        return i ;
    }
    public Integer recupererQuesIndice( QuestionAnamnese q2 ){
        Iterator<QuestionAnamnese> it = question.iterator() ;
        int indicequest = -1 ;
        while(it.hasNext()){
            QuestionAnamnese ques = it.next() ;
            if(ques == q2){
                indicequest =  ques.getIndiceQues() ;
            }
        }
        return indicequest ;
    }
    public Integer recupererQuesIndice( String enonce ){
        Iterator<QuestionAnamnese> it = question.iterator() ;
        int indicequest = -1 ;
        while(it.hasNext()){
            QuestionAnamnese ques = it.next() ;
            if(ques.getEnonce() == enonce){
                indicequest =  ques.getIndiceQues() ;
            }
        }
        return indicequest ;
    }
    public String recupererQuesenonce( Integer id ){
        Iterator<QuestionAnamnese> it = question.iterator() ;
        String s = "" ;
        while(it.hasNext()){
            QuestionAnamnese ques = it.next() ;
            if(ques.getIndiceQues() == id){
                s =  ques.getEnonce() ;
            }
        }
        return s ;
    }
    public void ModifierQuestion(int numOldQuestion , String newQuestion){
        QuestionAnamnese questomodify = null ;
        for(QuestionAnamnese ques : question ){// parcourir pour chercher l'objet qui a cette valeur de enonce et puis utiliser remove pour le supprimer
            if( ques.getIndiceQues().equals(numOldQuestion)){
                questomodify  = ques ;
                if(ques instanceof QuestionEnfant){
                    QuestionEnfant tired = (QuestionEnfant) ques;
                    CatQuestionEnfant category = tired.getTypecat();
                    this.AjouterQuestion(newQuestion,category);
                }else if (ques instanceof QuestionAdulte){
                    QuestionAdulte tired = (QuestionAdulte) ques;
                    CatQuestionAdulte category = tired.getTypecat();
                    this.AjouterQuestion(newQuestion,category);
                }
                break ;
            }
        }
        question.remove(questomodify);

    }
    public void ModifierQuestion(String OldQuestion , String newQuestion ,String cat,String catType){
        QuestionAnamnese questomodify = null ;
        for(QuestionAnamnese ques : question ){// parcourir pour chercher l'objet qui a cette valeur de enonce et puis utiliser remove pour le supprimer
            if( ques.getEnonce().equals(OldQuestion)){
                questomodify  = ques ;
                    if(catType.equals("Enfant")){
                        CatQuestionEnfant category  =  CatQuestionEnfant.fromDescription(cat) ;
                        this.AjouterQuestion(newQuestion,category);
                    }else if(catType.equals("Adulte")){
                        CatQuestionAdulte category =  CatQuestionAdulte.fromDescription(cat) ;
                        this.AjouterQuestion(newQuestion,category);
                    }

                break ;
            }
        }
        question.remove(questomodify);

    }
    public void supprimerQuestion(String q){
        System.out.println("index in supprimer "+q);
        QuestionAnamnese questodelete = null ;
        for(QuestionAnamnese ques : question ){// parcourir pour chercher l'objet qui a cette valeur de enonce et puis utiliser remove pour le supprimer
            System.out.println("ques.getIndiceQues() "+ques.getIndiceQues());
         if( ques.getEnonce().equals(q) ){
             questodelete = ques ;
             System.out.println("l element pour supprimer ");System.out.println(questodelete.getEnonce());
             break ;
         }
        }
        question.remove(questodelete);
    }
    public void afficherQuestionsAnamnese(){
        System.out.println("here 1") ;
        Iterator<QuestionAnamnese> it = question.iterator() ;

        while(it.hasNext()){
            QuestionAnamnese rdv = it.next() ;
            System.out.println(rdv.getEnonce());
            if (rdv instanceof QuestionEnfant) {
                QuestionEnfant consultation = (QuestionEnfant) rdv;
                System.out.println("that = " + consultation.getTypecat());
            } else if (rdv instanceof QuestionAdulte) {
                QuestionAdulte atelier = (QuestionAdulte) rdv;
                System.out.println("those = "+atelier.getTypecat());

            }
            //System.out.println(rdv.get());
        }
    }
}
