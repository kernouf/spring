package fr.univlille.redspring.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe entité qui représente la table Projet dans notre base de données.
 * 
 * @author Equipe Rouge
 */
@Entity
@Data
@NoArgsConstructor
public class Projet {

	public Projet(String nom, Utilisateur createur) {
		this.nom = nom;
		this.createur = createur;
		createur.getAdministrateur().add(this);
	}

	/**
	 * @Id - Attribut id correspondant à la clé primaire de la table.
	 * @GeneratedValue - La façon dont notre id est généré (ici IDENTITY
	 *                 c'est-à-dire en auto-incrémenté).
	 */
	@Id
	@Column(name = "id_projet")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Fais référence à la colonne nom de notre table.
	 */
	@Column
	private String nom;

	/**
	 * Fais référence à la colonne createur de notre table.
	 */
	@ManyToOne
	private Utilisateur createur;

	@OneToMany(mappedBy = "id.projet")
	private Set<Membership> membres;

	@OneToMany(mappedBy = "projet")
	private Set<Fichier> fichiers;

	public void addFichier(Fichier fichier) {
		this.fichiers.add(fichier);
	}

}
