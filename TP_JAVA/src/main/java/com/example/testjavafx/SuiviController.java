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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class SuiviController implements Initializable {

    @FXML
    private Button Valider;
    @FXML
    private HBox checkboxContainer;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton enligne;

    @FXML
    private RadioButton enpresentiel;
    @FXML
    private  ChoiceBox objectifs;

    @FXML
    private Spinner<?> heure;

    @FXML
    private Spinner<?> minute;

    @FXML
    private TableColumn<TableviewDossier, String> nom;

    @FXML
    private TableColumn<TableviewDossier, Integer> numero;

    @FXML
    private TableColumn<TableviewDossier, String> prenom;
    @FXML
    private TableColumn<TableviewDossier, Boolean> checkColumn;
    @FXML
    private TableView<TableviewDossier> tabledossier;

    @FXML
    private ToggleGroup type;
    @FXML
    private Label label;
    @FXML
    private AnchorPane suivipane;
    private static SuiviController suiv ;
    private DossierPatient doss ;
    //Orthophoniste ort1= new Orthophoniste("firse","ortho","dagi","email","2928","bjhff");
    ///Creation du dossier;
    agendacontrol ag  = agendacontrol.getAgenda();
    rdvtable rdv = ag.getrdv();
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste ort1 = (Orthophoniste) holder.getUser();
    DossierPatient doc = null;

    public static SuiviController getSuiv() {
        return suiv;
    }

    public static void setSuiv(SuiviController suiv) {
        SuiviController.suiv = suiv;
    }

    public void initialize(URL arg0,ResourceBundle arg1){

      /*  Patient patient1= new Enfant("nom1","prenom1","adress1","rien","rien","rien","rien");
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
    public void getCheckedCheckBoxes() {
        List<String> selectedObjectives = new ArrayList<>();
        int lastfich= doc.getFicheSuivi().size()-1;
        FicheSuivi fchoisi = doc.getFicheSuivi().get(lastfich);
        for (javafx.scene.Node node : checkboxContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                Objectifs obj = fchoisi.getObjnom(checkBox.getText());
                if (checkBox.isSelected()) {
                    obj.setSelected(true);
                    selectedObjectives.add(checkBox.getText());
                }else{
                    obj.setSelected(false);
                }
            }
        }
        // Do something with the selected objectives
        System.out.println("Selected objectives: " + selectedObjectives);
    }
    public void here() {
        TableviewDossier select = tabledossier.getSelectionModel().getSelectedItem();
        System.out.println("numero dossier ");
        int nb;
        // DossierPatient
        if (select.isChecked()){
            select.setChecked(false);
        }
        else{
            select.setChecked(true);
        }
        tabledossier.refresh();
        /////////////////////
        ArrayList<Integer> numbers = new ArrayList<>();
        TableView<TableviewDossier> table = tabledossier;

// Iterate over the rows of the table
        for (TableviewDossier item : table.getItems()) {
            if (item.isChecked()) {
                int numeroValue = item.getNumber();
                numbers.add(numeroValue);
            }
        }

        if(numbers.size()==1){
            int indice= select.getNumber();
             doc = ort1.getDossierDeNumPatient(indice);
            int lastfich= doc.getFicheSuivi().size()-1;
            if(lastfich<0){
                label.setText("il n'y aucune fiche de suivi à afficher");
            }
            else {
                FicheSuivi fchoisi = doc.getFicheSuivi().get(lastfich);
                List<Objectifs> Pist = fchoisi.getListobjs();

                //checkboxContainer.getChildren().clear();

                // Create and add checkboxes
                for (Objectifs ele : Pist) {
                    String unobj = ele.getEnonceObj();
                    CheckBox checkBox = new CheckBox(unobj);
                    checkboxContainer.getChildren().add(checkBox);

                }
            }

        }}







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


        if(numbers.size()==1){
            int  nb=numbers.get(0);
            LocalTime currentTime = rdv.getHeure();
            LocalDate currentDate = rdv.getJour();
            Toggle typerdv = type.getSelectedToggle();
            RendezVous rdv1 = null;
            String sienligne = null;
            if (typerdv == enligne) {
                sienligne = "en ligne";
                rdv1 = new Suivi(currentTime, currentDate, nb, sienligne);

            } else {
                if (typerdv == enpresentiel) {
                    sienligne = "presentielle";
                    rdv1 = new Suivi(currentTime, currentDate, nb, sienligne);
                    if(objectifs!=null){
                        label.setText("succés");
                    }
                    else{
                        label.setText("Vous n'avez pas choisit d'objectifs");
                    }

                }
            }
            System.out.println("typerdv"+typerdv);
            if( typerdv != null ) {
                ort1.afficherdocs();
                ort1.ajouterRdvPatient(nb, rdv1);
                getCheckedCheckBoxes();
                boolean add = ort1.getAgenda().ajouterRdv(rdv1);
                System.out.println("i am inside ajouter suivi " + rdv1);
                ort1.getAgenda().afficherRdvs();
                //DossierPatient dossier = ort1.getDossierDeNumPatient(nb);
                //dossier.ajouterRdv(rdv1);
                if(add == true){
                try {
                    ObjectHolder holder = ObjectHolder.getInstance();
                    holder.setUser(ort1);

                    new SceneControler(suivipane, "compteOrtho.fxml");
                } catch (IOException e) {
                    System.out.println("IOException");
                    e.printStackTrace();
                }
            }else{
                    label.setText("Vous ne pouvez pas ajouter ce rendez-vous en raison d'intersection avec d'autres rendez-vous");
                }
            }
            else {
                label.setText("Veuillez choisir le type");
            }
        }
        else{
            label.setText("Veuillez choisir le patient");
        }
        System.out.println("now");

    }

    public DossierPatient getDoss() {
        return doss;
    }

    public void setDoss(DossierPatient doss) {
        this.doss = doss;
    }
    @FXML
    void OnRetourclick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(ort1);
            System.out.println("retour");
            new SceneControler(suivipane, "compteOrtho.fxml");
        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }
}

