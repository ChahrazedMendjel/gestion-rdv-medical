package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Anamnese.Anamnese;
import noyau.Anamnese.CatQuestionAdulte;
import noyau.Anamnese.CatQuestionEnfant;
import noyau.Bilan.Bilan;
import noyau.Bilan.Diagnostic;
import noyau.Bilan.Trouble;
import noyau.Dossier.DossierPatient;
import noyau.Dossier.Enfant;
import noyau.Dossier.Patient;
import noyau.Orthophoniste;
import noyau.SystemeCabinetOrtho;
import noyau.Test.QCMU;
import noyau.Test.TestQuest;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML private Label welcomeText;
    @FXML private TextField nom_orth ;
    @FXML private TextField prenom_orth ;
    @FXML
    private PasswordField motpasse_orth;
    @FXML private TextField adr_orth ;
    @FXML private TextField mail_orth ;
    @FXML private TextField numtele_orth ;
   // @FXML private TextField motpasse_orth ;
    @FXML private AnchorPane scenecreer ;

    @FXML
    private Label emptyelement;
    @Override
    public void initialize  (URL location, ResourceBundle resources){

    }
    @FXML protected void onHelloButtonClick() throws IOException {
        String nom = nom_orth.getText() ;String motpasse = motpasse_orth.getText() ;
        String prenom = prenom_orth.getText() ;String adr = adr_orth.getText() ;
        String mail = mail_orth.getText() ;String num = numtele_orth.getText() ;
        if(nom.isEmpty() || prenom.isEmpty() ||motpasse.isEmpty() || adr.isEmpty() || num.isEmpty() || mail.isEmpty()) {
            emptyelement.setText("Veuillez continuez à remplir Tous les Information  ");
        }else{
            emptyelement.setText("");
            Orthophoniste orth = new Orthophoniste(nom, prenom, adr, mail, num, motpasse);
            String text = "Bienvenu chez nous " + orth.getNom();
       /* Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        SystemeCabinetOrtho cabinet =  (SystemeCabinetOrtho) stage.getUserData();*/
          /*  Anamnese an1 = orth.getAnamnese();
            an1.AjouterQuestion("quelle est votre nom ? ", CatQuestionAdulte.HISTOIRE_MALADIE);
            an1.AjouterQuestion("quelle est votre age ? ", CatQuestionEnfant.STRUCTURE_FAMILIALE);
            Patient p = new Enfant("aa", "bb", "cc", "dd", "ee", "ff", "758679856");
            DossierPatient e = new DossierPatient(p, orth);
            Bilan b1 = new Bilan();
            Diagnostic d1 = new Diagnostic(Trouble.degultition);
            b1.setDiagnostic(d1);
            e.ajouterListBilan(b1);
            orth.ajouterDocPatient(e);
            Patient p1 = new Enfant("aa1", "bb1", "cc", "dd", "ee", "ff", "758679856");
            DossierPatient e1 = new DossierPatient(p1, orth);
            Bilan b2 = new Bilan();
            Diagnostic d2 = new Diagnostic(Trouble.cognitif);
            b2.setDiagnostic(d2);
            e1.ajouterListBilan(b2);
            orth.ajouterDocPatient(e1);*/

            ObjectHolder holder = ObjectHolder.getInstance();
            SystemeCabinetOrtho cabinet = (SystemeCabinetOrtho) holder.getUser();
            cabinet.ajouterortho(orth);
            holder.setUser(orth);
            new SceneControler(scenecreer, "compteOrtho.fxml");
            welcomeText.setText(text);


        }
    }
}