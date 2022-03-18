package fr.univlille.redspring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univlille.redspring.dao.ProjetRepository;
import fr.univlille.redspring.dao.UtilisateurRepository;
import fr.univlille.redspring.dto.UtilisateurDTO;
import fr.univlille.redspring.pojo.Utilisateur;

/**
 * Classe permettant d'exécuter les traitements métiers liés à l'objet Utilisateur et faisant le pont entre le Controller et le Repository
 * @author Equipe Rouge
 */
@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private ProjetRepository projetRepository;
	
	/**
	 * Renvoi tout les utilisateurs de la base.
	 * @return liste d'objets utilisateur DTO 
	 */
	@Transactional
	public List<UtilisateurDTO> findAll() {
		List<UtilisateurDTO> liste = new ArrayList<>();
		utilisateurRepository.findAll().forEach(e -> {
			liste.add(convertToDto(e));
		});
		return liste;
	}

	/**
	 * Renvoie l'utilisateur d'id id
	 * @param id l'id de l'utilisateur
	 * @return l'utilisateur sous format UtilisateurDTO
	 */
	@Transactional
	public UtilisateurDTO findById(Integer id) {
		Utilisateur user = utilisateurRepository.findById(id).orElseThrow();
		UtilisateurDTO res = convertToDto(user);
		return res;
	}
	
	/**
	 * Permet de rendre l'objet Utilisateur persistant dans notre base de données
	 * @param utilisateur l'utilisateur que l'on veut ajouter à la base
	 * @return l'objet Utilisateur persisté
	 */
	@Transactional
	public Utilisateur saveCustomer(UtilisateurDTO utilisateur) {
		return utilisateurRepository.save(convertToEntity(utilisateur));
	}

	/*
	 * public UtilisateurDto updateCustomer(UtilisateurDto customerDTO) { if
	 * (customerDTO != null) { Utilisateur updatedObject =
	 * utilisateurRepository.updateCustomerByDTO(convertToEntity(customerDTO));
	 * Utilisateur getCustomer =
	 * utilisateurRepository.findById(updatedObject.getId()).get(); UtilisateurDto
	 * responseCustomer = convertToDto(getCustomer); return responseCustomer; } else
	 * { return null; }
	 * 
	 * }
	 */
	@Transactional
	public void deleteCustomer(Integer id) {
		utilisateurRepository.deleteById(id);
	}

	public UtilisateurDTO convertToDto(Utilisateur utilisateur) {
		List<Integer> administrateur = new ArrayList<>();
		List<Integer> membre = new ArrayList<>();
		
		utilisateur.getAdministrateur().forEach(e -> {
			administrateur.add(e.getId());
		});
		utilisateur.getMembre().forEach(e -> membre.add(e.getId().getProjet().getId()));
		return new UtilisateurDTO(utilisateur.getId(), utilisateur.getLogin(), administrateur, membre);
	}

	private Utilisateur convertToEntity(UtilisateurDTO utilisateurDTO) {
		Utilisateur trans = new Utilisateur();
		trans.setId(utilisateurDTO.getId());
		trans.setLogin(utilisateurDTO.getLogin());
		utilisateurDTO.getAdministrateur()
				.forEach(e -> trans.addAdministrateur(projetRepository.findById(e).orElseThrow()));
		return trans;
	}

}
