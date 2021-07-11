package fr.diginamic.yuka.entities;

public enum ScoreNutritionnel {
    /**
     * le grade nutritionnel d'un produit peut avoir de A à F
     */
    A("a"), B("b"), C("c"), D("d"), E("e"), F("f");

    private final String valeur;

    /**
     * Constructeur avec argument
     * @param valeur associée à l'élément
     */
    ScoreNutritionnel(String valeur) {
        this.valeur = valeur;
    }

    /**
     *
     * @return la valeur associée à la cste
     */
    public String getValeur() {
        return this.valeur;
    }
}
