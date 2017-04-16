package garage.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import net.entetrs.commons.jpa.AbstractDaoStandardEdition;

public class AbstractDaoNEPASUTILISERAUTEST<T> extends AbstractDaoStandardEdition<T> {

	private static EntityManager em = Persistence.createEntityManagerFactory("00_TEMPLATE_TEST").createEntityManager();

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
