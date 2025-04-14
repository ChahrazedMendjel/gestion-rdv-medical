package noyau.CompteRenduReponce;

import java.io.Serializable;

public class ReponceAna implements Serializable {
    private String reponceana;
    private int quest;

    public int getQuest() {

        return quest;
    }

    public void setQuest(int quest) {
        this.quest = quest;
    }

    public String getReponceana() {
        return reponceana;
    }

    public void setReponceana(String reponceana) {
        this.reponceana = reponceana;
    }
}
