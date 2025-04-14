package com.example.testjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noyau.Orthophoniste;
import noyau.SystemeCabinetOrtho;

//import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private AnchorPane sceneBienvenu;
    @FXML private TextField nom_orth ;
   // @FXML private TextField motpasse ;
    @FXML private Label doesntexist ;

    @FXML
    private PasswordField motpasse_orth;

    SystemeCabinetOrtho cabinet = new SystemeCabinetOrtho();
    ObjectInputStream in;
    @Override
    public void initialize  (URL location, ResourceBundle resources){
        doesntexist.setText("");
      /*  try{

        in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Ortho.txt"))));

           Orthophoniste orth =  (Orthophoniste)in.readObject();
           if(orth == null){
               System.out.println("ortho null");

           }else{
               System.out.println("info ortho "+orth.getNom());
           }
           cabinet.ajouterortho(orth);
           cabinet.afficherOrtho();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();}*/
    }

    public void switchToCreer() throws IOException {
            new SceneControler(sceneBienvenu, "hello-view.fxml");
        /*Node node = (Node) event.getSource();
        Scene scene = node.getScene();
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            stage.setUserData(cabinet);
        } else {
            System.err.println("Node is not attached to a scene.");
        }*/
        ObjectHolder holder = ObjectHolder.getInstance();
        // Step 3
        holder.setUser(cabinet);
    }
    @FXML
    protected void onHandleverification(){
        String nom = nom_orth.getText() ;
        String motpase = motpasse_orth.getText() ;
        String nomfile =  nom.concat(motpase) ;
        try{
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(nomfile))));
            Orthophoniste orth =  (Orthophoniste)in.readObject();
            if(orth == null){
                System.out.println("ortho null");

            }else{
                System.out.println("info ortho "+orth.getNom());
            }
            cabinet.ajouterortho(orth);
            cabinet.afficherOrtho();
            System.out.println("check");
            // aller à l'autre page
            try {
               // Orthophoniste orth = cabinet.getOrtho(nom,motpase);
                ObjectHolder holder = ObjectHolder.getInstance();
                holder.setUser(orth);
                new SceneControler(sceneBienvenu, "compteOrtho.fxml");
            }catch (Exception e){
                e.printStackTrace();

            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            doesntexist.setText(" Orthophoniste non valide ! vérifiez votre nom ou mot de passe ");

            // e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();}
       /* boolean a = cabinet.validerOrtho(nom,motpase) ;
        System.out.println("a = "+a);
        if(!a){
            doesntexist.setText(" Orthophoniste non valide ! vérifiez votre nom ou mot de passe ");
        }else{
            System.out.println("check");
          // aller à l'autre page
            try {
                Orthophoniste orth = cabinet.getOrtho(nom,motpase);
                ObjectHolder holder = ObjectHolder.getInstance();
                holder.setUser(orth);
                new SceneControler(sceneBienvenu, "compteOrtho.fxml");
            }catch (Exception e){
                e.printStackTrace();

            }

        }*/
    }

}
