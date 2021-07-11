package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Une donnée nutritionnelle hérite de la classe Composant
 * */
@Embeddable
public class DonneeNutritionnelle {

    private String libelle;
    private double quantite;
    /**
     * Constructeur par défaut
     */
    public DonneeNutritionnelle(){
    }

    /**
     * Constructeur avec arguments
     * @param libelle de l'Additif
     */
    public DonneeNutritionnelle(String libelle, double quantite) {
        this.libelle = libelle;
        this.quantite = quantite;
    }

    /**
     *
     * @return la quantité de la donnée nutritionnelle
     */
    public double getQuantite() {
        return quantite;
    }

    /**
     * modifie la quantité de la donnée nutritionnelle
     * @param quantite nouvelle quantite
     */
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "### "+libelle + ", " +quantite + "###";
    }
}
