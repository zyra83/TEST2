package test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;

import garage.model.dao.jpa.utils.JPAUtils;
import garage.model.entities.Batiment;
import garage.model.entities.Marin;

public class TestMarine {

	@Test
	public void testUUID() {
		EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");

		Marin m1 = new Marin("riri");
		Marin m2 = new Marin("fifi");
		Marin m3 = new Marin("loulou");

		Batiment b1 = new Batiment("Mébawl");

		m1.setSonBatiment(b1);
		m2.setSonBatiment(b1);
		m3.setSonBatiment(b1);

		b1.embarquer(m1, m2, m3);

		// Vu que les marins sont maitres, il faut persister le maitre et mettre
		// la cascade coté maitre sinon ça fait QUEDAL !!!
		em.getTransaction().begin();
		em.persist(m1);
		em.persist(m2);
		em.persist(m3);
		em.getTransaction().commit();

	}

	@Test
	public void testQuery() {
		EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");
		TypedQuery<Batiment> q0 = em.createQuery("SELECT b FROM Batiment b", Batiment.class);

		for (Batiment bat : q0.getResultList()) {
			System.out.println(bat.getNom());
			System.out.println(bat.getColMarins().size());

			// à la demande pour le cas LAZY
			for (Marin m : bat.getColMarins()) {
				System.out.println(m.getNom());
			}

		}
	}

	@Test
	public void testNamedQuery() {
		EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");

		TypedQuery<Batiment> q1 = em.createNamedQuery("READALL_BATIMENT", Batiment.class);

		for (Batiment bat : q1.getResultList()) {
			System.out.println(bat.getNom());
			System.out.println(bat.getColMarins().size());

			// à la demande pour le cas LAZY
			for (Marin m : bat.getColMarins()) {
				System.out.println(m.getNom());
			}
		}
	}

	@Test
	public void testNamedQueries() {
		EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");
		TypedQuery<Batiment> q2 = em.createNamedQuery("READ_BY_NOM", Batiment.class);
		q2.setParameter("param", "Mébawl");
		for (Batiment bat : q2.getResultList()) {
			System.out.println(bat.getNom());
			System.out.println(bat.getColMarins().size());

			// à la demande pour le cas LAZY
			for (Marin m : bat.getColMarins()) {
				System.out.println(m.getNom());
			}
		}
	}

}
