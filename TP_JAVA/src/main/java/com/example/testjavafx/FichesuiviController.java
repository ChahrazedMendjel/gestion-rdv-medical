package com.example.testjavafx;

import javafx.scene.layout.AnchorPane;
import noyau.Orthophoniste;
import noyau.Dossier.*;
import javafx.collections.FXCollections;
import com.example.testjavafx.donneetab.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class FichesuiviController implements Initializable {

    @FXML
    private Button Ajouterobjectif;
    @FXML
    private Label label1;

    @FXML
    private TableColumn<GestionFich, String> goal;

    @FXML
    private TableColumn<GestionFich, TypeObjectifs> goaltype;

    @FXML
    private TableColumn<GestionFich,String> nom;

    @FXML
    private TextField nomgoalacreer;

    @FXML
    private TextField nomtocreer;

    @FXML
    private TableView<GestionFich> tablefich;

    @FXML
    private ChoiceBox<TypeObjectifs> typeobjectif;

    @FXML
    private Button valider;
    @FXML
    private AnchorPane fichsuivipane;
    int tester=0;
    FicheSuivi f6=null;




    ObjectHolder holder = ObjectHolder.getInstance();
    DossierPatient doc = (DossierPatient) holder.getUser();
   // Orthophoniste ort1= new Orthophoniste("firse","ortho","dagi","email","2928","bjhff");

    public void initialize(URL arg0, ResourceBundle arg1){
        /*Patient patient1= new Enfant("nom1","prenom1","adress1","rien","rien","rien","rien");
        Patient patient2= new Enfant("nom2","prenom2","adress1","rien","rien","rien","rien");
        Patient patient3= new Enfant("nom3","prenom3","adress1","rien","rien","rien","rien");
        DossierPatient dossier1 = new DossierPatient(patient1);
        System.out.println("numero dossier");
        System.out.println(dossier1.getNumDossier());
        DossierPatient dossier2 = new DossierPatient(patient2);
        DossierPatient dossier3 = new DossierPatient(patient3);
        FicheSuivi f1= new FicheSuivi("firstfich1");
        f1.ajouterobj("Faire bouger la langue", TypeObjectifs.COURT_TERME);
        f1.ajouterobj("geste facial",TypeObjectifs.LONG_TERME);
        dossier1.ajouterFS(f1);


        ort1.ajouterDocPatient(dossier1);
        //ort1.ajouterDocPatient(dossier2);
        //ort1.ajouterDocPatient(dossier3);*/

        ObservableList<GestionFich> data = FXCollections.observableArrayList();
         List<FicheSuivi> table = doc.getFicheSuivi();
        for (FicheSuivi ele : table) {
            List<Objectifs> lesobjectifs= ele.getListobjs();
            for(Objectifs sele : lesobjectifs){
             GestionFich obj= new GestionFich(ele.getNomFichSuivi(),sele.getEnonceObj(),sele.getTypeobj());
                data.add(obj);
            }
        }

       nom.setCellValueFactory(new PropertyValueFactory<GestionFich,String>("name"));
        //nom.setCellFactory(TextFieldTableCell.forTableColumn());
      goal.setCellValueFactory(new PropertyValueFactory<GestionFich,String>("Goalname"));
        // prenom.setCellFactory(TextFieldTableCell.forTableColumn());
       goaltype.setCellValueFactory(new PropertyValueFactory<GestionFich,TypeObjectifs>("Typeofgoal"));
       tablefich.setItems(data);

        typeobjectif.setItems(FXCollections.observableArrayList(TypeObjectifs.COURT_TERME,TypeObjectifs.MOYEN_TERME,TypeObjectifs.LONG_TERME));
        typeobjectif.setValue(TypeObjectifs.MOYEN_TERME);
    }
    String noun=null;
    public   void Ajoutvalidate() {
        if (nomtocreer.getText().isEmpty()|| nomgoalacreer.getText().isEmpty() ) {
            System.out.println("hereee");
            label1.setText("Veuillez entrer au moins un objectif et un nom pour la fiche de suivi");
        }
        else {
            ///Obliger le user a fixer le nom de la fiche de suivi c a dire il ne peut pas changer le nom une foix ajouter
            tester++;
            if (tester > 1) {
                if(noun!=nomgoalacreer.getText()){label1.setText("Vous pouvez uniquement ajouter une fiche de suivi à la fois");}
                f6.ajouterobj(nomgoalacreer.getText(), typeobjectif.getValue());
            } else {
                f6 = new FicheSuivi(nomtocreer.getText());
                f6.ajouterobj(nomgoalacreer.getText(), typeobjectif.getValue());
                noun = nomtocreer.getText();
            }
            tablefich.getItems().add(new GestionFich(nomtocreer.getText(),nomgoalacreer.getText(),typeobjectif.getValue()));
            System.out.println(f6.getNomFichSuivi());
        }
    }


    @FXML
    void Validateaction() {
        if (nomtocreer.getText().isEmpty() || nomgoalacreer.getText().isEmpty() ) {
            label1.setText("Veuillez entrer au moins un objectif et un nom pour la fiche de suivi");
        }
        else{
            if (f6==null){label1.setText("Veuillez entrer les informations necessaires pour ajouter une fiche de suivi");}
            else{

      boolean wagi= doc.ajouterFS(f6);
      if(!wagi){
          label1.setText("Vous ne pouvez pas ajouter une fiche de suivi");
      } else{

      System.out.println(wagi);
      System.out.println("xagi");}
//Remember to set a label when it is not possible to add une fiche
        List<FicheSuivi> tables = doc.getFicheSuivi();
        for (FicheSuivi ele : tables) {
            List<Objectifs> lesobjectifs= ele.getListobjs();
            for(Objectifs sele : lesobjectifs){
                System.out.println("la fiche");
                System.out.println(ele.getNomFichSuivi());
                System.out.println("l objectif");
                System.out.println(sele.getEnonceObj());
            }

        }

    }}

        }
    @FXML
    void OnRetourClick(ActionEvent event) {
        try{
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(doc);
            new SceneControler(fichsuivipane, "Dossierpatients.fxml");

        }catch (IOException e){
            System.out.println("IOException inside");
        }
    }


}




