package fr.univlille.redspring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Classe entité qui représente la table Fichier dans notre base de données.
 * @author Equipe Rouge
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Fichier {
	
	/**
	 * @Id - Attribut id correspondant à la clé primaire de la table.
	 * @GeneratedValue - La façon dont notre id est généré (ici AUTO).
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * Fais référence à la colonne nom de notre table Fichier.
	 */
	@Column
	private String nom;
	
	/**
	 * Fais référence à la colonne projet de notre table Fichier.
	 */
	@ManyToOne
	private Projet projet;
	
	public void setProjet(Projet projet) {
		this.projet = projet;
		projet.addFichier(this);
	}
}
