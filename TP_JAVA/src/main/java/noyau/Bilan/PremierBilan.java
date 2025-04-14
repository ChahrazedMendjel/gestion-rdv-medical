package noyau.Bilan;

import noyau.CompteRenduReponce.CompteRendu;
import noyau.Dossier.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PremierBilan extends Bilan implements Serializable {
    private Map<Integer, String> tabreponceana ;
    private Patient patient ;
    public PremierBilan(Patient patient ){
        this.patient = patient ;
        this.tabreponceana = new HashMap<>();
        this.tabCompteRendu = new ArrayList<CompteRendu>();
    }
    public int findindicetest(String nomt) {
        int Index = -1; // Initialize to -1 (not found)
        for (int i = 0; i < this.tabreponceana.size(); i++) {
            if (this.tabreponceana.get(i).equals(nomt)) {
                Index = i; // Found the test, store its index
                break;
            }
        }
        return Index;
    }

    public void ajouterReponceAna(int i, String res) {
        this.tabreponceana.put(i,res);
    }


    public void supprimerReponceAna(int cle) {
        if (tabreponceana.containsKey(cle)) {
            this.tabreponceana.remove(cle);
        } else {
            System.out.println("Indice invalide : " + cle);
        }
    }

    public void modifierReponceAna(int cle, String modif) {
        if (tabreponceana.containsKey(cle)){
            this.tabreponceana.put(cle, modif);
        }
    }
    public Map<Integer, String> getTabreponceana() {
        return tabreponceana;
    }

    public void setTabreponceana(Map<Integer, String> tabreponceana) {
        this.tabreponceana = tabreponceana;
    }

    public void afficherReponceAna(){
        System.out.println("here 1") ;
        Iterator<Map.Entry<Integer, String>> iterator = tabreponceana.entrySet().iterator();

        // Parcourir la map à l'aide de l'itérateur
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Nques : " + key + ", Reponce : " + value);
        }
            //System.out.println(rdv.get());
        }
    }

