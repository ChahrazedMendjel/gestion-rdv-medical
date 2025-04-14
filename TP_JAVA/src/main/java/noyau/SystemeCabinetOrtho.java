package noyau;


import java.util.*;

public class SystemeCabinetOrtho {
    private Set<Orthophoniste> listOrtho ;

    //private List<DossierPatient> listDocPatient ;
    public SystemeCabinetOrtho(){
        this.listOrtho = new HashSet<Orthophoniste>();
    }
    public void ajouterortho(Orthophoniste orth ){
        this.listOrtho.add(orth);
    }
    public void supprimererortho(Orthophoniste orth ){
        this.listOrtho.remove(orth);
    }

    public boolean validerOrtho(String nom , String motPasse){
        boolean a = false ;
        Iterator<Orthophoniste> it =  listOrtho.iterator() ;
        while(it.hasNext()) {
            Orthophoniste orth = it.next();
            if(orth.getNom().equals(nom) && orth.getMotPasse().equals(motPasse)){
                a = true ;
                break;
            }
        }

        return a ;
    }
    public Orthophoniste getOrtho(String nom , String motPasse) {
        Orthophoniste a = null;
        Iterator<Orthophoniste> it = listOrtho.iterator();
        while (it.hasNext()) {
            Orthophoniste orth = it.next();
            if (orth.getNom().equals(nom) && orth.getMotPasse().equals(motPasse)) {
                a = orth;
                break;
            }

        }
        return a ;
    }
        public void afficherOrtho() {
            if (listOrtho == null) {
                System.out.println("List of Orthophonistes is empty.");
            }
            for (Orthophoniste orth : listOrtho) {
                System.out.println("nom ortho " + orth.getNom());
                System.out.println("motpasse ortho " + orth.getMotPasse());

            }
        }
  /*  public Orthophoniste recupererOrthoId(){
      return ;
    }*/ // à mettre dans ortho


    /*public ArrayList<Integer> patientSoufreTrouble(Trouble trouble){
        Iterator<Orthophoniste> it =  listOrtho.iterator() ;
        while(it.hasNext()){
            Orthophoniste orth = it.next() ;
            HashSet<DossierPatient> listDoc =  orth.getListDocPatient() ;
            Iterator<DossierPatient> it2 =  listDoc.iterator() ;
            while(it2.hasNext()){
               if(it2.next().) // je dois recuperer tableau de bilns puis chercher dans chaque bilan ou il y a ce trouble et stocker le patient ;
            }

        }
    }*/
}
