package com.example.testjavafx.donneetab;

public class TableviewDossier {
    private  String familyname;
    private String name;
    private int number;
   private boolean checked;
    public TableviewDossier(String familyname,String name,int number,boolean checked){
        this.familyname=familyname;
        this.name=name;
        this.number=number;
        this.checked=checked;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
