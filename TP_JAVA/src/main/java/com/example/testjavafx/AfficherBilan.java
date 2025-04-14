package com.example.testjavafx;

import com.example.testjavafx.donneetab.AnaTab;
import com.example.testjavafx.donneetab.TestTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import noyau.Anamnese.Anamnese;
import noyau.Anamnese.QuestionAdulte;
import noyau.Anamnese.QuestionAnamnese;
import noyau.Anamnese.QuestionEnfant;
import noyau.Bilan.Bilan;
import noyau.Bilan.PremierBilan;
import noyau.CompteRenduReponce.CRE;
import noyau.CompteRenduReponce.CRQ;
import noyau.CompteRenduReponce.CompteRendu;
import noyau.CompteRenduReponce.ReponceAna;
import noyau.Dossier.DossierPatient;
import noyau.Test.TestExer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class AfficherBilan implements Initializable {

    @FXML
    private Label nomdiag;

    @FXML
    private Label obser;

    @FXML
    private Label ortho;

    @FXML
    private Label projet;

    @FXML
    private TableColumn<TestTab, Integer> scoremoy;

    @FXML
    private TableView<TestTab> tableviewtest;

    @FXML
    private TableColumn<TestTab, String> testnom;

    @FXML
    private Label troublediag;
    @FXML
    private ListView<String> listviewbilan;
    @FXML
    private TableView<AnaTab> anamnesetab;
    @FXML
    private TableColumn<AnaTab, String> enonce;
    @FXML
    private TableColumn<AnaTab, String> type;

    @FXML
    private TableColumn<TestTab, String> typetest;

    @FXML
    private AnchorPane afficherbilanscene;
    @FXML  private AnchorPane bilanipane;
    ObjectHolder holder = ObjectHolder.getInstance();
    DossierPatient doc = (DossierPatient) holder.getUser();
    private final ObservableList<TestTab> data = FXCollections.observableArrayList();
    private final ObservableList<AnaTab> data1 = FXCollections.observableArrayList();

    @Override
    public void initialize  (URL location, ResourceBundle resources) {
        try {
            listviewbilan.getItems().add("BILANS");

            ArrayList<Bilan> bilanlist = doc.getListBilan();

            Iterator<Bilan> it = bilanlist.iterator();
            int s = 1;
            while (it.hasNext()) {
                System.out.println("bilan " + it.next().getDiagnostic());

                if (s == 1) {
                    listviewbilan.getItems().add("Bilan1");

                } else {
                    listviewbilan.getItems().add("Bilan" + String.valueOf(s));

                }
                s++;
            }
            listviewbilan.setCellFactory(lv -> {
                ListCell<String> cell = new ListCell<>();
                cell.textProperty().bind(cell.itemProperty());
                cell.setOnMouseClicked(event -> {
                    if (!cell.isEmpty() && !cell.getItem().equals("BILANS")) {
                        String item = cell.getItem();
                        System.out.println("Clicked on: " + item);
                        loadBilan(item); // Load bilan lazily
                        bilanipane.setVisible(true);
                    } else if (!cell.isEmpty() && cell.getItem().equals("BILANS")) {
                        String item = cell.getItem();
                        System.out.println("Clicked on: " + item);
                        bilanipane.setVisible(false);
                    }
                });
                return cell;
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }  private void loadBilan(String item) {
        testnom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        typetest.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        enonce.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("enonce"));
        type.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("type"));
        testnom.setCellValueFactory( new PropertyValueFactory<TestTab,String>("nom"));
        typetest.setCellValueFactory( new PropertyValueFactory<TestTab,String>("type"));
        scoremoy.setCellValueFactory( new PropertyValueFactory<TestTab,Integer>("capacite"));
        int bilanIndex = Integer.parseInt(item.substring(5)) -1;
        Bilan bilan = doc.getListBilan().get(bilanIndex);
        // Process bilan data
        nomdiag.setText(bilan.getDiagnostic().getNom());
        obser.setText(bilan.getObservation());
        troublediag.setText(bilan.getDiagnostic().getTrouble().getTroubleName());
        projet.setText(bilan.getProjettherapeutique());
        ArrayList<CompteRendu> cprlist = bilan.getTabCompteRendu();
        Iterator<CompteRendu> itcpr = cprlist.iterator();
        data.clear(); // Clear existing test data
        data1.clear(); // Clear existing anamnese data
        while (itcpr.hasNext()) {
            CompteRendu cpr = itcpr.next();
            if (cpr instanceof CRQ) {
                CRQ crq = (CRQ) cpr;
               // if(!data.contains(new TestTab(crq.getNomTest(), "Test Question", crq.setscoretotal()))) {
                    data.add(new TestTab(crq.getNomTest(), "Test Question", crq.setscoretotal()));
                //}
            } else if (cpr instanceof CRE) {
                CRE cre = (CRE) cpr;
                TestExer te = ((TestExer) doc.getOrtho().findobjetTest(cre.getNomTest()));
                data.add(new TestTab(cre.getNomTest(), "Test Exercice", (int)cre.setscoretotal(te)));
            }
        }
        tableviewtest.setItems(data);
        if (bilan instanceof PremierBilan) {
            anamnesetab.setVisible(true);
            Map<Integer, String> tabreponceana = ((PremierBilan) bilan).getTabreponceana();

            Iterator<Map.Entry<Integer, String>> iterator = tabreponceana.entrySet().iterator();
            Anamnese ana = doc.getOrtho().getAnamnese();
            // Parcourir la map à l'aide de l'itérateur
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> entry = iterator.next();
                Integer key = entry.getKey();
                String value = entry.getValue();
                data1.add(new AnaTab(ana.recupererQuesenonce(key), value));

            }

            anamnesetab.setItems(data1);
        } else {
            System.out.println("anamnesetab.setVisible(false);");
            anamnesetab.setVisible(false);
        }


}
/*
    @Override
    public void initialize  (URL location, ResourceBundle resources) {
        listviewbilan.getItems().add("BILANS");


        testnom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        typetest.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        enonce.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("Anamnese"));
        type.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("Reponce"));
        testnom.setCellValueFactory( new PropertyValueFactory<TestTab,String>("Test"));
        typetest.setCellValueFactory( new PropertyValueFactory<TestTab,String>("TypeTest"));
        scoremoy.setCellValueFactory( new PropertyValueFactory<TestTab,Integer>("Score/Moyenne"));
        ArrayList<Bilan> bilanlist =  doc.getListBilan();
        Iterator<Bilan> it = bilanlist.iterator(); int s = 1 ;
        while (it.hasNext()) {
            if (s == 1) {
                listviewbilan.getItems().add("Bilan1");
                s ++ ;
            }else{
                listviewbilan.getItems().add("Bilan"+String.valueOf(s));

            }
        }
        listviewbilan.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty() && !cell.getItem().equals("BILANS")) {
                    String item = cell.getItem();
                    System.out.println("Clicked on: " + item);
                    Bilan bilan = doc.getListBilan().get(Integer.parseInt(item.substring(5)));
                    nomdiag.setText(bilan.getDiagnostic().getNom());
                    obser.setText(bilan.getObservation());
                    troublediag.setText(bilan.getDiagnostic().getTrouble().getTroubleName());
                    projet.setText(bilan.getProjettherapeutique());
                    ArrayList<CompteRendu> cprlist = bilan.getTabCompteRendu();
                    Iterator<CompteRendu> itcpr = cprlist.iterator();
                    while (itcpr.hasNext()){
                        CompteRendu cpr = itcpr.next() ;
                        if(cpr instanceof CRQ){
                            data.add(new TestTab(cpr.getNomTest(),"Test Question",((CRQ)cpr).setscoretotal()));
                        }else if(cpr instanceof CRE){
                            TestExer te = ((TestExer)doc.getOrtho().findobjetTest(cpr.getNomTest()));
                            data.add( new TestTab(cpr.getNomTest(),"Test Exercice",((int)((CRE)cpr).setscoretotal(te))));
                        }
                    }
                    tableviewtest.setItems(data);
                    if( bilan instanceof PremierBilan){
                        anamnesetab.setVisible(true);
                        Map<Integer, String> tabreponceana = ((PremierBilan) bilan).getTabreponceana();

                        Iterator<Map.Entry<Integer, String>> iterator = tabreponceana.entrySet().iterator();
                        Anamnese ana = doc.getOrtho().getAnamnese();
                        // Parcourir la map à l'aide de l'itérateur
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, String> entry = iterator.next();
                            Integer key = entry.getKey();
                            String value = entry.getValue();
                            data1.add(new AnaTab(ana.recupererQuesenonce(key),value));

                        }

                        anamnesetab.setItems(data1);
                    }else{
                        anamnesetab.setVisible(false);
                    }

                }
                // faire le traitement
            });

            return cell ;
        });


    }

 */
    @FXML
    void onRetourClick(){
        try{
        ObjectHolder holder = ObjectHolder.getInstance();
        holder.setUser(doc);
        new SceneControler(afficherbilanscene, "Dossierpatients.fxml");

    }catch (IOException e){
        System.out.println("IOException inside");
    }
    }

}
