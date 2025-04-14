package com.example.testjavafx.donneetab;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnaTab {
    private  StringProperty enonce;
    private  StringProperty type;


    /*public AnaTab(String enonce,String Type ) {
        this.enonce = enonce;
        this.type= Type;
    }*/
    public AnaTab(String enonce, String type) {
        this.enonce = new SimpleStringProperty(enonce);
        this.type = new SimpleStringProperty(type);
    }

   /* public String getEnonce() {
        return enonce;
    }

    public String getType() {
        return type;
    }
    public void setEnonce(String enonce) {
         this.enonce = enonce;
    }

    public void setType(String type ) {
        this.type = type;
    }
    */

    public String getEnonce() {
        return enonce.get();
    }

    public void setEnonce(String enonce) {
        this.enonce.set(enonce);
    }

    public StringProperty enonceProperty() {
        return enonce;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    }
