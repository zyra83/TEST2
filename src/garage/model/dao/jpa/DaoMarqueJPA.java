package garage.model.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import garage.model.dao.DaoMarque;
import garage.model.dao.exceptions.DaoException;
import garage.model.dao.jpa.utils.JPAUtils;
import garage.model.entities.Marque;

public class DaoMarqueJPA implements DaoMarque {
	private static final EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");

	@Override
	public Marque read(UUID k) throws DaoException {
		try {
			return em.find(Marque.class, k);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void create(Marque k) throws DaoException {
		em.getTransaction().begin();
		em.persist(k);
		em.getTransaction().commit();
	}

	@Override
	public List<Marque> readAll() throws DaoException {
		try {
			TypedQuery<Marque> m = em.createQuery("SELECT m FROM Marque m", Marque.class);
			return m.getResultList();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	public Map<Marque, Long> compterVoituresParMarque() {
		Map<Marque, Long> dico = new HashMap<>();
		TypedQuery<Object[]> query = em.createQuery("SELECT m, COUNT(v) FROM Voiture v LEFT JOIN v.marque m GROUP BY m",
				Object[].class);
		final List<Object[]> retourRequete = query.getResultList();
		for (Object[] tableauObjets : retourRequete) {
			dico.put((Marque) tableauObjets[0], (Long) tableauObjets[1]);
		}
		return dico;
	}

}
