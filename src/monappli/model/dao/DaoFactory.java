package monappli.model.dao;

import monappli.model.dao.jpa.DaoAbonneJpa;
import monappli.model.dao.jpa.DaoPrestationJpa;

/**
 * Factory de DAO, ici du JPA uniquement.
 * @author mickael
 *
 */
public final class DaoFactory {

	private DaoFactory() {
		
	}

	
	public static DaoAbonneJpa fabriquerDaoAbonneJpa() {
		return new DaoAbonneJpa();
	}		
	
	public static DaoPrestationJpa fabriquerDaoPrestationJpa() {
		return new DaoPrestationJpa();
	}		
}
