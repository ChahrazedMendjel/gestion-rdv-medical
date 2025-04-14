package com.example.testjavafx;
import javafx.scene.layout.AnchorPane;
import noyau.Dossier.* ;


import noyau.RendezVous.*;
import com.example.testjavafx.donneetab.* ;
import noyau.Orthophoniste ;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AtelierController implements Initializable {

    @FXML
    private Button Valider;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton enligne;

    @FXML
    private RadioButton enpresentiel;

    @FXML
    private Spinner<?> heure;

    @FXML
    private Spinner<?> minute;
    @FXML
    private TextField thematique;

    @FXML
    private TableColumn<TableviewDossier, String> nom;

    @FXML
    private TableColumn<TableviewDossier, Integer> numero;

    @FXML
    private TableColumn<TableviewDossier, String> prenom;
    @FXML
    private TableColumn<TableviewDossier, Boolean> checkColumn;
    @FXML
    private TableColumn<TableviewDossier, Boolean> radioButtonColumn;
    @FXML
    private TableView<TableviewDossier> tabledossier;

    @FXML
    private ToggleGroup type;
    @FXML
    private Label label;
    @FXML
    private AnchorPane atelierpane;
    agendacontrol ag  = agendacontrol.getAgenda();
    rdvtable rdv = ag.getrdv();
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste ort1 = (Orthophoniste) holder.getUser();    ///Creation du dossier;
    public void initialize(URL arg0,ResourceBundle arg1){
/*
        Patient patient1= new Enfant("nom1","prenom1","adress1","rien","rien","rien","rien");
        Patient patient2= new Enfant("nom2","prenom2","adress1","rien","rien","rien","rien");
        Patient patient3= new Enfant("nom3","prenom3","adress1","rien","rien","rien","rien");
        DossierPatient dossier1 = new DossierPatient(patient1,ort1);
        System.out.println("numero dossier");
        System.out.println(dossier1.getNumDossier());
        DossierPatient dossier2 = new DossierPatient(patient2,ort1);
        DossierPatient dossier3 = new DossierPatient(patient3,ort1);

        ort1.ajouterDocPatient(dossier1);
        ort1.ajouterDocPatient(dossier2);
        ort1.ajouterDocPatient(dossier3);*/

        ObservableList<TableviewDossier> data = FXCollections.observableArrayList();
        HashSet<DossierPatient> table=ort1.getListDocPatient();
        for (DossierPatient ele : table) {
            TableviewDossier dosser =new TableviewDossier(ele.getPatient().getNom(),ele.getPatient().getPrenom(), ele.getNumDossier(), false);
            data.add(dosser);
        }





        nom.setCellValueFactory(new PropertyValueFactory<TableviewDossier,String>("Familyname"));
        //nom.setCellFactory(TextFieldTableCell.forTableColumn());
        prenom.setCellValueFactory(new PropertyValueFactory<TableviewDossier,String>("Name"));
        // prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        numero.setCellValueFactory(new PropertyValueFactory<TableviewDossier,Integer>("Number"));
        checkColumn.setCellValueFactory(new PropertyValueFactory<TableviewDossier,Boolean>("Checked"));
        // checkColumn.setCellFactory(CheckBoxTableCell.forTableColumn());
        //checkColumn.setCellFactory(column -> new CheckBoxTableCell<>());
        // ToggleGroup toggleGroup = new ToggleGroup();
        //radioButtonColumn.setCellFactory(RadioButtonTableCell.forTableColumn(toggleGroup));

        tabledossier.setItems(data);


    }

    public void here() {
        TableviewDossier select = tabledossier.getSelectionModel().getSelectedItem();
        System.out.println("numero dossier ");
        int nb;
        if (select.isChecked()){
            select.setChecked(false);
        }
        else{
            select.setChecked(true);
        }
        tabledossier.refresh();

    }
    public void Validate(){

        ArrayList<Integer> numbers = new ArrayList<>();
        TableView<TableviewDossier> table = tabledossier;

// Iterate over the rows of the table
        for (TableviewDossier item : table.getItems()) {
            if (item.isChecked()) {
                int numeroValue = item.getNumber();
                numbers.add(numeroValue);
            }

        }


        if(numbers.size()>1){
            LocalTime currentTime = rdv.getHeure();
            LocalDate currentDate = rdv.getJour();
           String txt= thematique.getText();
             System.out.println(txt);

            if(txt!=null){
               RendezVous rdv= new Atelier(txt,currentTime,currentDate,numbers);
                Agenda ag1=ort1.getAgenda();
                boolean add = ag1.ajouterRdv(rdv);
                ag1.afficherRdvs();
                if(add == true){
              // System.out.println("yes");
                //DossierPatient dossier = ort1.getDossierDeNumPatient(nb);
                //dossier.ajouterRdv(rdv1);
                try {
                    ObjectHolder holder = ObjectHolder.getInstance();
                    holder.setUser(ort1);

                    new SceneControler(atelierpane, "compteOrtho.fxml");

                } catch (IOException e){
                    System.out.println("IOException");
                    e.printStackTrace();
                }
                }else{
                    label.setText("Vous ne pouvez pas ajouter ce rendez-vous en raison d'intersection avec d'autres rendez-vous");
                }
            }
            else {
                label.setText("Veuillez choisir la thematique");
            }
        }
        else{
            label.setText("Veuillez choisir au moins deux patients");
        }





    }

    @FXML
    void Onretourclick() {

        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(ort1);
            // new SceneControler(sceneBienvenu, "RemplirBilan.fxml");
            new SceneControler(atelierpane, "agenda.fxml");

        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }

    }




}





