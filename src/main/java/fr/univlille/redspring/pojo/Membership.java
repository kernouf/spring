package fr.univlille.redspring.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

/**
 * Classe entité qui représente la table Membership dans notre base de données.
 * @author Equipe Rouge
 */
@Entity
@Data
public class Membership {
	
	/**
	 * @EmbeddedId - Permet de dire que l'id de cette table est un id composé.
	 */
	@EmbeddedId
	private MembershipId id;
	
	/**
	 * Fais référence à la colonne created de notre table.
	 */
	@Column
	private LocalDate created;
	
	/**
	 * Fais référence à la colonne role de notre table.
	 */
	@Column
	private String role;
	
}
