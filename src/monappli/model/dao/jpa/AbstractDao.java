package monappli.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import net.entetrs.commons.jpa.AbstractDaoStandardEdition;

public class AbstractDao<T> extends AbstractDaoStandardEdition<T> {

	private static EntityManager em = Persistence.createEntityManagerFactory("TEST2_BENOIT").createEntityManager();

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
