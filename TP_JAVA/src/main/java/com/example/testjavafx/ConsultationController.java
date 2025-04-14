package com.example.testjavafx;

import com.example.testjavafx.donneetab.rdvtable;
import javafx.scene.layout.AnchorPane;
import noyau.RendezVous.*;
import noyau.Orthophoniste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ConsultationController  {
    @FXML
    private Button button;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private Label wronglogin;
    @FXML
    private AnchorPane consultpane;
    private static ConsultationController consult ;
    public static void setConsult(ConsultationController selectedTab){
        consult = selectedTab;
    }
    public static ConsultationController getconsult() {
        return consult ;
    }
    private  String nompre ;
    public  void setrdv(String selectedTab){
        nompre= selectedTab;
    }
    public  String getrdv() {
        return nompre;
    }

    agendacontrol ag  = agendacontrol.getAgenda();
    rdvtable rdv = ag.getrdv();

    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste ort1 = (Orthophoniste) holder.getUser();    Agenda age1= ort1.getAgenda();

    public void ValiderAction(ActionEvent event) throws IOException {
        checklogin();

    }

    public void checklogin() throws IOException {
        boolean canAddrdv = true;

        if ((nom.getText().isEmpty()) || (prenom.getText().isEmpty()) || (age.getText().isEmpty())) {
            wronglogin.setText("Veuillez entrer les informations necessaires");
        } else {
            LocalDate selectedDate = rdv.getJour();
            LocalTime selectedTime= rdv.getHeure();
            System.out.println("hour =  "+selectedTime);

            System.out.println(" selectdate"+selectedDate);
            System.out.println(" selecttime"+selectedTime);


            String ageText = age.getText();
            System.out.println(ageText.matches("\\d*"));
            boolean isaNum= ageText.matches("\\d*");
            if (!isaNum){
                wronglogin.setText("Veuillez entrer un age valide");
            }
            else {
                int ageValue = Integer.parseInt(age.getText());
                String typee;
                if(ageValue <18){
                  typee="enfant";
                }
                else{
                  typee="adulte";
                }


                RendezVous rd1= new Consultation(nom.getText(),prenom.getText(),ageValue,selectedTime,selectedDate,typee);

               boolean add = age1.ajouterRdv(rd1);              age1.afficherRdvs();

                if(add == true){
                try {
                    String s = nom.getText();
                    s = s.concat(":"+prenom.getText());
                    this.setrdv(s);
                    ConsultationController.setConsult(this);
                    ObjectHolder holder = ObjectHolder.getInstance();
                    holder.setUser(ort1);

                    new SceneControler(consultpane, "compteOrtho.fxml");

                } catch (IOException e){
                    System.out.println("IOException");
                    e.printStackTrace();
                }}else{
                    wronglogin.setText("Vous ne pouvez pas ajouter ce rendez-vous en raison d'intersection avec d'autres rendez-vous");
                }

            }


        }
    }

    @FXML
    void Onretourclick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(ort1);
            // new SceneControler(sceneBienvenu, "RemplirBilan.fxml");
            new SceneControler(consultpane, "agenda.fxml");

        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }


}
