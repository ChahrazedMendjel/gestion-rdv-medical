package com.example.testjavafx;

import com.example.testjavafx.donneetab.TestTab;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Bilan.Bilan;
import noyau.CompteRenduReponce.CRQ;
import noyau.CompteRenduReponce.CompteRendu;
import noyau.Orthophoniste;
import noyau.Test.QCMU;
import noyau.Test.Question;
import noyau.Test.Test;
import noyau.Test.TestQuest;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ReponceTest implements Initializable {

    @FXML
    private Label labelqcm;

    @FXML
    private ListView<String> listview;

    @FXML
    private AnchorPane qcmScene;

    @FXML
    private TextField scoreqcm;

    @FXML
    private AnchorPane CRQqcm;
    @FXML
    private Label labelqcm1;
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste orth = (Orthophoniste) holder.getUser();

    private static TestTab selectedTab;
    String type = "";
    public  void setSelectedTab(TestTab selected){
        selectedTab = selected;
        if (selectedTab != null) {
            // Afficher les données de la cellule sélectionnée dans les champs de texte
            labelqcm.setText(selectedTab.getNom());
            type = selectedTab.getType();
            // typelabel.setText(selectedTab.getType());
            // categorymodifyBox.setValue(selectedTab.getType());
        }
    }


    public static TestTab getSelectedTab() {
        return selectedTab;
    }
    ArrayList<String> choixrep = new ArrayList<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        listview.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    String item = cell.getItem();
                    System.out.println("Clicked on: " + item);
                    choixrep.add(item);
                    /*if (q.GetType()) {
                        choixrep.add(item);
                        // q.ajouterchoix(item);
                    } else {

                        if (q.getTab().isEmpty()) {
                            choixrep.add(item);
                         //   q.ajouterchoix(item);
                        } else {
                            labelqcm1.setText("vous ne pouvez choisir une autre réponce");
                        }
                    }*/
                }
                // faire le traitement
            });

          return cell ;
        });
    }
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @FXML
    void onValiderQCMClick() {
        if (isInteger(scoreqcm.getText()) || !scoreqcm.getText().isEmpty()) {
            Bilan bilan = (Bilan) RemplirBilan.getrb();
            // ReponceTest rp = new ReponceTest();
            RemplirBilan rp = RemplirBilan.getRemlirb();

            String text2 = rp.getTreeItemques().getValue().getNom();
            //String text2 = rp.getSelectedTab().getNom();
            System.out.println("text2" + text2);
            CompteRendu cr1 = bilan.isTestcrdexiste(text2);
            type = selectedTab.getType();
            labelqcm.setText(selectedTab.getNom());
            if (cr1 != null) {
                CRQ cr = (CRQ) cr1;
                cr.setNomTest(text2);
                String s = "";
            /*TestQuest tq = ((TestQuest) orth.findobjetTest(text2));
            HashSet<String> listq = ((QCMU)tq.GetTabQues().get(tq.trouverquest(labelqcm.getText()))).getTab() ;
            Iterator<String> it = listq.iterator();
            while (it.hasNext()){
                s.concat(it.next()+"|");
            }*/
                Iterator<String> it = choixrep.iterator();
                while (it.hasNext()) {
                    cr.remplirreponce(((TestQuest) orth.findobjetTest(text2)).trouverquest(labelqcm.getText()), it.next());
                    if (type.equals("QCMU")) {
                        labelqcm1.setText("vous ne pouvez pas choisir une autre réponce");
                        break;
                    }
                }
                cr.remplirscore(((TestQuest) orth.findobjetTest(text2)).trouverquest(labelqcm.getText()), Integer.parseInt(scoreqcm.getText()));
                bilan.ajoutercr(cr);
                cr.afficherCRQ();
            } else {
                CRQ cr = new CRQ();
                cr.setNomTest(text2);
                String s = "";
            /* TestQuest tq = ((TestQuest) orth.findobjetTest(text2));
           HashSet<String> listq = ((QCMU)tq.GetTabQues().get(tq.trouverquest(labelqcm.getText()))).getTab() ;
            Iterator<String> it = listq.iterator();
            while (it.hasNext()){
                s.concat(it.next()+"|");
            }*/

                Iterator<String> it = choixrep.iterator();
                while (it.hasNext()) {
                    cr.remplirreponce(((TestQuest) orth.findobjetTest(text2)).trouverquest(labelqcm.getText()), it.next());
                    if (type.equals("QCMU")) {
                        break;
                    }
                }
                cr.remplirscore(((TestQuest) orth.findobjetTest(text2)).trouverquest(labelqcm.getText()), Integer.parseInt(scoreqcm.getText()));
                bilan.ajoutercr(cr);

                cr.afficherCRQ();

            }
            ((Stage) CRQqcm.getScene().getWindow()).close();
        }else {
            labelqcm1.setText("Veuillez continuer à remplir Toutes les Informations ");
        }
    }
    @FXML
    void onShowClick() {
        ArrayList<String> choix = new ArrayList<String>();
        //ReponceTest rp = new ReponceTest();
        RemplirBilan rp = RemplirBilan.getRemlirb();

        String text2 = rp.getTreeItemques().getValue().getNom();  //getTreeItemques().getValue().getNom();
        System.out.println("text2" + text2);
        Test t = orth.findobjetTest(text2);
        TestQuest tq = (TestQuest) t;
        QCMU q = (QCMU) tq.GetTabQues().get(tq.trouverquest(labelqcm.getText()));
        labelqcm.setText(q.getQuest());
        Iterator<String> it = q.getTab().iterator();//.iterator();
        System.out.println("i am in init rep test  ");
        while (it.hasNext()) {
            choix.add(it.next());
        }
        listview.getItems().addAll(choix);
    }
}
