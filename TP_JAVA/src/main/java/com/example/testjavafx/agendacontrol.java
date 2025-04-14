package com.example.testjavafx;

import javafx.scene.layout.AnchorPane;
import noyau.Dossier.DossierPatient;
import noyau.RendezVous.*;
import noyau.Orthophoniste;
import com.example.testjavafx.donneetab.TableviewDossier;
import com.example.testjavafx.donneetab.rdvtable;
import com.example.testjavafx.donneetab.tipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Set;

public class agendacontrol implements Initializable {

    @FXML
    private Button Valider;

    @FXML
    private TableColumn<rdvtable, LocalDate> date;

    @FXML
    private DatePicker datepick;

    @FXML
    private TableColumn<rdvtable,LocalTime> heure;

    @FXML
    private Spinner<Integer> heuredebut;

    @FXML
    private Spinner<Integer> minutedebut;

    @FXML
    private TableView<rdvtable> tabledossier;

    @FXML
    private TableColumn<rdvtable, tipe> type;

    @FXML
    private ChoiceBox<tipe> typerdv;
     @FXML
    private Label label1;

    @FXML
    private AnchorPane agendapane;
    private static agendacontrol agenda ;
    public static void setAgenda(agendacontrol selectedTab){
        agenda = selectedTab;
    }
    public static agendacontrol getAgenda() {
        return agenda ;
    }
    private  rdvtable edvtime ;
    public  void setrdv(rdvtable selectedTab){
        edvtime = selectedTab;
    }
    public  rdvtable getrdv() {
        return edvtime;
    }
    private DossierPatient doc ;

