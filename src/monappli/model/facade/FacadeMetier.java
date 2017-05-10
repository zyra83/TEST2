package monappli.model.facade;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import lombok.extern.apachecommons.CommonsLog;
import monappli.commons.exceptions.Messages;
import monappli.model.dao.DaoAbonne;
import monappli.model.dao.DaoFactory;
import monappli.model.dao.DaoPrestation;
import monappli.model.entities.Abonne;
import monappli.model.entities.Prestation;
import monappli.model.entities.PrestationFactory;
import monappli.model.entities.exceptions.DejaAbonneException;
import monappli.model.facade.exceptions.CreationAbonneImpossible;
import monappli.model.facade.exceptions.InitialisationImpossibleException;
import monappli.model.facade.exceptions.ListingAbonnesImpossible;
import monappli.model.facade.exceptions.ListingPrestationsImpossible;
import monappli.model.facade.exceptions.MajAbonneImpossible;

/**
 * La façade métier, suivant les cas va renvoyer directement les Exception. Mais
 * si on a le temps, catcher les Exceptions, et renvoyer les Exceptions de
 * Facade conçues pour êtres affichées.
 * 
 * @author mickael
 *
 */
@CommonsLog
public class FacadeMetier implements IFacadeMetier {

	// Besoin de 2 persistences JPA:1 pour chaque entité
	private DaoAbonne persistenceA = DaoFactory.fabriquerDaoAbonneJpa();
	private DaoPrestation persistenceP = DaoFactory.fabriquerDaoPrestationJpa();

	public FacadeMetier() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(Path p) throws InitialisationImpossibleException {
		try {
			List<String> lignes = Files.readAllLines(p);
			for (String ligne : lignes) {
				String[] tab = ligne.split(";");
				if (log.isInfoEnabled()) {
					log.info(String.format("Import de %s %s", tab[0], tab[1]));
				}
				Prestation prest = PrestationFactory.fabriquerPrestation(tab[0], tab[1]);
				// Teste la presence ou non de l'instrument
				if (!persistenceP.existByName(prest)) {
					persistenceP.create(prest);
				}
			}
			if (log.isInfoEnabled()) {
				log.info("Import du CSV en base...");
			}
		} catch (IOException | RuntimeException e) {
			if (log.isWarnEnabled()) {
				log.warn(Messages.INITIALISATION_IMPOSSIBLE_EXC, e);
			}
			throw new InitialisationImpossibleException(Messages.INITIALISATION_IMPOSSIBLE_EXC);
		}
	}

	@Override
	public void creerUnAbonne(Abonne a) throws CreationAbonneImpossible {
		try {
			persistenceA.create(a);
			if (log.isInfoEnabled()) {
				log.info(String.format("Création de l'abonné %s", a.getNom()));
			}
		} catch (RuntimeException e) {
			if (log.isWarnEnabled()) {
				log.warn(String.format(Messages.CREATION_ABONNE_IMPOSSIBLE_EXC, a.getNom()), e);
			}
			throw new CreationAbonneImpossible(String.format(Messages.CREATION_ABONNE_IMPOSSIBLE_EXC, a.getNom()), e);
		}
	}

	@Override
	public List<Abonne> listerLesAbonnes() throws ListingAbonnesImpossible {
		try {
			if (log.isInfoEnabled()) {
				log.info("Listing des abonnés en base.");
			}
			return persistenceA.readAll();
		} catch (RuntimeException e) {
			if (log.isWarnEnabled()) {
				log.warn(Messages.LISTING_ABONNES_IMPOSSIBLE_EXC, e);
			}
			throw new ListingAbonnesImpossible(Messages.LISTING_ABONNES_IMPOSSIBLE_EXC, e);
		}
	}

	@Override
	public List<Prestation> listerLesPrestations() throws ListingPrestationsImpossible {
		try {
			if (log.isInfoEnabled()) {
				log.info("Listing des prestations en base.");
			}
			return persistenceP.readAll();
		} catch (RuntimeException e) {
			if (log.isWarnEnabled()) {
				log.warn(Messages.LISTING_PRESTATIONS_IMPOSSIBLE_EXC, e);
			}
			throw new ListingPrestationsImpossible(Messages.LISTING_PRESTATIONS_IMPOSSIBLE_EXC, e);
		}
	}

	@Override
	public void ajouterPrestationAbonne(Prestation p, Abonne a) throws MajAbonneImpossible, DejaAbonneException {
		try {
			a.souscrire(p);
			persistenceA.update(a);
			if (log.isInfoEnabled()) {
				log.info(String.format("Ajout de la prestation %s à %s", p.getLibelle(), a.getNom()));
			}
		} catch (RuntimeException e) {
			if (log.isWarnEnabled()) {
				log.warn(String.format(Messages.MISEAJOUR_ABONNE_IMPOSSIBLE_EXC, a.getNom()), e);
			}
			throw new MajAbonneImpossible(String.format(Messages.MISEAJOUR_ABONNE_IMPOSSIBLE_EXC, a.getNom()), e);
		}
	}

	@Override
	public Map<Prestation, BigDecimal> detaillerRecettePrestation() {
		if (log.isInfoEnabled()) {
			log.info("détail des reccettes");
		}
		return persistenceP.detaillerRecette();
	}
}
