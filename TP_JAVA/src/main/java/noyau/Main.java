package noyau;

import noyau.Anamnese.*;
import noyau.Bilan.*;
import noyau.CompteRenduReponce.CRQ;
import noyau.Dossier.*;
import noyau.RendezVous.*;
import noyau.Test.QCMU;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        Orthophoniste orth1 = new Orthophoniste("amira","bd","jijel","azerty@gmail.com","0687654321","ami") ;
       // Agenda ag1 = orth1.getAgenda() ;
        noyau.RendezVous.Agenda ag1 = orth1.getAgenda() ;
        LocalDate date1 = LocalDate.of(2024, 5, 10);
        LocalDate date2 = LocalDate.of(2024, 5, 11);
        LocalTime time1 = LocalTime.of(00, 00);
        LocalTime time2 = LocalTime.of(21, 30);
        ag1.ajouterRdv( new Consultation("Ahmed","tg",23,time1,date2,"adulte")) ;
       // ag1.ajouterRdv( new Consultation("mohamed","tg",18,time2,date1,"enfant")) ;
        ag1.ajouterRdv( new Consultation("mohamed","tg",17,time2,date1,"adulte")) ;
        //ag1.ajouterRdv( new Suivi(time1,date2,18,"adulte")) ;
        ArrayList<Integer> listPatients = new ArrayList<Integer>(){{
            add(30);
            add(33);
            add(24);
        }};
        //ag1.ajouterRdv( new Atelier("thematique",time2,date1,listPatients));
         ag1.afficherRdvs();
        // Anamnese an1 = orth1.getAnamnese() ;
        noyau.Anamnese.Anamnese an1 = new Anamnese() ;
        int Q1 = an1.AjouterQuestion("quelle est votre nom ? ", CatQuestionAdulte.HISTOIRE_MALADIE);
        int Q2 = an1.AjouterQuestion("quelle est votre age ? ", CatQuestionEnfant.STRUCTURE_FAMILIALE);
        an1.ModifierQuestion(Q1,"Bonjour, comment allez vous");
        an1.afficherQuestionsAnamnese();
        Patient pt1 = new Enfant("ali","bd","mila","mila","22/12/2016","cem","0612345674");
        DossierPatient dpt1 = new DossierPatient(pt1,orth1) ;
        dpt1.ajouterFS(new FicheSuivi("1ere fiche"));
        dpt1.getFicheSuivi().get(0).ajouterobj("terminer avec le test de voix",TypeObjectifs.COURT_TERME);
        dpt1.getFicheSuivi().get(0).ajouterobj("terminer avec le test de voix2",TypeObjectifs.LONG_TERME);

        List<Objectifs> l= dpt1.getFicheSuivi().get(0).getListobjs() ;
               l.get(0).AjouterScore(3);
        List<Objectifs> l2= dpt1.getFicheSuivi().get(0).getListobjs() ;
       l2.get(1).AjouterScore(5);
        dpt1.getFicheSuivi().get(0).afficherObjs();
        //dpt1.ajouterFS(new FicheSuivi("2eme fiche"));
        PremierBilan bilan = new PremierBilan(pt1);
        bilan.ajouterReponceAna(Q1,"Bouderbala");
        bilan.ajouterReponceAna(Q2,"Amira");
        bilan.modifierReponceAna(Q2,"chahra");
        //bilan.afficherReponceAna();
        HashSet<String> tabchoix  = new HashSet<String>(); tabchoix.add("banane");tabchoix.add("mango");
        QCMU questQCMU = new QCMU(tabchoix,"votre fruits prefere",true);
        CRQ cr = new CRQ() ;
        cr.remplirreponce(questQCMU.getNumQuest(),"orange");// je doix recuperer l'indice de la questions crer avant et puis le mettre dans la fonction
        // ou je doit mettre le compteur des test est ce que dans ortho , ou bien le tableau làbas est pour les petits tests
        bilan.ajoutercr(cr);
       // cr.affichercrq();

        System.out.println("here") ;
    }
}