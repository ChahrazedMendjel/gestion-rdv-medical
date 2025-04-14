package com.example.testjavafx;

import com.example.testjavafx.donneetab.AnaTab;
import com.example.testjavafx.donneetab.TestTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import noyau.Anamnese.Anamnese;
import noyau.Anamnese.QuestionAdulte;
import noyau.Anamnese.QuestionAnamnese;
import noyau.Anamnese.QuestionEnfant;
import noyau.Bilan.Bilan;
import noyau.Bilan.Diagnostic;
import noyau.Bilan.PremierBilan;
import noyau.Bilan.Trouble;
import noyau.CompteRenduReponce.CRE;
import noyau.CompteRenduReponce.CRQ;
import noyau.CompteRenduReponce.CompteRendu;
import noyau.Dossier.*;
import noyau.Orthophoniste;
import noyau.Test.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RemplirBilan implements Initializable {

    @FXML
    private TableView<AnaTab> anamnesetab;

    @FXML
    private TableColumn<AnaTab, String> enonce;

    @FXML
    private Label ortho;

    @FXML
    private TableColumn<AnaTab, String> type;

    @FXML
    private AnchorPane anaScene;
    @FXML
    private TextField newrep ;

    @FXML
    private TreeTableColumn<TestTab,String> capaciteColumn;

    @FXML
    private TreeTableColumn<TestTab,String> capaciteColumn2;
    @FXML
    private TreeTableColumn<TestTab, String> nomColumn;

    @FXML
    private TreeTableColumn<TestTab, String> nomColumn2;

    @FXML
    private AnchorPane testScene;

    @FXML
    private TreeTableView<TestTab> treeTableViewexo;

    @FXML
    private TreeTableView<TestTab> treeTableViewques;

    @FXML
    private TreeTableColumn<TestTab, String> typeColumn;

    @FXML
    private TreeTableColumn<TestTab, String> typeColumn2;
    @FXML
    private TextField repquestlibre;

    @FXML
    private TextField scoreexo;
    @FXML
    private Label enonceoutside;

    @FXML
    private Label labelexo;

    @FXML
    private Label labelqueslibre;

    @FXML
    private AnchorPane traitementScene;

    @FXML
    private CheckBox oui;

    @FXML
    private TextArea prjthera;
    @FXML
    private TextField nomtrouble;

    @FXML
    private CheckBox non;

    @FXML
    private TextArea observation;
    @FXML
    private ChoiceBox<String> troubleBox;

    @FXML
    private Label nonremplit;
    @FXML
    private AnchorPane remplirBillanScene;

    @FXML
    private TextField noteobj;

    @FXML
    private AnchorPane objectifspane;

    @FXML
    private ListView<String> objlistview;

    @FXML
    private AnchorPane decisionpane;
    @FXML
    private Button anabutton;
    private static Object rb ;
    public static void setrb(Object selectedTab){
        rb = selectedTab;
    }
    public static Object getrb() {
        return rb ;
    }
    private static RemplirBilan remlirb ;
    public static void setRemlirb(RemplirBilan selectedTab){
        remlirb= selectedTab;
    }
    public static RemplirBilan getRemlirb() {
        return remlirb ;
    }
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste orth = (Orthophoniste) holder.getUser();
    Bilan bilan  = new Bilan();
    private AnaTab selectedRow;
    private final ObservableList<String> typetrouble = FXCollections.observableArrayList("degultition","neuro","cognitif");

    private final ObservableList<AnaTab> data1 = FXCollections.observableArrayList(
           // new AnaTab("question ana 1", "Adulte: L'histoire de sa maladie"),
            //new AnaTab("question ana 1", "Enfant: structure familiale")
    );


    // je doit recuperer le vrai patient
    Bilan1 bil = Bilan1.getBil();
    Patient patient = bil.getP(); ;//new Enfant("hi","hello","","","","", "");
    agendacontrol agctr = agendacontrol.getAgenda();
    DossierPatient doc = agctr.getDoc();
 ;

        // je doit recuperer celui la dans le cas suivi/atelier


    Map<String,Integer> repchoix = new HashMap<String,Integer>();
    TreeItem<TestTab> rootItem = new TreeItem<>(new TestTab("Root", "Root Type", 0));
    TreeItem<TestTab> rootItem2 = new TreeItem<>(new TestTab("Root2", "Root Type2", 0));
    String clickedtype = "";
    @Override
    public void initialize  (URL location, ResourceBundle resources) {

        enonce.setCellValueFactory(new PropertyValueFactory<AnaTab, String>("enonce"));
        type.setCellValueFactory(new PropertyValueFactory<AnaTab, String>("type"));
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().nomProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().typeProperty());
        nomColumn2.setCellValueFactory(cellData -> cellData.getValue().getValue().nomProperty());
        typeColumn2.setCellValueFactory(cellData -> cellData.getValue().getValue().typeProperty());
        nomColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        typeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        capaciteColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacite"));
        nomColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        typeColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        capaciteColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacite"));
        rootItem.setExpanded(false);
        rootItem2.setExpanded(false);
        troubleBox.setItems(typetrouble);
        troubleBox.setValue("type du trouble");
        Anamnese ana = orth.getAnamnese();

        Iterator<QuestionAnamnese> itana = ana.getQuestion().iterator();
        while (itana.hasNext()) {
            QuestionAnamnese quesana = itana.next();
            if (quesana instanceof QuestionEnfant) {
                String s = "Enfant : ";
                data1.add(new AnaTab(quesana.getEnonce(), s.concat(((QuestionEnfant) quesana).getTypecat().getDisplayName())));
            } else if (quesana instanceof QuestionAdulte) {
                String s = "Adulte : ";
                data1.add(new AnaTab(quesana.getEnonce(), s.concat(((QuestionAdulte) quesana).getTypecat().getCategorieName())));
            }
        }

        HashSet<Test> listTest = orth.getListTest();
        Iterator<Test> ittest = listTest.iterator();
        while (ittest.hasNext()) {
            Test t = ittest.next();
            if (t instanceof TestQuest) {
                TreeItem<TestTab> childq = new TreeItem<>(new TestTab(t.getNom(), "Question", ((TestQuest) t).getNbquest()));
                rootItem.getChildren().addAll(childq);
                ArrayList<Question> listq = ((TestQuest) t).GetTabQues();
                Iterator<Question> itques = listq.iterator();
                while (itques.hasNext()) {
                    Question q = itques.next();
                    if (q instanceof QCMU && ((QCMU) q).GetType()) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "QCM", ((QCMU) q).getsizetab()));
                        childq.getChildren().add(subChild);
                    } else if (q instanceof QCMU && !((QCMU) q).GetType()) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "QCMU", ((QCMU) q).getsizetab()));
                        childq.getChildren().add(subChild);
                    } else if (q instanceof Question) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "Question à reponce libre", 0));
                        childq.getChildren().add(subChild);
                    }

                }

            } else if (t instanceof TestExer) {
                TreeItem<TestTab> childe = new TreeItem<>(new TestTab(t.getNom(), "Exercice", ((TestExer) t).getNbexer()));
                rootItem2.getChildren().addAll(childe);
                ArrayList<Exercice> listq = ((TestExer) t).getTabQuestExercice();
                Iterator<Exercice> itques = listq.iterator();
                while (itques.hasNext()) {
                    Exercice q = itques.next();
                    if (q instanceof ExerciceM) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.GetExercice(), "Exercice materiels", 1));
                        childe.getChildren().add(subChild);
                    } else if (q instanceof Exercice) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.GetExercice(), "Exercice normale", 0));
                        childe.getChildren().add(subChild);
                    }

                }
            }
        }
        treeTableViewques.setRoot(rootItem);
        treeTableViewques.setShowRoot(false);
        treeTableViewexo.setRoot(rootItem2);
        treeTableViewexo.setShowRoot(false);

        anamnesetab.setItems(data1);
        if (anaScene.isVisible()) {  // le patient doi etre récuperer de la  la page rendez vous (avec la meme methode utiliser pour le comptecontroller et TestEdit
            bilan = new PremierBilan(patient);  // ici qu'on doit avoir l'instance billan
        }
        anamnesetab.setRowFactory(tv -> {
            TableRow<AnaTab> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    AnaTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getEnonce() + " " + rowData.getType());
                    selectedRow = rowData;
                    // Effectuer une action ici, par exemple afficher une boîte de dialogue
                    //  showAlert(rowData);
                }
            });
            return row;
        });
        treeTableViewques.setRowFactory(tv -> { // le traitements des reponce doit etre à l'exterieur de initialize
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    clickedtype = rowData.getType();

                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                    if (clickedtype.equals("QCM") || clickedtype.equals("QCMU")) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponceTest.fxml"));
                            Stage stage = new Stage();

                            stage.setScene(new Scene(loader.load()));
                            // Passer la cellule sélectionnée à la fenêtre d'édition
                            ReponceTest controller = loader.getController();
                            TreeItem<TestTab> selectedTab = treeTableViewques.getSelectionModel().getSelectedItem();
                            controller.setSelectedTab(selectedTab.getValue());// le paire de l'element cliquer
                            RemplirBilan.setrb(bilan);
                            RemplirBilan.setRemlirb(this);

                            // RemplirBilan.setrb(selectedTab.getValue());
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(treeTableViewques.getScene().getWindow());
                            stage.setTitle("Reponce Test");
                            stage.showAndWait(); // Wait for the edit window to close


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {

                        if (clickedtype.equals("Question à reponce libre")) {
                            enonceoutside.setText(rowData.getNom());
                            clickedtype = rowData.getType();
                           /* labelqueslibre.setTextFill(Color.RED);
                            labelexo.setTextFill(Color.RED);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(3000); // Attendre pendant 3 secondes
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                            labelqueslibre.setTextFill(Color.BLACK);
                            labelexo.setTextFill(Color.BLACK);*/

                        }
                    }

                }
            });

            return row;
        });
        treeTableViewexo.setRowFactory(tv2 -> {
            TreeTableRow<TestTab> row2 = new TreeTableRow<>();
            row2.setOnMouseClicked(event -> {
                if (!row2.isEmpty()) {
                    TestTab rowData = row2.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                    enonceoutside.setText(rowData.getNom());
                    clickedtype = rowData.getType();
                }

            });
            return row2;
        });
        if (patient == null) {
            anabutton.setVisible(false);
            anaScene.setVisible(false);
            testScene.setVisible(true);
        } else {
            anabutton.setVisible(true);
            anaScene.setVisible(true);
            testScene.setVisible(false);
        }

        ArrayList<String> choix = new ArrayList<String>();
        if (doc != null) {
            FicheSuivi fich = doc.getFicheSuivi().getLast();
            ;


        Iterator<Objectifs> listobj = fich.getListobjs().iterator();
        while (listobj.hasNext()) {
            Objectifs obj = listobj.next();
            if (obj.isSelected()) {
                choix.add(obj.getEnonceObj());
            }
        }
        objlistview.getItems().addAll(choix);


        objlistview.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    String item = cell.getItem();
                    System.out.println("Clicked on: " + item);
                    if (!noteobj.getText().isEmpty()) {
                        repchoix.put(item, Integer.parseInt(noteobj.getText()));
                    }
                    //choixrep.add(item);

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

            return cell;
        });
    }
    }
    @FXML
    void onAnamneseClick() {
        anaScene.setVisible(true);
        testScene.setVisible(false);
        traitementScene.setVisible(false);
    }

    @FXML
    void onValideranaClick() {
        Anamnese ana =  orth.getAnamnese();
        int id = ana.recupererQuesIndice(selectedRow.getEnonce());
        ((PremierBilan)bilan).ajouterReponceAna(id,newrep.getText());
        ((PremierBilan)bilan).afficherReponceAna();
        newrep.clear();
    }
    @FXML
    void onTestClick() {
        anaScene.setVisible(false);
        testScene.setVisible(true);
        traitementScene.setVisible(false);
    }

    @FXML
    void onValiderTestClick() {
        if (clickedtype.equals("Question à reponce libre")) {
            TreeItem<TestTab> selectedTreeItem = treeTableViewques.getSelectionModel().getSelectedItem().getParent();

            CompteRendu cr1 = bilan.isTestcrdexiste(selectedTreeItem.getValue().getNom());

            if (cr1 != null) {
                System.out.println("cr nom =  "+cr1.getNomTest());

                CRQ cr = (CRQ)cr1 ;
               // cr.setNomTest(selectedTreeItem.getValue().getNom());
                cr.remplirreponce(((TestQuest) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverquest(enonceoutside.getText()), repquestlibre.getText());
                cr.remplirscore(((TestQuest) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverquest(enonceoutside.getText()),Integer.parseInt(scoreexo.getText()));
                bilan.ajoutercr(cr);
                cr.afficherCRQ();
            } else {
                CRQ cr = new CRQ();
                cr.setNomTest(selectedTreeItem.getValue().getNom());
                cr.remplirreponce(((TestQuest) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverquest(enonceoutside.getText()), repquestlibre.getText());
                cr.remplirscore(((TestQuest) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverquest(enonceoutside.getText()),Integer.parseInt(scoreexo.getText()));
                bilan.ajoutercr(cr);
                cr.afficherCRQ();

            }

        } if(clickedtype.equals("Exercice materiels") || clickedtype.equals("Exercice normale")){
            TreeItem<TestTab> selectedTreeItem = treeTableViewexo.getSelectionModel().getSelectedItem().getParent();
            CompteRendu cr1 = bilan.isTestcrdexiste(selectedTreeItem.getValue().getNom());
            if (cr1 != null) {
                System.out.println("cr nom =  "+cr1.getNomTest());

                CRE cr = (CRE)cr1 ;
               // cr.setNomTest(selectedTreeItem.getValue().getNom());
                cr.setscore(((TestExer) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverEnonce(enonceoutside.getText()), Integer.parseInt(scoreexo.getText()));
                bilan.ajoutercr(cr);
                cr.afficherCRE();

            } else {
                CRE cr =  new CRE() ;
                cr.setNomTest(selectedTreeItem.getValue().getNom());
                cr.setscore(((TestExer) orth.findobjetTest(selectedTreeItem.getValue().getNom())).trouverEnonce(enonceoutside.getText()), Integer.parseInt(scoreexo.getText()));
                bilan.ajoutercr(cr);
                cr.afficherCRE();
            }

        }
        repquestlibre.clear();scoreexo.clear();

        }
    protected TreeItem<TestTab> getTreeItemques(){
        TreeItem<TestTab> selectedTreeItem = treeTableViewques.getSelectionModel().getSelectedItem();
        return selectedTreeItem.getParent();
    }
    protected TreeItem<TestTab> getTreeItemexo(){
        TreeItem<TestTab> selectedTreeItem = treeTableViewexo.getSelectionModel().getSelectedItem();
        return selectedTreeItem.getParent();
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
    void onValiderTraitClick() {
        try {// je doit gerer le cas d'accepter ou réfuser le patient
            nonremplit.setText("");
            boolean anamnese = decisionpane.isVisible();
            if (!anamnese) {// à changer
                if (!troubleBox.getValue().equals("type du trouble") && !observation.getText().equals("") && !prjthera.getText().equals("") && !nomtrouble.getText().equals("")) {
                    if (isInteger(noteobj.getText()) || !noteobj.getText().isEmpty()) {
                        Trouble tr = Trouble.fromDescription(troubleBox.getValue());
                        Diagnostic d = new Diagnostic(tr);
                        d.setNom(nomtrouble.getText());
                        FicheSuivi fich  =  doc.getFicheSuivi().getLast();;
                        int numDossier = doc.getNumDossier();
                        //DossierPatient doc = orth.getDossierDeNumPatient(numDossier);
                        bilan.setDiagnostic(d);
                        bilan.setObservation(observation.getText());
                        bilan.setProjettherapeutique(prjthera.getText());
                        //objlistview.getSelectionModel().getSelectedItem();
                        // je suis besoins de l'instance dossierPatient
                        // ajouter les notes des objectifs
                        Iterator<Map.Entry<String, Integer>> iterator = repchoix.entrySet().iterator();

                        // Parcourir la map avec l'iterator
                        while (iterator.hasNext()) {
                            Map.Entry<String, Integer> entry = iterator.next();
                            String key = entry.getKey();
                            Integer value = entry.getValue();
                            Objectifs obj = fich.getObjnom(key);
                            obj.AjouterScore(value);
                            obj.setSelected(false);
                            System.out.println("Clé: " + key + ", Valeur: " + value);
                        }
                        doc.ajouterListBilan(bilan);
                        bilan.afficherCompteRendu();
                        nonremplit.setText("Enregistrer avec succés");
                    } else {
                        nonremplit.setText("Veuillez continuer à remplir Toutes les Informations ou vérifier que la note est un entier   ");
                    }
                } else {
                    nonremplit.setText("Veuillez continuer à remplir Toutes les Informations");
                }
            } else {
                if (!troubleBox.getValue().equals("type du trouble") && !observation.getText().equals("") && !prjthera.getText().equals("") && !nomtrouble.getText().equals("")) {
                    if (oui.isSelected() && !non.isSelected()) { // creer dossier consultation
                        System.out.println("valider ! ");
                        Trouble tr = Trouble.fromDescription(troubleBox.getValue());
                        Diagnostic d = new Diagnostic(tr);
                        d.setNom(nomtrouble.getText());
                        bilan.setDiagnostic(d);
                        bilan.setObservation(observation.getText());
                        bilan.setProjettherapeutique(prjthera.getText());
                        DossierPatient doc = new DossierPatient(patient, orth);
                        orth.ajouterDocPatient(doc);
                        doc.ajouterListBilan(bilan);
                       // System.out.println("bilannnnn "+bilan.getTabCompteRendu().get(0).getNomTest());
                        bilan.afficherCompteRendu();

                        nonremplit.setText("Enregistrer avec succés");
                    } else if (oui.isSelected() && non.isSelected()) {
                        nonremplit.setText("Veuillez choisir une seule decision ");
                    } else if (!oui.isSelected() && !non.isSelected()) {
                        nonremplit.setText("Veuillez continuer à remplir Toutes les Informations  ");
                    }
             /*   else if (!anaScene.isVisible()) { // suivi ou atelier
                   DossierPatient doc = orth.getDossierDeNumPatient(numDossier);
                   doc.ajouterListBilan(bilan);
                   nonremplit.setText("Enregistrer avec succee");

               } else if (oui.isSelected() && non.isSelected() && anaScene.isVisible()) {
                   nonremplit.setText("Veuillez choisir une seule decision ");
               } else if (!oui.isSelected() && !non.isSelected() && anaScene.isVisible()) {
                   nonremplit.setText("Veuillez continuez à remplir Tous les Information  ");
               }
           } else {
               nonremplit.setText("Veuillez continuez à remplir Tous les Information ");
           }*/
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void onTraitementClick() {
        anaScene.setVisible(false);
        testScene.setVisible(false);
        traitementScene.setVisible(true);
        if(patient == null){
            objectifspane.setVisible(true);
            decisionpane.setVisible(false);
        }else{
            decisionpane.setVisible(true);
            objectifspane.setVisible(false);
        }

    }
    @FXML
    void onRetourClick() {
        try {
            new SceneControler(remplirBillanScene, "compteOrtho.fxml");

        }catch (Exception e){
            System.out.println("exeption");
        }
    }
    }



