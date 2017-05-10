package monappli.model.facade;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import monappli.model.entities.Abonne;
import monappli.model.entities.Prestation;
import monappli.model.entities.exceptions.DejaAbonneException;
import monappli.model.facade.exceptions.CreationAbonneImpossible;
import monappli.model.facade.exceptions.InitialisationImpossibleException;
import monappli.model.facade.exceptions.ListingAbonnesImpossible;
import monappli.model.facade.exceptions.ListingPrestationsImpossible;
import monappli.model.facade.exceptions.MajAbonneImpossible;

public interface IFacadeMetier {

	/**
	 * Initialisation de prestations en base depuis un fichier fourni
	 */
	void init(Path p) throws InitialisationImpossibleException;

	/**
	 * Fonctionnalité "Ajouter un abonné"
	 * @param a : nouvel abonné
	 * @throws CreationAbonneImpossible 
	 */
	void creerUnAbonne(Abonne a) throws CreationAbonneImpossible;

	/**
	 * Fonctionnalité "Lister tous les abonnés"
	 * @return List<Abonne> : les abonnés de la bdd
	 * @throws ListingAbonnesImpossible 
	 */
	List<Abonne> listerLesAbonnes() throws ListingAbonnesImpossible;

	/**
	 * sous-fonctionnalité "Lister les prestations"
	 * @return List<Prestation> : les prestations de la bdd
	 * @throws ListingPrestationsImpossible 
	 */
	List<Prestation> listerLesPrestations() throws ListingPrestationsImpossible;

	/**
	 * Fonctionnalité "Ajouter une prestation a un abonné"
	 * 
	 * @param p : la prestation choisie
	 * @param a : l'abonné choisi 
	 * @throws MajAbonneImpossible 
	 * @throws DejaAbonneException 
	 */
	void ajouterPrestationAbonne(Prestation p, Abonne a) throws MajAbonneImpossible, DejaAbonneException;

	/**
	 * Fonctionnalité qui donne par Prestation le montant total des recettes sous la forme d'un dictionnaire:
	 * Clé     : Valeur
	 * FITNESS : 4500€
	 * AGUAGYM : 6200€
	 * UV      : 3560€
	 * etc...
	 * 
	 * @return Map<Prestation, BigDecimal> 
	 */
	Map<Prestation, BigDecimal> detaillerRecettePrestation();

}