package fr.univlille.redspring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe de transfert de données (DTO) de l'objet Projet 
 * @author Equipe Rouge
 */
@Data
@AllArgsConstructor
public class ProjetDTO {

	private Integer id;

	private String nom;

	private Integer createur;

	private List<Integer> membres;

	private List<Integer> fichiers;

}
