package noyau.Dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

public class Objectifs implements Serializable {
    //private int idobj ;
    private String enonceObj ;
    private TypeObjectifs typeobj ;
    private ArrayList<Integer> listNote ;
    private boolean selected ;
    public Objectifs(String enonceObj  , TypeObjectifs typeobj){
        this.enonceObj = enonceObj ;
        this.typeobj = typeobj ;
        this.listNote  = new ArrayList<Integer>() ;

    }

    public ArrayList<Integer> getNote() {
        return listNote;
    }

    public void AjouterScore(int note) {
        if(note <= 5 && note >= 0) {
            System.out.println("setting note");
            this.listNote.add(note) ;
        }
    }
    public String getEnonceObj(){
        return this.enonceObj ;
    }
  /*  public boolean isAtteint(){
        boolean a = false ;// si la derniere valeur est 5 alors elle est atteinte
        if( this.listNote.get(this.listNote.size()-1) == 5){
            a = true ;
        }
        return a ;
    }*/
  public boolean isAtteint(){
      boolean a = false ;

      if(this.listNote.size() >0){
          if( this.listNote.get(this.listNote.size()-1) == 5){
              a= true ;
          }}
      return a;
  }
    public void affichernotes(){
        ListIterator<Integer> it = listNote.listIterator();
        while (it.hasNext()){
            Integer obj = it.next();
            System.out.print(obj+" , ");

        }
    }
    public TypeObjectifs getTypeobj() {
        return typeobj;
    }

    public void setTypeobj(TypeObjectifs typeobj) {
        this.typeobj = typeobj;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
