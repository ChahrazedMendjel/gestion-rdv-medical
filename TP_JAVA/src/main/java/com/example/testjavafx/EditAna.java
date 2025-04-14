package com.example.testjavafx;

import com.example.testjavafx.donneetab.AnaTab;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Anamnese.Anamnese;
import noyau.Anamnese.CatQuestionAdulte;
import noyau.Anamnese.CatQuestionEnfant;
import noyau.Orthophoniste;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditAna implements Initializable {
    @FXML
    private ChoiceBox<String> categorymodifyBox;

    @FXML
    private TextField newques;
    @FXML
    private AnchorPane editscene;
    @FXML
    private Label enoncelabel;
    @FXML
    private Label typelabel;
    private static AnaTab selectedTab;
    private boolean isDeleteAction = false;
    public boolean isDeleteAction() {
        return isDeleteAction;
    }

    public  void setSelectedTab(AnaTab selectedTab){
        this.selectedTab = selectedTab;
        if (selectedTab != null) {
            // Afficher les données de la cellule sélectionnée dans les champs de texte
            enoncelabel.setText(selectedTab.getEnonce());
             typelabel.setText(selectedTab.getType());
           // categorymodifyBox.setValue(selectedTab.getType());
        }
    }
    public  AnaTab getSelectedTab() {
       return this.selectedTab ;
    }
    ObjectHolder holder = ObjectHolder.getInstance();
    Orthophoniste orth = (Orthophoniste) holder.getUser();

    private final ObservableList<String> cat1 = FXCollections.observableArrayList("Enfant: structure familiale","Enfant: dynamique familiale","Enfant: antecedents familiaux","Enfant: developpement psychmoteur","Enfant: developpement langagier","Enfant: caractere","Enfant: comporetement");
    private final ObservableList<String> cat2 = FXCollections.observableArrayList("Adulte: L'histoire de sa maladie","Adulte: suivi medicale");
    private final ObservableList<String> cat = FXCollections.observableArrayList("Adulte: L'histoire de sa maladie","Adulte: suivi medicale","Enfant: structure familiale","Enfant: dynamique familiale","Enfant: antecedents familiaux","Enfant: developpement psychmoteur","Enfant: developpement langagier","Enfant: caractere","Enfant: comporetement");

    @Override
    public void initialize  (URL location, ResourceBundle resources) {
        categorymodifyBox.setValue("Category du question");
        categorymodifyBox.setItems(cat);
        String originalString = typelabel.getText();
        // String originalString = "Adulte: L'histoire de sa maladie";

        // Find the index of the character ':'
        int index = originalString.indexOf(':');

        if (index != -1) {
            // Get the substring before the character ':'
            String beforeColon = originalString.substring(0, index).trim();

            // Get the substring after the character ':'
            String afterColon = originalString.substring(index + 1).trim();

            System.out.println("Before ':': " + beforeColon);
            System.out.println("After ':': " + afterColon);

            if (beforeColon.equals("Enfant")) {
                System.out.println("enfant");
                categorymodifyBox.setValue("Category du question");
                categorymodifyBox.setItems(cat1);
            } else if (beforeColon.equals("Adulte")) {
                System.out.println("adulte");
                categorymodifyBox.setValue("Category du question");
                categorymodifyBox.setItems(cat2);
            }
        }
       // enoncelabel.setText();


    }


    @FXML
    protected  void onModifierclick() {
        String text = newques.getText();
        String originalString = categorymodifyBox.getValue().toString().trim();
        // String originalString = "Adulte: L'histoire de sa maladie";

        // Find the index of the character ':'
        int index = originalString.indexOf(':');

        if (index != -1) {
            // Get the substring before the character ':'
            String beforeColon = originalString.substring(0, index).trim();

            // Get the substring after the character ':'
            String afterColon = originalString.substring(index + 1).trim();

            System.out.println("Before ':': " + beforeColon);
            System.out.println("After ':': " + afterColon);
            Anamnese ana = orth.getAnamnese();
           // if (beforeColon.equals("Enfant")) {

                ana.ModifierQuestion(enoncelabel.getText(),text,afterColon,beforeColon);
                ana.afficherQuestionsAnamnese();
            if (selectedTab != null) {
                selectedTab.setEnonce(text);
                selectedTab.setType(originalString);
            }
            ((Stage) editscene.getScene().getWindow()).close();
           // } else if (beforeColon.equals("Adulte")) {}

            //holder.setUser(new AnaTab(text,originalString));

            //anamnesetab.getItems().add(e);
        }
    }
    @FXML
    protected  void onSupprimerclick() {
        isDeleteAction = true;
        String text = enoncelabel.getText();
       compteController cpt =  compteController.getcpt();
       cpt.delleteana();
        Anamnese ana = orth.getAnamnese();
        ana.supprimerQuestion(text);
        ((Stage) editscene.getScene().getWindow()).close();
    }
    @FXML
    protected  void onRetourclick() {
        ((Stage) editscene.getScene().getWindow()).close();
    }
}
