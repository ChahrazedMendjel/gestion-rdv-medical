package com.example.testjavafx.donneetab;

import noyau.Dossier.*;


public class GestionFich {
    private String name;
    private String goalname;
    private TypeObjectifs typeofgoal;

    public TypeObjectifs getTypeofgoal() {
        return typeofgoal;
    }

    public void setTypeofgoal(TypeObjectifs typeofgoal) {
        this.typeofgoal = typeofgoal;
    }

    public String getGoalname() {
        return goalname;
    }

    public void setGoalname(String goalname) {
        this.goalname = goalname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public GestionFich(String name,String goalname,TypeObjectifs typeofgoal){
        this.name=name;
        this.goalname=goalname;
        this.typeofgoal=typeofgoal;
    }
}
