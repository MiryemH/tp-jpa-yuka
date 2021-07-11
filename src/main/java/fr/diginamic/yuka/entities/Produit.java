package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe représente un produit. Il est caractérisé par: id, nom, une catégorie, une marque, un grade nutritionnel
 * une liste de données nutritionnelles (énergie, sel, etc...), une liste d'ingrédients, une liste d'additifs,
 * une liste d'allergènes
 */
@Entity
@Table(name="PRODUITS")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PROD", nullable = false)
    private long id;
    @Column(name="NOM_PROD", nullable = false)
    private String nom;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_CAT")
    private Categorie categorie;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_MARQ")
    private Marque marque;

    @Enumerated(EnumType.STRING)
    private ScoreNutritionnel scoreNutritionnel;

    @Embedded
    @ElementCollection
    private Set<DonneeNutritionnelle> donneeNutritionnelle = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="COMP_PROD",
            joinColumns= @JoinColumn(name="ID_PRODUIT", referencedColumnName="ID_PROD"),
            inverseJoinColumns= @JoinColumn(name="ID_COMPOSANT", referencedColumnName="ID_COMP"))
    private Set<Composant> ingredients = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="COMP_PROD",
            joinColumns= @JoinColumn(name="ID_PRODUIT", referencedColumnName="ID_PROD"),
            inverseJoinColumns= @JoinColumn(name="ID_COMPOSANT", referencedColumnName="ID_COMP"))
    private Set<Composant> additifs = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="COMP_PROD",
            joinColumns= @JoinColumn(name="ID_PRODUIT", referencedColumnName="ID_PROD"),
            inverseJoinColumns= @JoinColumn(name="ID_COMPOSANT", referencedColumnName="ID_COMP"))
    private Set<Composant> allergenes = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Produit() {
    }

    /**
     * Constructeur avec arguments
     * @param nom du produit
     */
    public Produit(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return id du produit
     */
    public long getId() {
        return id;
    }

    /**
     * modifie l'id du produit
     * @param id le nouveau id du produit
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom du produit
     */
    public String getNom() {
        return nom;
    }

    /**
     * modifie le nom du produit
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return la catégorie du produit
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * modifie la catégorie du produit
     * @param categorie nouvelle catégorie
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    /**
     *
     * @return la marque du produit
     */
    public Marque getMarque() {
        return marque;
    }

    /**
     * modifie la marque du produit
     * @param marque nouvelle marque
     */
    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    /**
     *
     * @return le grade ntritionnel
     */
    public ScoreNutritionnel getScoreNutritionnel() {
        return scoreNutritionnel;
    }

    /**
     * modifie le score nutritionnel du produit
     * @param scoreNutritionnel nouveau score nutritionnel
     */
    public void setScoreNutritionnel(ScoreNutritionnel scoreNutritionnel) {
        this.scoreNutritionnel = scoreNutritionnel;
    }

    /**
     *
     * @return la liste des données nutritionnelles
     */
    public Set<DonneeNutritionnelle> getDonneeNutritionnelle() {
        return donneeNutritionnelle;
    }

    /**
     * modifie la liste des données nutritionnelles
     * @param donneeNutritionnelle nouvelle liste
     */
    public void setDonneeNutritionnelle(Set<DonneeNutritionnelle> donneeNutritionnelle) {
        this.donneeNutritionnelle = donneeNutritionnelle;
    }

    /**
     *
     * @return la liste des ingrédients
     */
    public Set<Composant> getIngredients() {
        return ingredients;
    }

    /**
     * modifie la liste des ingrédients
     * @param ingredients nouvelle liste
     */
    public void setIngredients(Set<Composant> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @return la liste des additifs
     */
    public Set<Composant> getAdditifs() {
        return additifs;
    }

    /**
     * modifie la liste des additifs
     * @param additifs nouvelle liste
     */
    public void setAdditifs(Set<Composant> additifs) {
        this.additifs = additifs;
    }

    /**
     *
     * @return la liste des allergenes
     */
    public Set<Composant> getAllergenes() {
        return allergenes;
    }

    /**
     * modifie la liste des allergènes
     * @param allergenes nouvelle liste
     */
    public void setAllergenes(Set<Composant> allergenes) {
        this.allergenes = allergenes;
    }

    /**
     *
     * @return la description du produit  sous forme de chaine de
     * caractères en concaténant ses attributs: id, nom, catégorie et marque
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ Id:" + id +
                ", Nom:'" + nom +
                ", Categorie: " + categorie.getLibelle() +
                ", Marque: "+ marque.getLibelle()
                +'}';
    }
}
