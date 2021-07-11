package fr.diginamic.yuka.entities;

/**
 * @author Miryem HRARTI
 */

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Un Additif hérite de la classe Composant
 * */
@Entity
@Table(name="ADDITIFS")
public class Additif extends Composant{

    /**
     * Constructeur par défaut
     */
    public Additif(){
        super();
    }

    /**
     * Constructeur avec arguments
     * @param libelle de l'Additif
     */
    public Additif(String libelle) {
        super(libelle);
    }
}
