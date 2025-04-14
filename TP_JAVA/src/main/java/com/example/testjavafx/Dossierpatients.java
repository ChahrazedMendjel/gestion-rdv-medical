package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Anamnese.Anamnese;
import noyau.Dossier.DossierPatient;
import noyau.Dossier.Patient;
import noyau.Orthophoniste;
import noyau.RendezVous.Atelier;
import noyau.RendezVous.Consultation;
import noyau.RendezVous.RendezVous;
import noyau.RendezVous.Suivi;
import noyau.SystemeCabinetOrtho;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Dossierpatients implements Initializable {

    @FXML
    private Label adrp;

    @FXML
    private Label dateNp;

    @FXML
    private Label lieuNp;

    @FXML
    private Label nomp;

    @FXML
    private Label ortho;

    @FXML
    private Label prenomp;

    @FXML
    private ListView<String> listrv;
    @FXML
    private AnchorPane docscene;
    private boolean isDeleteAction = false;
    public boolean isDeleteAction() {
        return isDeleteAction;
    }
    ObjectHolder holder = ObjectHolder.getInstance();
    DossierPatient doc = (DossierPatient) holder.getUser();


    @Override
    public void initialize  (URL location, ResourceBundle resources){
        ArrayList<String> choix = new ArrayList<String>();
        System.out.println("N doc "+doc.getNumDossier());
        Iterator<RendezVous> it = doc.getRendezVous().iterator();
        System.out.println("i am in doc patient ");
        while (it.hasNext()){
            RendezVous rdv = it.next();
            if( rdv instanceof Consultation ){
                System.out.println("consultation");
                choix.add(String.valueOf(rdv.getJour().toString().concat("  |  ").concat(rdv.getHeure().toString().concat(" => Consultation"))));
            }else if(rdv instanceof Suivi){
                choix.add(String.valueOf(rdv.getJour().toString().concat("  |  ").concat(rdv.getHeure().toString().concat(" => Suivi"))));
            }else if(rdv instanceof Atelier){
               Iterator<Integer> itatelier = ((Atelier)rdv).getListPatients().iterator() ;
               String sommeAtelier = "";
               while (itatelier.hasNext()){
                   sommeAtelier.concat(itatelier.next().toString());
               }
                choix.add(sommeAtelier.concat(" => Atelier"));

            }
        }
        System.out.println("choix "+choix);
        //choix.add("1ereconsultehgryefgyuzetfdyftefyufzetfte => Consultation");
        listrv.getItems().addAll(choix);

        Patient p =doc.getPatient();
        ortho.setText(p.getNom());
        nomp.setText(p.getNom());
        prenomp.setText(p.getPrenom());
        lieuNp.setText(p.getLieuNaissance());
        dateNp.setText(p.getDateNaissance());
        adrp.setText(p.getAdresse());


    }
    @FXML
    void onBillanClick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            System.out.println("bilan "+doc.getListBilan().get(0).getObservation());
            holder.setUser(doc);
            new SceneControler(docscene, "AfficherBilan.fxml");

        }catch (IOException e){
            System.out.println("IOException inside");
        }
    }

    @FXML
    void onFichSuiviClick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            System.out.println("bilan "+doc.getListBilan().get(0).getObservation());
            holder.setUser(doc);
            new SceneControler(docscene, "Fichesuivi.fxml");

        }catch (IOException e){
            System.out.println("IOException inside");
            e.printStackTrace();
        }
    }

    @FXML
    void onRVclick() {

    }

    @FXML
    void onRetourClick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(doc.getOrtho());
            new SceneControler(docscene, "compteOrtho.fxml");

        }catch (IOException e){
            System.out.println("IOException inside");
        }

    }
    @FXML
    void onSupprimerclick() {
        isDeleteAction = true;
        compteController cpt =  compteController.getcpt();
        cpt.delletedoc();
        ObjectHolder holder = ObjectHolder.getInstance();
        holder.setUser(doc.getOrtho());
        doc.getOrtho().supprimerDocPatient(doc);
        try {
            new SceneControler(docscene, "compteOrtho.fxml");

        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}
