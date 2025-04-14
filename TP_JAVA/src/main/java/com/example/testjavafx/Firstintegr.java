package com.example.testjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import noyau.Orthophoniste ;

import java.io.IOException;

public class Firstintegr {



        @FXML
        private Button button;
        @FXML
        private TextField username;
        @FXML
        private TextField password;
        @FXML
        private Label wronglogin;
        public void userlogin(ActionEvent event) throws IOException {
            checklogin();
        }
        public void checklogin() throws IOException {
            if((username.getText().isEmpty()) || (password.getText().isEmpty())){
                wronglogin.setText("Please enter your data");
            }
            else{
                wronglogin.setText("Success");
                Orthophoniste orto=new Orthophoniste(username.getText(),"rili","tizi","none","9883238999", password.getText());
                System.out.println("le premier ortho is ");
                System.out.println(orto.getNom());
            }
        }





}
