package garage.model.dao.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import garage.model.dao.DaoVoiture;
import garage.model.dao.exceptions.DaoException;
import garage.model.dao.jpa.utils.JPAUtils;
import garage.model.entities.Voiture;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class DaoVoitureJPA implements DaoVoiture {
	private static final EntityManager em = JPAUtils.getEm("00_TEMPLATE_TEST");
	// public static final Log LOG = LogFactory.getLog(DaoVoitureJPA.class);

	@Override
	public void create(Voiture t) throws DaoException {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Voiture>> constraintViolations = validator.validate(t);
		if (constraintViolations.size() > 0) {
			Iterator<ConstraintViolation<Voiture>> iterator = constraintViolations.iterator();
			while (iterator.hasNext()) {
				ConstraintViolation<Voiture> cv = iterator.next();
				System.err
						.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

			}
		} else {
			try {
				em.getTransaction().begin();
				em.persist(t);
				em.getTransaction().commit();
				if (log.isDebugEnabled())
					log.info("ajout d'une voiture (Cf. le loggins dans projet musique).");
			} catch (Exception e) {
				em.getTransaction().rollback();
				throw new DaoException("pas possible d'ajouter l'automobiile...");
			}

		}

	}

	@Override
	public Voiture read(UUID k) throws DaoException {
		try {
			return em.find(Voiture.class, k);
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Voiture t) throws DaoException {
		try {
			em.getTransaction().begin();
			// TODO ça ça va surement merder vu qu'on est hors contexte de
			// persistance
			em.remove(t);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Voiture t) throws DaoException {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public List<Voiture> readAll() throws DaoException {
		try {
			TypedQuery<Voiture> v = em.createQuery("SELECT v FROM Voiture v", Voiture.class);
			return v.getResultList();
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public boolean exist(Voiture t) throws DaoException {
		TypedQuery<Boolean> v = em.createQuery("SELECT count(v) > 0 FROM Voiture v WHERE v.uuid = :id;", Boolean.class);
		v.setParameter("id", t.getId().toString());
		return v.getSingleResult();
	}

	@Override
	public List<Voiture> listerParPuissance() {

		try {
			TypedQuery<Voiture> v = em.createQuery("SELECT v FROM Voiture v ORDER BY v.puissance DESC", Voiture.class);
			return v.getResultList();
		} catch (Exception e) {
			// throw new DaoException(e.getMessage(), e);
		}
		return new ArrayList<Voiture>();
	}

	@Override
	public List<Voiture> rechercherParModele(String pmodele) {
		try {
			TypedQuery<Voiture> v = em.createQuery("SELECT v FROM Voiture v WHERE v.modele LIKE :modele",
					Voiture.class);
			v.setParameter("modele", "%" + pmodele + "%");
			return v.getResultList();
		} catch (Exception e) {
			// throw new DaoException(e.getMessage(), e);
		}
		return new ArrayList<Voiture>();
	}

}
