package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Un allergène hérite de la classe Composant
 * */
@Entity
@Table(name="ALLERGENES")
public class Allergene extends Composant{

    /**
     * Constructeur par défaut
     */
    public Allergene(){
        super();
    }

    /**
     * Constructeur avec arguments
     * @param libelle de l'allergène
     */
    public Allergene(String libelle) {
        super(libelle);
    }
}
