package com.example.testjavafx.donneetab;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestTab {
    private StringProperty nom;
    private StringProperty type;
    private IntegerProperty capacite;

    public TestTab(String nom,String Type ,Integer c) {
        this.nom = new SimpleStringProperty(nom);
        this.type= new SimpleStringProperty(Type);
        this.capacite = new SimpleIntegerProperty(c) ;
    }
    public String getNom() {
        return nom.get();
    }

    public void setNom(String enonce) {
        this.nom.set(enonce);
    }

    public StringProperty nomProperty() {
        return nom;
    }
    public Integer getCapacite() {
        return capacite.get();
    }

    public void setCapacite(Integer capacite) {
        this.capacite.set(capacite);
    }

    public IntegerProperty capaciteProperty() {
        return capacite;
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
