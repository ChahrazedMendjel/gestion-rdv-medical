package noyau.Anamnese;

import java.io.Serializable;

public enum CatQuestionEnfant implements Serializable {
    STRUCTURE_FAMILIALE("structure familiale"),
    DYNAMIQUE_FAMILIALE("dynamique familiale"),
    ANTECEDENTS_FAMILIAUX("antecedents familiaux"),
    CONDITIONS_NATALES("developpement psychmoteur"),
    DEVELOPPEMENT_LANGAGIER("developpement langagier"),
    CARACTERE("caractere"),
    COMPORETEMENT("comporetement");

    private final String categorieName;

    private CatQuestionEnfant(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getDisplayName() {
        return categorieName;
    }

    // Méthode pour récupérer l'instance de l'énumération à partir de la description
    public static CatQuestionEnfant fromDescription(String description) {
        for (CatQuestionEnfant category : CatQuestionEnfant.values()) {
            if (category.getDisplayName().equalsIgnoreCase(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
