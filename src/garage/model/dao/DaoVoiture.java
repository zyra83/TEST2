package garage.model.dao;

import java.util.List;
import java.util.UUID;

import garage.model.entities.Voiture;

public interface DaoVoiture extends Dao<Voiture,UUID> {

	List<Voiture> listerParPuissance();	

	List<Voiture> rechercherParModele(String pmodele);
	
	//Voiture rechercherParImmat(String pimmat);
	

}
