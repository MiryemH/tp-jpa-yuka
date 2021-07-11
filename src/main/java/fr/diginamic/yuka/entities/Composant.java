package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** C'est une classe mère pour modéliser les composants d'un produit: ingrédient, allergène, additif et donnée nutritionnelle
 * Un composant est caractérisé par: id, libellé et liste des produits auxquels il appartient
 */

@Entity
@Table(name="COMPOSANTS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Composant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_COMP", nullable = false)
    private Long id;
    @Column(name="LIB_COMP", nullable = false, columnDefinition="TEXT")
    private String libelle;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="COMP_PROD",
            joinColumns= @JoinColumn(name="ID_COMPOSANT", referencedColumnName="ID_COMP"),
            inverseJoinColumns= @JoinColumn(name="ID_PRODUIT", referencedColumnName="ID_PROD"))
    private Set<Produit> produits = new HashSet<>();

    /**
     * Constructeur par défaut
     */
    public Composant(){}

    /**
     * Constructeur avec arguments
     * @param libelle du composant
     */
    public Composant(String libelle){
        this.libelle = libelle;
    }

    /**
     *
     * @return l'id du composant
     */
    public Long getId() {
        return id;
    }

    /**
     * modifie l'identifiant du composant
     * @param id nouveau id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return le nom du composant
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * modifie le nom du composant
     * @param libelle le nouveau nom du composant
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     *
     * @return l'ensemble des produits auxquels appartient le composant
     */
    public Set<Produit> getProduits() {
        return produits;
    }

    /**
     * modifie la liste des produits auxquels appartient le composant
     * @param produits : nouvelle liste
     */
    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    /**
     *
     * @return la description d'un composant  sous forme de chaine de
     * caractères en concaténant ses attributs: id, libelle
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ Id:" + id +
                ", Libelle:" + libelle + '}';
    }

}
