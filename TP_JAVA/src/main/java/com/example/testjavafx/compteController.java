package com.example.testjavafx;

import com.example.testjavafx.donneetab.AnaTab;
import com.example.testjavafx.donneetab.Person;
import com.example.testjavafx.donneetab.TestTab;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import noyau.Anamnese.*;
import noyau.Bilan.Trouble;
import noyau.Dossier.DossierPatient;
import noyau.Orthophoniste;
import noyau.SystemeCabinetOrtho;
import noyau.Test.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;


public class compteController implements Initializable {
    @FXML
    private AnchorPane sceneBienvenu;
    @FXML
    protected  Label ortho;
    @FXML
    private TableView<Person> tableDocs;
    @FXML
    private TableColumn<Person, String> column1;
    @FXML
    private TableColumn<Person,String> column2;
    @FXML
    private TableColumn<Person, String> column3;
    @FXML
    private TableView<AnaTab> anamnesetab;
    @FXML
    private TableColumn<AnaTab,String> enonce;
    @FXML
    private TableColumn<AnaTab,String> type;
    @FXML
    private ChoiceBox<String> categoryBox;
    @FXML
    private TextField questionana;
    @FXML
    private Button ajouterAna;
    @FXML
    private Button ajouterTest;
    @FXML
    private Label QuestLabel;
    @FXML
    private Label catlabel;
    @FXML
    private TreeTableView<TestTab> treeTableViewques;
    @FXML
    private TreeTableView<TestTab> treeTableViewexo;
    @FXML
    private TreeTableColumn<TestTab, String> nomColumn;
    @FXML
    private TreeTableColumn<TestTab, String> typeColumn;
    @FXML
    private TreeTableColumn<TestTab, Integer> capaciteColumn;
    @FXML
    private TreeTableColumn<TestTab, String> nomColumn2;
    @FXML
    private TreeTableColumn<TestTab, String> typeColumn2;
    @FXML
    private TreeTableColumn<TestTab, Integer> capaciteColumn2;
    @FXML
    private ChoiceBox<String> typetest;
    @FXML
    private TextField nomtest;
    @FXML
    private PieChart piechart;

    @FXML
    private AnchorPane statpane;
    @FXML
    private TableView<SimpleStringProperty> cognitiftab;
    @FXML
    private TableView<SimpleStringProperty> degultitiontab;

    @FXML
    private TableView<SimpleStringProperty> neurotab;

    @FXML
    private TableColumn<SimpleStringProperty, String> colonec;

    @FXML
    private TableColumn<SimpleStringProperty, String> coloned;

    @FXML
    private TableColumn<SimpleStringProperty, String> colonen;
    @FXML
    private Label nonremplit ;
    private static compteController cpt ;
    public static void setcpt(compteController selectedTab){
        cpt = selectedTab;
    }
    public static compteController getcpt() {
        return cpt ;
    }
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste orth = (Orthophoniste) holder.getUser();
   // TableColumn<Person, Void> buttonColumn = new TableColumn<>("Action");
   private final ObservableList<String> cat = FXCollections.observableArrayList("Adulte: L'histoire de sa maladie","Adulte: suivi medicale","Enfant: structure familiale","Enfant: dynamique familiale","Enfant: antecedents familiaux","Enfant: developpement psychmoteur","Enfant: developpement langagier","Enfant: caractere","Enfant: comporetement");
    private final ObservableList<String> typeT = FXCollections.observableArrayList("Question","Exercice");


    private final ObservableList<Person> data = FXCollections.observableArrayList(
           // new Person("001", "Doe", "John"),
           // new Person("002", "Smith", "Jane"),
           // new Person("003", "Doe", "Alice")
    );

    private final ObservableList<AnaTab> data1 = FXCollections.observableArrayList();

