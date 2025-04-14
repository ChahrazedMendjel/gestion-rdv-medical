package noyau.CompteRenduReponce;

import java.io.Serializable;

public class RepScore implements Serializable {
        private String reponse;
        private int score;

        public RepScore(String reponse, int score) {
            this.reponse = reponse;
            this.score = score;
        }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Getters et Setters pour reponse et score


}
