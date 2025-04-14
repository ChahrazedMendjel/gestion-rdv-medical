package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import noyau.Dossier.Adulte;
import noyau.Dossier.Enfant;
import noyau.Dossier.Patient;
import noyau.Orthophoniste;

import java.io.IOException;

public class Bilan1 {

    @FXML
    private Button Suivant;

    @FXML
    private TextField adresse;

    @FXML
    private TextField classe;

    @FXML
    private DatePicker date;

    @FXML
    private TextField diplome;

    @FXML
    private Label label1;

    @FXML
    private TextField lieu;

    @FXML
    private TextField profession;

    @FXML
    private TextField teleph;

    @FXML
    private TextField telephparent;
    @FXML
    private AnchorPane bilan1pane;
    @FXML
    private Label emptylabel;
    private static Bilan1 bil ;
    private Patient p ;
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste ort1 = (Orthophoniste) holder.getUser();

    public static Bilan1 getBil() {
        return bil;
    }

    public static void setBil(Bilan1 bil) {
        Bilan1.bil = bil;
    }


    @FXML
    void Suivantpage(ActionEvent event) {

        // creer le patient et envoyer l'objet patient à remplir bilan
        if(!adresse.getText().isEmpty() && !lieu.getText().isEmpty() && date.getValue() != null  ){
            if( !profession.getText().isEmpty() && !telephparent.getText().isEmpty() && !classe.getText().isEmpty() ) {
                ConsultationController cpt = ConsultationController.getconsult();
                String s = cpt.getrdv();
                int index =s.indexOf(':');

                if (index != -1) {
                    String nom = s.substring(0, index).trim();
                    String prenom = s.substring(index + 1).trim();

                    System.out.println("Before ':': " + nom);
                    System.out.println("After ':': " + prenom);

                    Patient patient = new Enfant(nom,prenom,lieu.getText(),adresse.getText(),date.getValue().toString(),classe.getText(),telephparent.getText());
                    try {
                        ObjectHolder holder = ObjectHolder.getInstance();
                        holder.setUser(ort1);
                        Bilan1.setBil(this);
                        this.p = patient;
                        new SceneControler(bilan1pane, "RemplirBilan.fxml");

                    } catch (IOException e) {
                        System.out.println("IOException");
                        e.printStackTrace();
                    }
                }
            }else if( !diplome.getText().isEmpty() && !teleph.getText().isEmpty()){
                ConsultationController cpt = ConsultationController.getconsult();
                String s = cpt.getrdv();
                int index =s.indexOf(':');
                System.out.println("s = "+s);

                if (index != -1) {
                    String nom = s.substring(0, index).trim();
                    String prenom = s.substring(index + 1).trim();

                    System.out.println("Before ':': " + nom);
                    System.out.println("After ':': " + prenom);

                    Patient patient = new Adulte(nom,prenom,lieu.getText(),adresse.getText(),date.getValue().toString(),diplome.getText(),profession.getText(),teleph.getText());
                    try {
                        ObjectHolder holder = ObjectHolder.getInstance();
                        holder.setUser(ort1);
                        Bilan1.setBil(this);
                        this.p = patient;
                        new SceneControler(bilan1pane, "RemplirBilan.fxml");

                    } catch (IOException e) {
                        System.out.println("IOException");
                        e.printStackTrace();
                    }
                }
            }else{
                emptylabel.setText("Veuillez continuer à remplir Toutes les Informations");
            }
        }else{
            emptylabel.setText("Veuillez continuer à remplir Toutes les Informations");

        }

    }

    public Patient getP() {
        return p;
    }

    public void setP(Patient p) {
        this.p = p;
    }
}
