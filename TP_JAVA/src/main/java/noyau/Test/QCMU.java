package noyau.Test;

import java.io.Serializable;
import java.util.HashSet;

public class QCMU extends Question implements Serializable { // questions à plusieur choix ou  à choix unique
    private HashSet<String> tabchoice ; ///GERER DE NE PAS AJOUTER LE MEME STRING
    private boolean type;  // true qcm , false qcmu
    public QCMU(HashSet<String> tabchoice, String quest ,boolean type ) {
        super(quest);
        this.tabchoice =  new HashSet<String>(tabchoice);
        this.type = type ;
    }
    public void ajouterchoix(String choice){
        this.tabchoice.add(choice);
    }
    public void supprimerchoice(String elementToRemove){
        if (this.tabchoice.contains(elementToRemove)) {
            tabchoice.remove(elementToRemove);
        } else {
            System.out.println(elementToRemove + " does not exist in the HashSet.");

        }}
    public void SetTab(HashSet<String> tabchoice) {
        this.tabchoice = tabchoice;
    }
    public HashSet<String> getTab() {
        return this.tabchoice;
    }
    public int getsizetab(){
        return this.tabchoice.size() ;
    }
    public boolean GetType() {
        return this.type;
    }
    public void SetType(boolean type ) {
        this.type = type;
    }

}


/*package noyau.Test;

public class QCMU extends Question { // questions à plusieur choix ou  à choix unique
    private String[] tabchoice;
    private boolean type;
    public QCMU(String[] tabchoice, String quest) {
        super(quest);
        this.tabchoice = tabchoice;
    }

    public void SetTab(String[] tabchoice) {
        this.tabchoice = tabchoice;
    }
    public String[] GetTab() {
        return this.tabchoice;
    }
    public boolean GetType() {
        return this.type;
    }
    public void SetType(boolean type ) {
        this.type = type;
    }

}
*/