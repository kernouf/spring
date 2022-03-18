package fr.univlille.redspring.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Classe représentant l'id de la table Membership modélisée par la classe entité Membership.
 * @author Equipe Rouge
 */
@Embeddable
@Data
@SuppressWarnings("serial")
public class MembershipId implements Serializable{
	
	/**
	 * @JoinColumn pour dire que cet attribut fais référence à l'id_projet dans la table Projet
	 */
	@ManyToOne
	@JoinColumn(name = "id_projet")
	private Projet projet;
	
	/**
	 * @JoinColumn pour dire que cet attribut fais référence à l'id_utilisateur dans la table Utilisateur
	 */
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur membre;

}
