package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * C'est une classe qui représente la marque d'un produit. Une marque est caractérisée par:
 * id, libellé et une liste de produits ayant cette marque
 */
@Entity
@Table(name="MARQUES")
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MARQ", nullable = false)
    private long id;
    @Column(name="LIB_MARQ", nullable = false)
    private String libelle;
    @OneToMany(mappedBy = "marque", cascade=CascadeType.ALL)
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Marque(){}

    /**
     * Constructeur avec arguments
     * @param libelle de la marque
     */
    public Marque(String libelle){
        this.libelle = libelle;
    }

    /**
     *
     * @return l'id de la marque
     */
    public long getId() {
        return id;
    }

    /**
     * modifie l'identifiant de la marque
     * @param id nouveau id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom de la marque
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * modifie le nom de la marque
     * @param libelle le nouveau nom de la marque
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     *
     * @return l'ensemble des produits ayant cette marque
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * modifie la liste des produits ayant cette marque
     * @param produits : nouvelle liste
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    /**
     *
     * @return la description de la marque  sous forme de chaine de
     * caractères en concaténant ses attributs: id, libelle
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ Id:" + id +
                ", Libelle:'" + libelle + '}';
    }
}
