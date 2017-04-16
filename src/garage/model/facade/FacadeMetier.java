package garage.model.facade;

import java.util.List;
import java.util.Map;

import garage.model.dao.DaoFactory;
import garage.model.dao.exceptions.DaoException;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;

/**
 * La façade métier, suivant les cas va renvoyer directement les DaoException.
 * Mais si on a le temps, catcher les DaoExceptions, et renvoyer les Exceptions
 * de Facade conçues pour êtres affichées.
 * 
 * @author mickael
 *
 */
public class FacadeMetier implements IFacadeMetier {

	public FacadeMetier() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creerVoiture(Voiture v) throws DaoException {
		DaoFactory.fabriquerDaoVoitureJpaFactory().create(v);
	}

	@Override
	public void supprimerVoiture(Voiture v) throws DaoException {
		DaoFactory.fabriquerDaoVoitureJpaFactory().delete(v);
	}

	@Override
	public List<Voiture> listerLesVoitures() throws DaoException {
		return DaoFactory.fabriquerDaoVoitureJpaFactory().readAll();
	}

	@Override
	public List<Voiture> listerLesVoituresParPuissance() {
		return DaoFactory.fabriquerDaoVoitureJpaFactory().listerParPuissance();
	}

	@Override
	public List<Voiture> rechercherVoiture(String recherche) {
		return DaoFactory.fabriquerDaoVoitureJpaFactory().rechercherParModele(recherche);
	}

	@Override
	public void mettreAjourUneVoiture(Voiture v) throws DaoException {
		DaoFactory.fabriquerDaoVoitureJpaFactory().update(v);
	}

	@Override
	public Map<Marque, Long> compterVoituresParMarque() {
		return DaoFactory.fabriquerDaoMarqueJpaFactory().compterVoituresParMarque();
	}

	@Override
	public List<Marque> listerLesMarques() throws DaoException {
		return DaoFactory.fabriquerDaoMarqueJpaFactory().readAll();
	}

}
