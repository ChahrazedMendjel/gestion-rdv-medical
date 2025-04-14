package noyau.CompteRenduReponce;

import java.io.Serializable;

public abstract class CompteRendu implements Serializable {
    private String conclusion;
    private String nomTest ;
    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    public String getNomTest() {
        return nomTest;
    }

    public void setNomTest(String nomTest) {
        this.nomTest = nomTest;
    }
}
