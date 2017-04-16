package garage.model.dao.jpa;

import garage.model.dao.jpa.DaoMarqueJPA;
import garage.model.dao.jpa.DaoVoitureJPA;

/**
 * Fabrique de persistence (ici uniquement JPA).
 * 
 * @author mickael
 *
 */
public final class DaoFactory {

	private DaoFactory() {

	}

	public static DaoVoitureJPA fabriquerDaoVoitureJpaFactory() {
		return new DaoVoitureJPA();
	}

	public static DaoMarqueJPA fabriquerDaoMarqueJpaFactory() {
		return new DaoMarqueJPA();
	}

}
