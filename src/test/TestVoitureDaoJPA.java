package test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import garage.model.dao.DaoFactory;
import garage.model.dao.exceptions.DaoException;
import garage.model.entities.Marque;
import garage.model.entities.MarqueFactory;
import garage.model.entities.Voiture;
import garage.model.entities.VoitureFactory;

public class TestVoitureDaoJPA {

	public static final Log LOG = LogFactory.getLog(TestMarqueDaoJPA.class.getSimpleName());
	List<Marque> readAll;

	@Before
	public void init() throws DaoException {
		EntityManager em = Persistence.createEntityManagerFactory("00_TEMPLATE_TEST").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM VOITURE WHERE id != '';").executeUpdate();
		em.getTransaction().commit();

		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM MARQUE WHERE id != '';").executeUpdate();
		em.getTransaction().commit();
	}

	@After
	public void post() throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory("00_TEMPLATE_TEST").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM VOITURE WHERE id != '';").executeUpdate();
		em.getTransaction().commit();

		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM MARQUE WHERE id != '';").executeUpdate();
		em.getTransaction().commit();

	}

	@Test
	public void testInsertionBDD() throws Exception {

		Marque m;
		Voiture v;
		Voiture v2;

		m = MarqueFactory.fabriquerMarque("MarqueTest");
		DaoFactory.fabriquerDaoMarqueJpaFactory().create(m);
		
		v = VoitureFactory.fabriquerVoiture("modele", "DJ-355-PA", 5, LocalDate.now().minusDays(1), m);
		v2 = VoitureFactory.fabriquerVoiture("modele2", "DJ-356-PP", 6, LocalDate.now().minusDays(1), m);

		// DaoFactory.fabriquerDaoMarqueJpaFactory().create(m);
		// m = DaoFactory.fabriquerDaoMarqueJpaFactory().read(m.getId());
		// v.setMarque(m);
		DaoFactory.fabriquerDaoVoitureJpaFactory().create(v);
		DaoFactory.fabriquerDaoVoitureJpaFactory().create(v2);

	}

}
