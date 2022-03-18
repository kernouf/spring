package fr.univlille.redspring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe entité qui représente la table Utilisateur dans notre base de données.
 * 
 * @author Equipe Rouge
 */
@Entity
@Data
@NoArgsConstructor
public class Utilisateur {

	public Utilisateur(String login) {
		this.login = login;
	}

	public Utilisateur(Integer id, String login) {
		this.id = id;
		this.login = login;
	}

	/**
	 * @Id - Attribut id correspondant à la clé primaire de la table.
	 * @Column - Fais référence à la colonne id_utilisateur de notre table.
	 * @GeneratedValue - La façon dont notre id est généré (ici IDENTITY
	 *                 c'est-à-dire en auto-incrémenté).
	 */
	@Id
	@Column(name = "id_utilisateur")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Fais référence à la colonne login de notre table.
	 */
	@Column
	private String login;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "createur")
	private Set<Projet> administrateur = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "id.membre")
	private Set<Membership> membre = new HashSet<>();

	/**
	 * Permet d'ajout le p
	 * 
	 * @param projet
	 */
	public void addAdministrateur(Projet projet) {
		this.administrateur.add(projet);
		projet.setCreateur(this);
	}

}