    SpinnerValueFactory<Integer> svfhs= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste ort1 = (Orthophoniste) holder.getUser();
    //Orthophoniste ort1= new Orthophoniste("firse","ortho","dagi","email","2928","bjhff");
        SpinnerValueFactory<Integer> svfms= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);///Creation du dossier;
    public void initialize(URL arg0, ResourceBundle arg1){
     System.out.println("here chahra");
    /*Patient patient1= new Enfant("nom1","prenom1","adress1","rien","rien","rien","rien");
    Patient patient2= new Enfant("nom2","prenom2","adress1","rien","rien","rien","rien");
    Patient patient3= new Enfant("nom3","prenom3","adress1","rien","rien","rien","rien");
    DossierPatient dossier1 = new DossierPatient(patient1);
    System.out.println("numero dossier");
        System.out.println(dossier1.getNumDossier());
    DossierPatient dossier2 = new DossierPatient(patient2);
    DossierPatient dossier3 = new DossierPatient(patient3);
        TableviewDossier patienttable1 = new TableviewDossier(patient1.getNom(),patient1.getPrenom(),dossier1.getNumDossier());
        TableviewDossier patienttable2 = new TableviewDossier(patient2.getNom(),patient2.getPrenom(),dossier2.getNumDossier());
        ort1.ajouterDocPatient(dossier1);
        ort1.ajouterDocPatient(dossier2);
        ort1.ajouterDocPatient(dossier3);


        ObservableList<TableviewDossier> data = FXCollections.observableArrayList();
        data.add(patienttable1);
        data.add(patienttable2);*/
        //LocalDate day=LocalDate.now();
        //LocalTime hour=LocalTime.now();
       Agenda ag1= ort1.getAgenda();
        //RendezVous rdv1= new Consultation("nom1","prenom1",12,hour,day,"hjdj");
        //ag1.ajouterRdv(rdv1);
        Set<RendezVous> table = ag1.getRDV();
        ObservableList<rdvtable> data = FXCollections.observableArrayList();
        for (RendezVous ele : table) {
            tipe rdvType = tipe.valueOf(ele.getClass().getSimpleName());
             rdvtable rdv=new rdvtable(ele.getJour(),ele.getHeure(),rdvType);
             data.add(rdv);
        }




        date.setCellValueFactory(new PropertyValueFactory<rdvtable, LocalDate>("Jour"));
        //nom.setCellFactory(TextFieldTableCell.forTableColumn());
        heure.setCellValueFactory(new PropertyValueFactory<rdvtable, LocalTime>("Heure"));
        // prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setCellValueFactory(new PropertyValueFactory<rdvtable, tipe>("Tipe"));



        tabledossier.setItems(data);
       /* int hours = heuredebut.getValue();
        int minutes = minutedebut.getValue();
        LocalTime selectedTime = LocalTime.of(hours, minutes);
        LocalDate selectedDatee = datepick.getValue();*/

       typerdv.setItems(FXCollections.observableArrayList(tipe.Consultation, tipe.Suivi, tipe.Atelier));
          heuredebut.setValueFactory(svfhs);
                 heuredebut.setEditable(true);
                 minutedebut.setValueFactory(svfms);
                 minutedebut.setEditable(true);
                 typerdv.setValue(tipe.Consultation);


    }
    public void gotobilan(MouseEvent event) throws IOException {  // remplir bilan
        // si consultation aller à bilan 1 puis remplir bilan
        // sinon aller directement à remplir bilan
      /*  rdvtable select = tabledossier.getSelectionModel().getSelectedItem();
        Main m= new Main();
        m.changeScene("bilan1.fxml");*/  // je doit recuperer le rendezvous
        rdvtable select = tabledossier.getSelectionModel().getSelectedItem();
        if( select.getTipe().equals(tipe.Consultation)){
            try {
                ObjectHolder holder = ObjectHolder.getInstance();
                holder.setUser(ort1);
                this.setrdv(select);
                agendacontrol.setAgenda(this);

                new SceneControler(agendapane, "bilan1.fxml");

            } catch (IOException e){
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else if(select.getTipe().equals(tipe.Suivi)){
            try {

                Agenda ag = ort1.getAgenda();
                RendezVous rdv =  ag.getRdv(select.getHeure(),select.getJour());
                //if(rdv instanceof Suivi){
                    int numdoc =  ((Suivi)rdv).getNDossier();
                //}
                this.setDoc(ort1.getDossierDeNumPatient(numdoc));
                ObjectHolder holder = ObjectHolder.getInstance();
                holder.setUser(ort1);
                Bilan1 bil = new Bilan1();
                Bilan1.setBil(bil); bil.setP(null);
                this.setrdv(select);
                agendacontrol.setAgenda(this);

                //this.setDoc(o);
                new SceneControler(agendapane, "RemplirBilan.fxml");
            } catch (IOException e){
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else if(select.getTipe().equals(tipe.Atelier)){
            // walou
        }
    }
    public void valider() throws IOException {
           LocalDate selectedDate = datepick.getValue();
                      LocalDate currentDate = LocalDate.now();// i need to send it

                      if (selectedDate != null) {
                          if (selectedDate.isBefore(currentDate)) {
                              label1.setText("Veuillez séléctionner une date valide");
                          }
                          else{
                              int hours = heuredebut.getValue();
                              int minutes = minutedebut.getValue();
                              LocalTime selectedTime = LocalTime.of(hours, minutes);// i need to send it
                              rdvtable currentelement = new rdvtable(selectedDate,selectedTime,typerdv.getValue());
                              if (typerdv.getValue()==tipe.Consultation){
                                  try {
                                      ObjectHolder holder = ObjectHolder.getInstance();
                                      holder.setUser(ort1);this.setrdv(currentelement);
                                      agendacontrol.setAgenda(this);

                                      new SceneControler(agendapane, "consultation.fxml");
                                  } catch (IOException e){
                                      System.out.println("IOException");
                                      e.printStackTrace();
                                  }

                              }
                              else{
                                  if(typerdv.getValue()==tipe.Suivi) {
                                      try {
                                          ObjectHolder holder = ObjectHolder.getInstance();
                                          holder.setUser(ort1);this.setrdv(currentelement);
                                          agendacontrol.setAgenda(this);

                                          new SceneControler(agendapane, "suivi.fxml");

                                      } catch (IOException e){
                                          System.out.println("IOException");
                                          e.printStackTrace();
                                      }

                                  }
                                  else{
                                      if (typerdv.getValue()==tipe.Atelier){
                                          try {
                                              ObjectHolder holder = ObjectHolder.getInstance();
                                              holder.setUser(ort1);
                                              agendacontrol.setAgenda(this);
                                              this.setrdv(currentelement);
                                              new SceneControler(agendapane, "atelier.fxml");

                                          } catch (IOException e){
                                              System.out.println("IOException");
                                              e.printStackTrace();
                                          }
                                      }
                                      else{
                                          label1.setText("Veuillez choisir le type de votre rdv");
                                      }

                                  }

                              }

                          }
                      }
                      else {
                              label1.setText("Veuillez entrer la date du rdv ");
                          }


    }

    @FXML
    void onRetourClick(ActionEvent event) {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(ort1);
            System.out.println("retour");
            new SceneControler(agendapane, "compteOrtho.fxml");
        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public DossierPatient getDoc() {
        return doc;
    }

    public void setDoc(DossierPatient doc) {
        this.doc = doc;
    }
}
