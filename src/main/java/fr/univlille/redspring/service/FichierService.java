package fr.univlille.redspring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univlille.redspring.dao.FichierRepository;
import fr.univlille.redspring.dto.FichierDTO;
import fr.univlille.redspring.pojo.Fichier;

/**
 * Classe permettant d'exécuter les traitements métiers liés à l'objet Fichier et faisant le pont entre le Controller et le Repository
 * @author Equipe Rouge
 */
@Service
public class FichierService {

	@Autowired
	private FichierRepository fichierRepository;
	
	/**
	 * Renvoie la liste des fichiers contenus dans le projet name
	 * @param name le projet dont on veut avoir les fichiers
	 * @return liste des fichiers DTO contenus dans le projet name
	 */
	@Transactional
	public List<FichierDTO> getAllFilesFromProject(Integer id) {
		List<FichierDTO> liste = new ArrayList<>();
		fichierRepository.findByProjetId(id).orElseThrow().forEach(projet -> liste.add(convertToDto(projet)));;
		return liste;
	}
	
	/**
	 * Renvoie le fichier d'id id
	 * @param id l'id du fichier 
	 * @return le fichier sous forme d'objet DTO
	 */
	@Transactional
	public FichierDTO findById(Integer id) {
		Fichier fichier = fichierRepository.findById(id).orElseThrow();
		return convertToDto(fichier);
	}
	
	/**
	 * Permet de convertir un objet Fichier en objet FichierDTO
	 * @param fichier le fichier que l'on veut convertir
	 * @return le fichier converti en DTO
	 */
	public FichierDTO convertToDto(Fichier fichier) {

		return new FichierDTO(fichier.getId(), fichier.getNom(), fichier.getProjet().getId());
	}

	

	/*
	 * private fichier convertToEntity(fichierDTO fichierDTO) { Fichier trans = new
	 * fichier(); trans.setId(fichierDTO.getId()); trans.setNom(fichierDTO.getNom());
	 * trans.setCreateur(utilisateurRepository.findById(fichierDTO.getCreateur()).
	 * orElseThrow()); fichierDTO.getMembres() .forEach(e ->
	 * trans.addMembres(fichierRepository.findById(e).orElseThrow())); return trans;
	 * }
	 */

}