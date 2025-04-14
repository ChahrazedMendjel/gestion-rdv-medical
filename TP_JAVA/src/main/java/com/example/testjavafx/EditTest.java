package com.example.testjavafx;

import com.example.testjavafx.donneetab.AnaTab;
import com.example.testjavafx.donneetab.TestTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Anamnese.Anamnese;
import noyau.Orthophoniste;
import noyau.Test.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class EditTest implements Initializable {
    @FXML
    private ChoiceBox<String> categorymodifyBox;

    @FXML
    private TextField newques;
    @FXML
    private AnchorPane editscene;
    @FXML
    private Label enoncelabel;
    @FXML
    private Label typelabel;
    @FXML
    private TextField newsousques;
    @FXML
    private TextField qcm;
    @FXML
    private ChoiceBox<String> categorymodifysousBoxe;
    @FXML
    private ChoiceBox<String> categorymodifysousBoxq;
    @FXML
    private AnchorPane ajoutersoustestpane;
    @FXML
    private Button ajouterchoix;
    @FXML
    private ListView<String> listview;
    @FXML
    private AnchorPane ajoutersoustestpane1;
    @FXML
    private TextField qcm1;
    private HashSet<String> tabchoice= new HashSet<>();
    private static TestTab selectedTab;
    private boolean isDeleteAction = false;
    public boolean isDeleteAction() {
        return isDeleteAction;
    }

    public  void setSelectedTab(TestTab selectedTab){
        this.selectedTab = selectedTab;
        if (selectedTab != null) {
            // Afficher les données de la cellule sélectionnée dans les champs de texte
            enoncelabel.setText(selectedTab.getNom());
            typelabel.setText(selectedTab.getType());
            // categorymodifyBox.setValue(selectedTab.getType());
        }
    }
    public  TestTab getSelectedTab() {
        return this.selectedTab ;
    }
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste orth = (Orthophoniste) holder.getUser();

    private final ObservableList<String> typeT = FXCollections.observableArrayList("Question","Exercice");
    private final ObservableList<String> typeques = FXCollections.observableArrayList("QCM","QCMU","Question à reponce libre");
    private final ObservableList<String> typeexo = FXCollections.observableArrayList("Exercice materiels","Exercice normale");

    @Override
    public void initialize  (URL location, ResourceBundle resources) {
        String  s= typelabel.getText();
        System.out.println("s = "+s);
        categorymodifyBox.setValue(s);
            categorymodifyBox.setVisible(false);
        ajoutersoustestpane.setVisible(false);
        ajoutersoustestpane1.setVisible(false);
        categorymodifysousBoxq.setItems(typeques);
        categorymodifysousBoxq.setValue("type Question");
        categorymodifysousBoxe.setItems(typeexo);
        categorymodifysousBoxe.setValue("type exercice");


       // categorymodifyBox.setItems(typeT);

    }
    @FXML
    protected  void onModifierclick() {
        String text = newques.getText();
        String originalString = typelabel.getText(); //categorymodifyBox.getValue().toString().trim();
        // String originalString = "Adulte: L'histoire de sa maladie";
        compteController cpt =  compteController.getcpt();
        // Find the index of the character ':'

            if (selectedTab != null) {
                selectedTab.setNom(text);
                selectedTab.setType(selectedTab.getType());
                selectedTab.setCapacite(selectedTab.getCapacite());
            }
                if (originalString.equals("Question")) {
                    TestQuest t = new TestQuest(text, selectedTab.getCapacite());
                    orth.modifierTest(enoncelabel.getText(), t);
                } else if (originalString.equals("Exercice")) {
                    TestExer t = new TestExer(text, selectedTab.getCapacite());
                    orth.modifierTest(enoncelabel.getText(), t);
                }else if(originalString.equals("QCM") ){
                    String text2  = cpt.getTreeItemques().getValue().getNom();
                    System.out.println("text2"+text2);
                    Test t = orth.findobjetTest(text2);
                    TestQuest tq = (TestQuest) t ;
                    QCMU m = new QCMU(tabchoice,text,true);
                    tq.modifierquest(tq.trouverquest(enoncelabel.getText()),m);
                    ((TestQuest) t).afficherTestquestion();
                }else if(originalString.equals("QCMU") ){
                    String text2  = cpt.getTreeItemques().getValue().getNom();
                    System.out.println("text2"+text2);
            Test t = orth.findobjetTest(text2);
            TestQuest tq = (TestQuest) t ;
                    QCMU m = new QCMU(tabchoice,text,false);
                    tq.modifierquest(tq.trouverquest(enoncelabel.getText()),m);
            ((TestQuest) t).afficherTestquestion();
               }else if( originalString.equals("Question à reponce libre") ){
                    String text2  = cpt.getTreeItemques().getValue().getNom();
                    System.out.println("text2"+text2);
                    Test t = orth.findobjetTest(text2);
                    TestQuest tq = (TestQuest) t ;
                    Question m = new Question(text);
                    tq.modifierquest(tq.trouverquest(enoncelabel.getText()),m);
                    ((TestQuest) t).afficherTestquestion();
                }else if(originalString.equals("Exercice materiels") ){
                    String text4 = cpt.getTreeItemexo().getValue().getNom();
                    System.out.println("text4"+text4);
                    Test t = orth.findobjetTest(text4);
                    TestExer tq = (TestExer) t ;
                    Exercice m = new Exercice(text);
                    tq.modifierExercice(tq.trouverEnonce(enoncelabel.getText()),m);
                    ((TestExer) t).afficherTestexo();
                 }else if( originalString.equals("Exercice normale") ){
                    String text4 = cpt.getTreeItemexo().getValue().getNom();
                    System.out.println("text4"+text4);
                    Test t = orth.findobjetTest(text4);
                    String text3 = qcm.getText();
                    TestExer tq = (TestExer) t ;
                    ExerciceM m = new ExerciceM(text3,text);
                    tq.modifierExercice(tq.trouverEnonce(enoncelabel.getText()),m);
                    ((TestExer) t).afficherTestexo();
                 }
                orth.afficherTest();

            ((Stage) editscene.getScene().getWindow()).close();

    }
    @FXML
    protected  void onSupprimerclick() {
        isDeleteAction = true;
        compteController cpt =  compteController.getcpt();
        cpt.delleteexo();
        cpt.delleteQues();
        String text = enoncelabel.getText();
        String originalString = typelabel.getText();

        //Integer c = selectedTab.getCapacite();
        if(originalString.equals("Question") || originalString.equals("Exercice") ){
            orth.supprimerTest(text);
            orth.afficherTest();
        }else if(originalString.equals("QCM") || originalString.equals("QCMU") || originalString.equals("Question à reponce libre") ){
            String text2  = cpt.getTreeItemques().getValue().getNom();
            System.out.println("text2"+text2);
            Test t = orth.findobjetTest(text2);
            TestQuest tq = (TestQuest) t ;
            tq.supprimerQuestion(tq.trouverquest(text));
            t.Setcapacite(t.Getcapacite()-1);
            ((TestQuest) t).afficherTestquestion();
        }else if(originalString.equals("Exercice materiels") || originalString.equals("Exercice normale") ){
            String text4 = cpt.getTreeItemexo().getValue().getNom();
            System.out.println("text4"+text4);
            Test t = orth.findobjetTest(text4);
            TestExer tq = (TestExer) t ;
            tq.supprimerExercice(tq.trouverEnonce(text));
            t.Setcapacite(t.Getcapacite()-1);
            ((TestExer) t).afficherTestexo();
        }


        ((Stage) editscene.getScene().getWindow()).close();
    }
    @FXML
    protected  void onRetourclick() {
        ((Stage) editscene.getScene().getWindow()).close();
    }
    @FXML
    protected void onAjouterChoixclick() {
        tabchoice.add(qcm.getText());
        qcm.clear();

    }

    @FXML
    protected void onAjouterSousTestclick() {
        String originalString = typelabel.getText();
        String text = enoncelabel.getText();
        if (originalString.equals("Question")) {
            ajoutersoustestpane.setVisible(true);
            categorymodifysousBoxe.setVisible(false);
            categorymodifysousBoxq.setVisible(true);
            ajouterchoix.setVisible(true);
            qcm.setVisible(true);

        } else if (originalString.equals("Exercice")) {
            ajoutersoustestpane.setVisible(true);
            categorymodifysousBoxq.setVisible(false);
            categorymodifysousBoxe.setVisible(true);
            ajouterchoix.setVisible(false);
            qcm.setVisible(true);
        }else if(originalString.equals("QCMU") || originalString.equals("QCM")){
            ajoutersoustestpane.setVisible(false);
            ajoutersoustestpane1.setVisible(true);
            //qcm1.setVisible(true);
            ArrayList<String> choix = new ArrayList<String>();
            compteController cpt =  compteController.getcpt();
            String text2  = cpt.getTreeItemques().getValue().getNom();
            System.out.println("text2"+text2);
            Test t = orth.findobjetTest(text2);
            TestQuest tq = (TestQuest) t ;
            QCMU q = (QCMU) tq.GetTabQues().get(tq.trouverquest(text));
            Iterator<String> it = q.getTab().iterator();//.iterator();
            System.out.println("i am in afficher test ");
            while (it.hasNext()){
                choix.add(it.next());
            }
            listview.getItems().addAll(choix);
        }
    }
    @FXML
    protected void onValiderclick() {
        if (selectedTab != null) {
            selectedTab.setNom(selectedTab.getNom());
            selectedTab.setType(selectedTab.getType());
            selectedTab.setCapacite(selectedTab.getCapacite()+1);
        }
        compteController cpt =  compteController.getcpt();
        String originalString = typelabel.getText();
        String text = enoncelabel.getText();
        String text3 = qcm.getText();
        Test t = orth.findobjetTest(text);
        String text2  = newsousques.getText();// à ajouter dans le tableau
        if (originalString.equals("Question")) {
            String s = categorymodifysousBoxq.getValue();// à ajouter dans le tableau
            System.out.println(" s valide = "+s);
            if(s.equals("QCM")){
                QCMU q = new QCMU(tabchoice,text2,true); // declarer au debut un tableau hashset on va le remlir dans ajouter choix / materiel
                 TestQuest tq = (TestQuest) t ;
                 tq.ajouterquest(q);
                 t.Setcapacite(t.Getcapacite()+1);
                 TestTab l = new TestTab(text2,"QCM",tabchoice.size());
                cpt.ajouterSousTest(l,"Question");
                newsousques.clear();
            }else if(s.equals("QCMU")){
                QCMU q = new QCMU(tabchoice,text2,false); // declarer au debut un tableau hashset on va le remlir dans ajouter choix / materiel
                TestQuest tq = (TestQuest) t ;
                tq.ajouterquest(q);
                t.Setcapacite(t.Getcapacite()+1);
                TestTab l = new TestTab(text2,"QCMU",tabchoice.size());
                cpt.ajouterSousTest(l,"Question");     newsousques.clear();
            }else if(s.equals("Question à reponce libre")){
                  Question q = new Question(text2); // declarer au debut un tableau hashset on va le remlir dans ajouter choix / materiel
                TestQuest tq = (TestQuest) t ;
                tq.ajouterquest(q);
                t.Setcapacite(t.Getcapacite()+1);
                TestTab l = new TestTab(text2,"Question à reponce libre",0);
                cpt.ajouterSousTest(l,"Question");

            }

        } else if (originalString.equals("Exercice")) {
            String s = categorymodifysousBoxe.getValue();// à ajouter
            if(s.equals("Exercice materiels")){
                ExerciceM q = new ExerciceM(text3,text2); // declarer au debut un tableau hashset on va le remlir dans ajouter choix / materiel
                TestExer tq = (TestExer) t ;
                tq.ajouterExercice(q);
                t.Setcapacite(t.Getcapacite()+1);
                TestTab l = new TestTab(text2,"Exercice materiels",1);
                cpt.ajouterSousTest(l,"Exercice");
            }else if(s.equals("Exercice normale")){
                Exercice q = new Exercice(text2); // declarer au debut un tableau hashset on va le remlir dans ajouter choix / materiel
                TestExer tq = (TestExer) t ;
                tq.ajouterExercice(q);
                t.Setcapacite(t.Getcapacite()+1);
                TestTab l = new TestTab(text2,"Exercice normale",0);
                cpt.ajouterSousTest(l,"Exercice");
            }
        }
        ((Stage) editscene.getScene().getWindow()).close();
    }
    @FXML
    protected void onValiderclick1() {
         String text =  qcm1.getText();
        String text1 = enoncelabel.getText();
        compteController cpt =  compteController.getcpt();
        String text2  = cpt.getTreeItemques().getValue().getNom();
        System.out.println("text2"+text2);
        Test t = orth.findobjetTest(text2);
        TestQuest tq = (TestQuest) t ;
        listview.getItems().add(text) ;
        QCMU q = (QCMU) tq.GetTabQues().get(tq.trouverquest(text1));
        q.ajouterchoix(text);
        if (selectedTab != null) {
            selectedTab.setNom(selectedTab.getNom());
            selectedTab.setType(selectedTab.getType());
            selectedTab.setCapacite(selectedTab.getCapacite()+1);
        }

    }
}
