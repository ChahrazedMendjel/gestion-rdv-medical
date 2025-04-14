package noyau.Dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FicheSuivi implements Serializable {
    private String nomFichSuivi ;
    private List<Objectifs> listobjs ;
    public FicheSuivi(String nomFichSuivi ){
        this.nomFichSuivi = nomFichSuivi ;
        this.listobjs = new ArrayList<Objectifs>();
    }
    public void ajouterobj(String enonceObj  , TypeObjectifs typeobj){
        this.listobjs.add(new Objectifs(enonceObj  ,typeobj)) ;
    }
    public void supprimerobj(Objectifs Objf){
        this.listobjs.remove(Objf) ;
    }
   /* public boolean canAddFichSuivi(){
        boolean a = true ;
        Iterator<Objectifs> it = listobjs.iterator() ;
        boolean nx = it.hasNext() ;
        System.out.println("it.hasNext()"+nx);
        while (nx){
            boolean bl = it.next().isAtteint() ;
            System.out.println("bl"+bl);
           if(!bl){
               a =  false ;

           }
            nx = it.hasNext() ;
        }
         return a ;
    }*/
    public List<Objectifs> getListobjs(){
        return this.listobjs;
    }

    public void afficherObjs(){
        ListIterator<Objectifs>  it = listobjs.listIterator();
        while (it.hasNext()){
            Objectifs obj = it.next();
            System.out.println(obj.getEnonceObj());
            System.out.print("note :");
            obj.affichernotes();
            System.out.println("isAtteint "+obj.isAtteint());
        }
    }
    public Objectifs getObjnom(String nomo){
        ListIterator<Objectifs>  it = listobjs.listIterator();
        Objectifs ob = null ;
        while (it.hasNext()){
            Objectifs obj = it.next();
            if(obj.getEnonceObj().equals(nomo)){
                ob = obj ;
                break;
            }
        }
        return ob ;
    }
   /* public boolean canAddFichSuivi(){

        boolean a = true ;
        System.out.println("this.listobjs.size() "+this.listobjs.size());
        for(int i = 0 ; i< this.listobjs.size() ; i++){
            if( this.listobjs.get(i).isAtteint() == false ){
             a = false ;
             System.out.println("hello");
            }
        }
        System.out.println("a"+a);
        return  a ;
    }*/
   public boolean canAddFichSuivi(){

       boolean a = true ;
       System.out.println("this.listobjs.size() "+this.listobjs.size());
       for(int i = 0 ; i< this.listobjs.size() ; i++){
           System.out.println("i");
           System.out.println(i);
           Objectifs lobjectif= this.listobjs.get(i);
           System.out.println(this.listobjs.get(i).isAtteint());
           if( this.listobjs.get(i).isAtteint() == false ){
               a = false ;
               System.out.println("hello");
           }
       }
       System.out.println("a"+a);
       return  a ;
   }

    public String getNomFichSuivi() {
        return nomFichSuivi;
    }

    public void setNomFichSuivi(String nomFichSuivi) {
        this.nomFichSuivi = nomFichSuivi;
    }

}
