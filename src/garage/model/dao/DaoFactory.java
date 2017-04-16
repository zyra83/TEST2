package garage.model.dao;

import garage.model.dao.jpa.DaoMarqueJPA;
import garage.model.dao.jpa.DaoVoitureJPA;

/**
 * Fabrique de persistence. Ici du JPA mais l'interface DAO laisse le champ
 * libre à d'autres implémentations.
 * 
 * @author mickael
 *
 */
public final class DaoFactory {

	private DaoFactory() {

	}

	public static DaoVoiture fabriquerDaoVoitureJpaFactory() {
		return new DaoVoitureJPA();
	}

	public static DaoMarque fabriquerDaoMarqueJpaFactory() {
		return new DaoMarqueJPA();
	}

}
