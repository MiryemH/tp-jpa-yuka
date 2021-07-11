package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * C'est une classe qui représente la catégorie d'un produit. Une catégorie est caractérisée par:
 * id, libellé et une liste de produits appartenant à cette catégorie
 */
@Entity
@Table(name="CATEGORIES")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CAT", nullable = false)
    private long id;
    @Column(name="LIB_CAT", nullable = false)
    private String libelle;
    @OneToMany(mappedBy = "categorie", cascade=CascadeType.ALL)
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Categorie(){}

    /**
     * Constructeur avec arguments
     * @param libelle de la catégorie
     */
    public Categorie(String libelle){
        this.libelle = libelle;
    }

    /**
     *
     * @return l'id de la catégorie
     */
    public long getId() {
        return id;
    }

    /**
     * modifie l'identifiant de la catégorie
     * @param id nouveau id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom de la catégorie
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * modifie le nom de la catégorie
     * @param libelle le nouveau nom de la catégorie
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     *
     * @return l'ensemble des produits appartenant à cette catégorie
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * modifie la liste des produits appartenant à cette catégorie
     * @param produits : nouvelle liste
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    /**
     *
     * @return la description de la catégorie  sous forme de chaine de
     * caractères en concaténant ses attributs: id, libelle
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ Id:" + id +
                ", Libelle: " + libelle + '}';
    }
}
