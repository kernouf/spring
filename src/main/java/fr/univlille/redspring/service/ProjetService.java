package fr.univlille.redspring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univlille.redspring.dao.FichierRepository;
import fr.univlille.redspring.dao.ProjetRepository;
import fr.univlille.redspring.dto.ProjetDTO;
import fr.univlille.redspring.pojo.Projet;
import fr.univlille.redspring.pojo.Raw;

/**
 * Classe permettant d'exécuter les traitements métiers liés à l'objet Projet et faisant le pont entre le Controller et le Repository
 * @author Equipe Rouge
 */
@Service
public class ProjetService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	private FichierRepository fichierRepository;
	
	/**
	 * Renvoie le projet d'id id
	 * @param id l'id du projet
	 * @return le projet sous forme d'objet DTO
	 */
	@Transactional
	public ProjetDTO findById(Integer id) {
		Projet projet = projetRepository.findById(id).orElseThrow();
		return convertToDto(projet);
	}
	
	/**
	 * Renvoie les projets de l'utilisateurs username
	 * @param username l'utilisateur dont on veut avoir les projets
	 * @return liste des projets sous forme d'objets DTO
	 */
	@Transactional
	public List<ProjetDTO> getAllProjectFromUsername(String username) {
		List<ProjetDTO> liste = new ArrayList<>();
		projetRepository.findByUsername(username).orElseThrow().forEach(projet -> liste.add(convertToDto(projet)));
		return liste;
	}

	/**
	 * Convertit un objet Projet en un objet ProjetDTO
	 * @param projet le projet que l'on veut convertir
	 * @return l'objet Projet converti en l'objet ProjetDTO
	 */
	public ProjetDTO convertToDto(Projet projet) {
		List<Integer> membres = new ArrayList<>();
		List<Integer> fichiers = new ArrayList<>();

		projet.getMembres().forEach(e -> {
			membres.add(e.getId().getMembre().getId());
		});
		projet.getFichiers().forEach(e -> {
			fichiers.add(e.getId());
		});

		return new ProjetDTO(projet.getId(), projet.getNom(), projet.getCreateur().getId(), membres, fichiers);
	}

	@Transactional
	public void newFile(Integer id, String name, byte[] données) throws SerialException, SQLException {
		Projet projet = projetRepository.findById(id).orElseThrow();
		
		Raw newFile = new Raw();
		newFile.setContenu(new SerialBlob(données));
		newFile.setNom(name);
		newFile.setProjet(projet);
		
		projet.getFichiers().forEach(e -> System.out.println(e.getNom()));
		projetRepository.save(projet);
		fichierRepository.save(newFile);
	}

	/*
	 * private Projet convertToEntity(ProjetDTO projetDTO) { Projet trans = new
	 * Projet(); trans.setId(projetDTO.getId()); trans.setNom(projetDTO.getNom());
	 * trans.setCreateur(utilisateurRepository.findById(projetDTO.getCreateur()).
	 * orElseThrow()); projetDTO.getMembres() .forEach(e ->
	 * trans.addMembres(projetRepository.findById(e).orElseThrow())); return trans;
	 * }
	 */

}