    String text = orth.getNom() ;
    // Create root item
    TreeItem<TestTab> rootItem = new TreeItem<>(new TestTab("Root", "Root Type", 0));
    TreeItem<TestTab> rootItem2 = new TreeItem<>(new TestTab("Root2", "Root Type2", 0));
    ObservableList<SimpleStringProperty> datac = FXCollections.observableArrayList();
    ObservableList<SimpleStringProperty> datae = FXCollections.observableArrayList();
    ObservableList<SimpleStringProperty> datad = FXCollections.observableArrayList();

    @Override
    public void initialize  (URL location, ResourceBundle resources){
        // Method called when the FXML file is loaded
        initializePage();
        column1.setCellValueFactory( new PropertyValueFactory<Person,String>("nDossier"));
        column2.setCellValueFactory( new PropertyValueFactory<Person,String>("nom"));
        column3.setCellValueFactory( new PropertyValueFactory<Person,String>("prenom"));

        enonce.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("enonce"));
        type.setCellValueFactory( new PropertyValueFactory<AnaTab,String>("type"));
        // buttonColumn.setCellFactory(param -> new ButtonCell());
        HashSet<DossierPatient> docp = orth.getListDocPatient();
        Iterator<DossierPatient> it = docp.iterator();
        while (it.hasNext()){
            DossierPatient d = it.next() ;
            data.add(new Person(String.valueOf(d.getNumDossier()),d.getPatient().getNom(),d.getPatient().getPrenom()));
        }
        Anamnese ana = orth.getAnamnese() ;
        Iterator<QuestionAnamnese> itana = ana.getQuestion().iterator() ;
        while (itana.hasNext()){
            QuestionAnamnese quesana = itana.next();
            if(quesana instanceof QuestionEnfant) {
                String s = "Enfant : ";
                data1.add(new AnaTab(quesana.getEnonce(),s.concat(((QuestionEnfant) quesana).getTypecat().getDisplayName())));
            }else if(quesana instanceof QuestionAdulte){
                String s = "Adulte : ";
                data1.add(new AnaTab(quesana.getEnonce(),s.concat(((QuestionAdulte) quesana).getTypecat().getCategorieName())));
            }
        }
        tableDocs.setItems(data);
        anamnesetab.setItems(data1);
        categoryBox.setValue("Category du question");
        categoryBox.setItems(cat);
        typetest.setValue("type Test");
        typetest.setItems(typeT);

