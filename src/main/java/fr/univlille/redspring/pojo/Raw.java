package fr.univlille.redspring.pojo;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * Classe Raw qui est un fichier qui sera stocké en brut (Blob)
 * @author Equipe Rouge
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Raw extends Fichier {

	@Lob
	private Blob contenu;

}
