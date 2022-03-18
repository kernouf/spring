package fr.univlille.redspring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe de transfert de données (DTO) de l'objet Utilisateur 
 * @author Equipe Rouge
 */
@Data
@AllArgsConstructor
public class UtilisateurDTO {
	private Integer id;
	private String login;
	private List<Integer> administrateur;
	private List<Integer> membre;
}