        enonce.setCellValueFactory(cellData -> cellData.getValue().enonceProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().nomProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().typeProperty());
        nomColumn2.setCellValueFactory(cellData -> cellData.getValue().getValue().nomProperty());
        typeColumn2.setCellValueFactory(cellData -> cellData.getValue().getValue().typeProperty());
       // capaciteColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().getcapa());
        // Ajouter un gestionnaire d'événements pour les clics sur les lignes
        tableDocs.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Person rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getPrenom());
                    try {
                        compteController.setcpt(this);
                        ObjectHolder holder = ObjectHolder.getInstance();
                        System.out.println("Integer.parseInt(rowData.getNDossier())"+Integer.parseInt(rowData.getNDossier()));
                        holder.setUser(orth.getDossierDeNumPatient(Integer.parseInt(rowData.getNDossier())));
                        new SceneControler(sceneBienvenu, "Dossierpatients.fxml");

                    } catch (IOException e){
                        System.out.println("IOException");
                    }
                    // Effectuer une action ici, par exemple afficher une boîte de dialogue
                    //  showAlert(rowData);
                }
            });
            return row;
        });
        anamnesetab.setRowFactory(tv -> {
            TableRow<AnaTab> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    AnaTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getEnonce() + " " + rowData.getType());
                    try {
                        compteController.setcpt(this);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAna.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));

                        // Passer la cellule sélectionnée à la fenêtre d'édition
                        EditAna controller = loader.getController();
                        AnaTab selectedTab = anamnesetab.getSelectionModel().getSelectedItem();
                        controller.setSelectedTab(selectedTab);

                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(anamnesetab.getScene().getWindow());
                        stage.setTitle("Edit Anamnese");
                        stage.show();
                        if (controller.isDeleteAction()) {
                            System.out.println("salut");
                            data1.remove(selectedTab);
                            //anamnesetab.refresh();
                             anamnesetab.getItems().removeAll(anamnesetab.getSelectionModel().getSelectedItem());
                        } else {
                            // Optionally refresh the TableView
                            anamnesetab.refresh();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Effectuer une action ici, par exemple afficher une boîte de dialogue
                    //  showAlert(rowData);
                }
            });
            return row;
        });
        treeTableViewques.setRowFactory(tv -> {
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom()+ " " + rowData.getType());
                    try {
                        compteController.setcpt(this);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTest.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));
                        // Passer la cellule sélectionnée à la fenêtre d'édition
                        EditTest controller = loader.getController();
                        TreeItem<TestTab> selectedTab = treeTableViewques.getSelectionModel().getSelectedItem();
                        controller.setSelectedTab(selectedTab.getValue());

                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(treeTableViewques.getScene().getWindow());
                        stage.setTitle("Edit Test");
                        stage.showAndWait(); // Wait for the edit window to close

                        if (controller.isDeleteAction()) {
                            System.out.println("salut");
                            selectedTab.getParent().getChildren().remove(selectedTab); // Remove the selected item
                            // Optionally refresh the TreeTableView
                            treeTableViewques.refresh();
                        } else {
                            // Optionally refresh the TreeTableView
                            treeTableViewques.refresh();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        treeTableViewexo.setRowFactory(tv -> {
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                    try {
                        compteController.setcpt(this);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTest.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));

                        // Passer la cellule sélectionnée à la fenêtre d'édition
                        EditTest controller = loader.getController();
                        TreeItem<TestTab> selectedTab = treeTableViewexo.getSelectionModel().getSelectedItem();
                        controller.setSelectedTab(selectedTab.getValue());

                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(treeTableViewexo.getScene().getWindow());
                        stage.setTitle("Edit Test");
                        stage.showAndWait(); // Wait for the edit window to close

                        if (controller.isDeleteAction()) {
                            System.out.println("salut");
                            selectedTab.getParent().getChildren().remove(selectedTab); // Remove the selected item
                            // Optionally refresh the TreeTableView
                            treeTableViewexo.refresh();
                        } else {
                            // Optionally refresh the TreeTableView
                            treeTableViewexo.refresh();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        nomColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        typeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        capaciteColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacite"));
        nomColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        typeColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
        capaciteColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacite"));
        rootItem.setExpanded(false);
        rootItem2.setExpanded(false);


        // Add child items
        TreeItem<TestTab> child1 = new TreeItem<>(new TestTab("Child 1", "Type 1", 10));
        TreeItem<TestTab> child2 = new TreeItem<>(new TestTab("Child 2", "Type 2", 20));
        TreeItem<TestTab> subChild1 = new TreeItem<>(new TestTab("SubChild 1", "Type 1.1", 5));
        TreeItem<TestTab> subChild2 = new TreeItem<>(new TestTab("SubChild 2", "Type 1.2", 15));

        child1.getChildren().add(subChild1);
        child1.getChildren().add(subChild2);
       // rootItem.getChildren().addAll(child1, child2);
      //  rootItem2.getChildren().addAll(child1, child2);

        // Set root item in TreeTableVie
        HashSet<Test > listTest  = orth.getListTest() ;
        Iterator<Test> ittest = listTest.iterator() ;
        while (ittest.hasNext()){
            Test t = ittest.next();
            if(t instanceof TestQuest ){
                TreeItem<TestTab> childq = new TreeItem<>(new TestTab(t.getNom(), "Question", t.Getcapacite()));
                rootItem.getChildren().addAll(childq);
                ArrayList<Question> listq = ((TestQuest) t).GetTabQues();
                Iterator<Question> itques = listq.iterator() ;
                while (itques.hasNext()){
                    Question q = itques.next();
                    if(q instanceof QCMU && ((QCMU) q).GetType()) {
                        System.out.println("((QCMU) q).GetType()"+((QCMU) q).GetType());
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "QCM", ((QCMU) q).getsizetab()));
                        childq.getChildren().add(subChild);
                    }else if(q instanceof QCMU && !((QCMU) q).GetType()) {
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "QCMU", ((QCMU) q).getsizetab()));
                        childq.getChildren().add(subChild);
                    }else if(q instanceof Question){
                        TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.getQuest(), "Question à reponce libre", 0));
                        childq.getChildren().add(subChild);
                    }

                }

            }else if(t instanceof TestExer){
                TreeItem<TestTab> childe = new TreeItem<>(new TestTab(t.getNom(), "Exercice", t.Getcapacite()));
                rootItem2.getChildren().addAll(childe);
                        ArrayList<Exercice> listq = ((TestExer) t).getTabQuestExercice();
                        Iterator<Exercice> itques = listq.iterator() ;
                        while (itques.hasNext()){
                            Exercice q = itques.next();
                            if(q instanceof ExerciceM) {
                                TreeItem<TestTab> subChild = new TreeItem<>(new TestTab(q.GetExercice(), "Exercice materiels", 1));
                                childe.getChildren().add(subChild);
                            }else if(q instanceof Exercice) {
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

        // the piechart
        float[] tab = orth.pourcentagePatientParTrouble();
        System.out.println("tab");
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("degultition", new BigDecimal(tab[0]).setScale(2, RoundingMode.HALF_UP).floatValue()),
                new PieChart.Data("cognitif", new BigDecimal(tab[1]).setScale(2, RoundingMode.HALF_UP).floatValue()),
                new PieChart.Data("neuro", new BigDecimal(tab[2]).setScale(2, RoundingMode.HALF_UP).floatValue())
        );
        pieChartData.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(),data.pieValueProperty()))));
        piechart.getData().addAll(pieChartData);
        // the stat table
        colonec.setCellValueFactory(data -> data.getValue());
        colonen.setCellValueFactory(data -> data.getValue());
        coloned.setCellValueFactory(data -> data.getValue());
        ArrayList<Integer> tabd =  orth.patientSoufreTrouble(Trouble.degultition);
        Iterator<Integer> itd = tabd.iterator();
        while (itd.hasNext()){
            datad.add(new SimpleStringProperty(String.valueOf(itd.next())));
        }
        ArrayList<Integer> tabc =  orth.patientSoufreTrouble(Trouble.cognitif);
        Iterator<Integer> itc = tabc.iterator();
        while (itc.hasNext()){
            datac.add(new SimpleStringProperty(String.valueOf(itc.next())));
        }
        ArrayList<Integer> tabn =  orth.patientSoufreTrouble(Trouble.neuro);
        Iterator<Integer> itn = tabn.iterator();
        while (itn.hasNext()){
            datae.add(new SimpleStringProperty(String.valueOf(itn.next())));
        }
        degultitiontab.setItems(datad);
        cognitiftab.setItems(datac);
        neurotab.setItems(datae);



    }
    private void setupTreeTableView(TreeTableView<TestTab> treeTableView) {
        treeTableView.setRowFactory(tv -> {
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                }
            });
            return row;
        });
    }
    private void initializePage() {
        // Your custom method to be executed when the FXML page is loaded
        System.out.println("New FXML page loaded!");
        ortho.setText(text);
    }
    @FXML  protected void onPatientsClick(){
            tableDocs.setVisible(true);statpane.setVisible(false);
            anamnesetab.setVisible(false);categoryBox.setVisible(false);
        ajouterAna.setVisible(false); questionana.setVisible(false);
        QuestLabel.setVisible(false);catlabel.setVisible(false);   nonremplit.setText("");
        treeTableViewques.setVisible(false);treeTableViewexo.setVisible(false);
        nomtest.setVisible(false);typetest.setVisible(false);ajouterTest.setVisible(false);
    }
    @FXML protected void onStaticsClick(){
        tableDocs.setVisible(false);statpane.setVisible(true);   nonremplit.setText("");
        anamnesetab.setVisible(false);categoryBox.setVisible(false);
        ajouterAna.setVisible(false); questionana.setVisible(false);
        QuestLabel.setVisible(false);catlabel.setVisible(false);
        treeTableViewques.setVisible(false);treeTableViewexo.setVisible(false);
        nomtest.setVisible(false);typetest.setVisible(false);ajouterTest.setVisible(false);
    }
    @FXML protected void onAnamneseClick(){
        tableDocs.setVisible(false);   nonremplit.setText("");
        anamnesetab.setVisible(true);categoryBox.setVisible(true);
        ajouterAna.setVisible(true); questionana.setVisible(true);
        QuestLabel.setVisible(true);catlabel.setVisible(true);statpane.setVisible(false);
        treeTableViewques.setVisible(false);treeTableViewexo.setVisible(false);
        nomtest.setVisible(false);typetest.setVisible(false);ajouterTest.setVisible(false);

    }
    @FXML protected void onTestClick(){
        tableDocs.setVisible(false);   nonremplit.setText("");
        anamnesetab.setVisible(false);categoryBox.setVisible(false);
        ajouterAna.setVisible(false); questionana.setVisible(false);
        QuestLabel.setVisible(false);catlabel.setVisible(false);statpane.setVisible(false);
        treeTableViewques.setVisible(true);treeTableViewexo.setVisible(true);
        nomtest.setVisible(true);typetest.setVisible(true);ajouterTest.setVisible(true);

    }
     protected void delleteana(){

        anamnesetab.getItems().removeAll(anamnesetab.getSelectionModel().getSelectedItem());

    }
    protected void delletedoc(){

        tableDocs.getItems().removeAll(tableDocs.getSelectionModel().getSelectedItem());

    }
    protected void delleteQues(){
        //treeTableViewques.getTreeItem().removeAll(anamnesetab.getSelectionModel().getSelectedItem());
        treeTableViewques.setRowFactory(tv -> {
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                    try {
                        // Load the EditAna FXML and controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTest.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));

                        // Get the controller and pass the selected item
                        EditTest controller = loader.getController();
                        TreeItem<TestTab> selectedTab = treeTableViewques.getSelectionModel().getSelectedItem();
                        controller.setSelectedTab(selectedTab.getValue());

                        // Show the edit window and wait for it to close
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(treeTableViewques.getScene().getWindow());
                        stage.setTitle("Edit Table");
                        stage.showAndWait(); // Wait for the edit window to close

                        // Check if the delete action was triggered and remove the item
                        if (controller.isDeleteAction()) {
                            System.out.println("Deleting: " + selectedTab.getValue().getNom());
                            TreeItem<TestTab> parent = selectedTab.getParent();
                            if (parent != null) {
                                parent.getChildren().remove(selectedTab);
                            } else {
                                treeTableViewques.setRoot(null); // If there is no parent, the selected item is the root
                            }
                            treeTableViewques.refresh();
                        } else {
                            // Optionally refresh the TreeTableView
                            treeTableViewques.refresh();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }
    protected void delleteexo(){

      //  treeTableViewexo.getTreeItem(0).removeAll(treeTableViewexo.getSelectionModel().getSelectedItem());
        treeTableViewexo.setRowFactory(tv -> {
            TreeTableRow<TestTab> row = new TreeTableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    TestTab rowData = row.getItem();
                    System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());
                    try {
                        // Load the EditAna FXML and controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTest.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(loader.load()));

                        // Get the controller and pass the selected item
                        EditTest controller = loader.getController();
                        TreeItem<TestTab> selectedTab = treeTableViewexo.getSelectionModel().getSelectedItem();
                        controller.setSelectedTab(selectedTab.getValue());

                        // Show the edit window and wait for it to close
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(treeTableViewexo.getScene().getWindow());
                        stage.setTitle("Edit Table");
                        stage.showAndWait(); // Wait for the edit window to close

                        // Check if the delete action was triggered and remove the item
                        if (controller.isDeleteAction()) {
                            System.out.println("Deleting: " + selectedTab.getValue().getNom());
                            TreeItem<TestTab> parent = selectedTab.getParent();
                            if (parent != null) {
                                parent.getChildren().remove(selectedTab);
                            } else {
                                treeTableViewexo.setRoot(null); // If there is no parent, the selected item is the root
                            }
                            treeTableViewexo.refresh();
                        } else {
                            // Optionally refresh the TreeTableView
                            treeTableViewexo.refresh();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }
    @FXML protected void onAjouteranaClick(){

       String text =  questionana.getText();
       String originalString = categoryBox.getValue().toString().trim();
       // String originalString = "Adulte: L'histoire de sa maladie";

        // Find the index of the character ':'
        if( !questionana.getText().isEmpty() && (!categoryBox.getValue().equals("Category du question"))) {
            nonremplit.setText("");
            int index = originalString.indexOf(':');

            if (index != -1) {
                // Get the substring before the character ':'
                String beforeColon = originalString.substring(0, index).trim();

                // Get the substring after the character ':'
                String afterColon = originalString.substring(index + 1).trim();

                System.out.println("Before ':': " + beforeColon);
                System.out.println("After ':': " + afterColon);
                Anamnese ana = orth.getAnamnese();
                if (beforeColon.equals("Enfant")) {
                    CatQuestionEnfant enf = CatQuestionEnfant.fromDescription(afterColon);
                    System.out.println("enf" + enf);
                    ana.AjouterQuestion(text, enf);
                } else if (beforeColon.equals("Adulte")) {
                    CatQuestionAdulte enf = CatQuestionAdulte.fromDescription(afterColon);
                    ana.AjouterQuestion(text, enf);
                }
                AnaTab e = new AnaTab(text, originalString);
                anamnesetab.getItems().add(e);
            } else {
                System.out.println("The character ':' was not found in the string.");
            }
            questionana.clear();
            categoryBox.setValue("Category du question");
        }else{
            nonremplit.setText("Veuillez continuez à remplir Tous les Information  ");

        }
    }
    @FXML protected void Ajoutertest(){
         String nom = nomtest.getText() ;
         Integer c = 0 ;
         String type = typetest.getValue().toString().trim();
        //ArrayList<Test > listTest = orth.getListTest();
        if(!nomtest.getText().isEmpty() && (!typetest.getValue().equals("type Test"))) {
            nonremplit.setText("");
            if (type.equals("Question")) {
                TestQuest e = new TestQuest(nom, c);
                orth.ajouterTest(e);
                TreeItem<TestTab> child1 = new TreeItem<>(new TestTab(nom, type, c));
                rootItem.getChildren().add(child1);
            } else if (type.equals("Exercice")) {
                TestExer e = new TestExer(nom, c);
                orth.ajouterTest(e);
                TreeItem<TestTab> child1 = new TreeItem<>(new TestTab(nom, type, c));
                rootItem2.getChildren().add(child1);
            }
            System.out.println("end add test");
            orth.afficherTest();
            nomtest.clear();
            typetest.setValue("Type Test");
        }else{
            nonremplit.setText("Veuillez continuez à remplir Tous les Information  ");
        }
    }
    protected void ajouterSousTest(TestTab s, String type) {
        System.out.println("type ajouter sous test " + type);
        if (type.equals("Question")) {
            TreeItem<TestTab> selectedTreeItem = treeTableViewques.getSelectionModel().getSelectedItem();
            if (selectedTreeItem != null) {
                TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                selectedTreeItem.getChildren().add(subChild1);
                System.out.println("Added SubChild to: " + selectedTreeItem.getValue().getNom());
            }
        } else if (type.equals("Exercice")) {
            TreeItem<TestTab> selectedTreeItem = treeTableViewexo.getSelectionModel().getSelectedItem();
            if (selectedTreeItem != null) {
                TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                selectedTreeItem.getChildren().add(subChild1);
                System.out.println("Added SubChild to: " + selectedTreeItem.getValue().getNom());
            }
        }
    }
    protected TreeItem<TestTab> getTreeItemques(){
        TreeItem<TestTab> selectedTreeItem = treeTableViewques.getSelectionModel().getSelectedItem();
           return selectedTreeItem.getParent();
    }
    protected TreeItem<TestTab> getTreeItemexo(){
        TreeItem<TestTab> selectedTreeItem = treeTableViewexo.getSelectionModel().getSelectedItem();
        return selectedTreeItem.getParent();
    }

    @FXML
    void onParamettreClick() {
           // sortir
        ObjectOutputStream out ;
        try{
            String nomfile = orth.getNom().concat(orth.getMotPasse());
            System.out.println("nomfile"+nomfile);
            out = new ObjectOutputStream(new BufferedOutputStream( new FileOutputStream(new File(nomfile))));
            out.writeObject(orth);
            System.out.println("i am in save ");
            out.close();
            ((Stage) sceneBienvenu.getScene().getWindow()).close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void onAgendaClick() {
        try {
            ObjectHolder holder = ObjectHolder.getInstance();
            holder.setUser(orth);
           // new SceneControler(sceneBienvenu, "RemplirBilan.fxml");
            new SceneControler(sceneBienvenu, "agenda.fxml");

        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }
    /*protected void ajouterSousTest(TestTab s , String type) {
        System.out.println("type ajouter sous test "+type);
       if(type.equals("Question")){
           treeTableViewques.setRowFactory(tv -> {
               TreeTableRow<TestTab> row = new TreeTableRow<>();
               row.setOnMouseClicked(event -> {
                   if (!row.isEmpty()) {
                       TestTab rowData = row.getItem();
                       System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());

                           TreeItem<TestTab> selectedTreeItem = treeTableViewques.getSelectionModel().getSelectedItem();
                           TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                           selectedTreeItem.getChildren().add(subChild1);



                   }
               });
               return row;
           });

           // TreeItem<TestTab> subChild1 = new TreeItem<>(new TestTab("SubChild 1", "Type 1.1", 5));
          // treeTableViewques.getSelectionModel().selectedItemProperty().a
           treeTableViewques.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue != null) {
                   System.out.println("newValue "+newValue);
                   // Récupérer l'élément sélectionné
                   TreeItem<TestTab> selectedTreeItem = newValue ;

                   // Ajouter un sous-enfant à l'élément sélectionné
                   TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                   selectedTreeItem.getChildren().add(subChild1);

                   System.out.println("Added SubChild to: " + selectedTreeItem.getValue().getNom());
               }
           });
       }else if(type.equals("Exercice")){
           treeTableViewexo.setRowFactory(tv -> {
               TreeTableRow<TestTab> row = new TreeTableRow<>();
               row.setOnMouseClicked(event -> {
                   if (!row.isEmpty()) {
                       TestTab rowData = row.getItem();
                       System.out.println("Clicked on: " + rowData.getNom() + " " + rowData.getType());

                       TreeItem<TestTab> selectedTreeItem = treeTableViewexo.getSelectionModel().getSelectedItem();
                       TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                       selectedTreeItem.getChildren().add(subChild1);



                   }
               });
               return row;
           });
           treeTableViewexo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue != null) {
                   // Récupérer l'élément sélectionné
                   TreeItem<TestTab> selectedTreeItem = newValue ;

                   // Ajouter un sous-enfant à l'élément sélectionné
                   TreeItem<TestTab> subChild1 = new TreeItem<>(s);
                   selectedTreeItem.getChildren().add(subChild1);

                   System.out.println("Added SubChild to: " + selectedTreeItem.getValue().getNom());
               }
           });
       }
    }*/
    /*private class ButtonCell extends TableCell<Person, Void> {
        private final Button actionButton = new Button("Action");

        ButtonCell() {
            actionButton.setOnAction(event -> {
                Person person = getTableView().getItems().get(getIndex());
                System.out.println("Action clicked for: " + person.getNom());
                // Add your action logic here
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(actionButton);
            }
        }
    }*/

}

