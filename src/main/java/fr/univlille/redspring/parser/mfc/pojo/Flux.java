package fr.univlille.redspring.parser.mfc.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Flux {
	
	private final String id;
	
	private String name;
	
	private String idSource;
	
	private String idTarget;
}
