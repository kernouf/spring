package fr.univlille.redspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Classe de transfert de données (DTO) de l'objet Ficher 
 * @author Equipe Rouge
 */
@Data
@AllArgsConstructor
public class FichierDTO {

	private Integer id;

	private String nom;

	private Integer projet;
}