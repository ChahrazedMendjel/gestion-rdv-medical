package com.example.testjavafx.donneetab;

public class Person {
    private final String nDossier;
    private final String nom;
    private final String prenom;

    public Person(String nDossier, String nom, String prenom) {
        this.nDossier = nDossier;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNDossier() {
        return nDossier;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
