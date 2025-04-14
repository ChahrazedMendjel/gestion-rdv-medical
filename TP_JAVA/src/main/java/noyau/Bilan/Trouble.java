package noyau.Bilan;

import noyau.Anamnese.CatQuestionAdulte;

import java.io.Serializable;

public enum Trouble implements Serializable {
    degultition("degultition"), neuro("neuro"), cognitif("cognitif");

    private final String troubleName;

    Trouble(String troubleName) {
        this.troubleName = troubleName;
    }

    public String getTroubleName() {
        return troubleName;
    }

    public static Trouble fromDescription(String description) {
        for (Trouble trouble : Trouble.values()) {
            if (trouble.getTroubleName().equalsIgnoreCase(description)) {
                return trouble;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
