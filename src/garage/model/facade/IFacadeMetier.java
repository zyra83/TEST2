package garage.model.facade;

import java.util.List;
import java.util.Map;

import garage.model.dao.exceptions.DaoException;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;

public interface IFacadeMetier {

	/**
	 * 1ere fonctionnalité: menu 1 : ajouter une voiture
	 * @param v : Voiture
	 * @throws DaoException 
	 */
	void creerVoiture(Voiture v) throws DaoException;

	/**
	 * 2eme fonctionnalité: menu 2 : supprimer une voiture
	 * @param v
	 * @throws DaoException 
	 */
	void supprimerVoiture(Voiture v) throws DaoException;

	/**
	 * 3 eme fonctionnalité: lister toutes les voitures
	 * @return
	 * @throws DaoException 
	 */
	List<Voiture> listerLesVoitures() throws DaoException;

	/**
	 * 4 eme fonctionnalité  : lister les voitures triées par puissance
	 * @return: liste de voitures triées par puissance
	 */
	List<Voiture> listerLesVoituresParPuissance();

	
	/**
	 * 5 eme fonctionnalité :  Mettre à jour les caractéristiques d'une voiture en base de données
	 * @param v
	 * @throws DaoException 
	 */
	void mettreAjourUneVoiture(Voiture v) throws DaoException;

	/**
	 * 6 eme fonctionnalité: rechercher( par saisie du modèle) une /des voitures et afficher les détails
	 * @param recherche
	 * @return List<Voiture>
	 */
	List<Voiture> rechercherVoiture(String recherche);

	/**
	 * 7 eme fonctionnalite
	 * @return
	 */
	Map<Marque, Long> compterVoituresParMarque();

	
	/**
	 * Sous fonctionnalité du menu 1 et du menu 8: lister les marques de la table des marques
	 * @return List<Marque>
	 * @throws DaoException 
	 */
	List<Marque> listerLesMarques() throws DaoException;

	

}