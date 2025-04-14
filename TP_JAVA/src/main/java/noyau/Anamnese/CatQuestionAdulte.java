package noyau.Anamnese;

import java.io.Serializable;

public enum CatQuestionAdulte implements Serializable {
    HISTOIRE_MALADIE("L'histoire de sa maladie"),
    SUIVI_MEDICAL("suivi medicale");

    private final String categorieName;

    CatQuestionAdulte(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getCategorieName() {
        return categorieName;
    }

    // Méthode pour récupérer l'instance de l'énumération à partir de la description
    public static CatQuestionAdulte fromDescription(String description) {
        for (CatQuestionAdulte category : CatQuestionAdulte.values()) {
            if (category.getCategorieName().equalsIgnoreCase(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
