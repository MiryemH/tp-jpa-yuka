package fr.diginamic.yuka.entities;
/**
 * @author Miryem HRARTI
 */

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Un Ingredient hérite de la classe Composant
 * */
@Entity
@Table(name="INGREDIENTS")
public class Ingredient extends Composant{

    /**
     * Constructeur par défaut
     */
    public Ingredient(){
        super();
    }

    /**
     * Constructeur avec arguments
     * @param libelle de l'Ingredient
     */
    public Ingredient(String libelle) {
        super(libelle);
    }
}
