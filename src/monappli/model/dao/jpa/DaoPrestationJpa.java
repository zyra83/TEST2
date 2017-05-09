package monappli.model.dao.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import lombok.NoArgsConstructor;
import monappli.model.dao.DaoPrestation;
import monappli.model.entities.Prestation;

/**
 * CRUD de prestations + méthodes spécifiques aux prestations
 * 
 * @author mickael
 *
 */
@NoArgsConstructor
public class DaoPrestationJpa extends AbstractDao<Prestation> implements DaoPrestation {

	@Override
	public Map<Prestation, BigDecimal> detaillerRecette() {
		Map<Prestation, BigDecimal> dico = new HashMap<>();
		// TODO !! Faire la requête
		TypedQuery<Object[]> query = getEntityManager().createQuery(
				"SELECT p, SUM(p.cout) FROM Abonne a LEFT JOIN a.lstPrestations p GROUP BY p", Object[].class);
		final List<Object[]> retourRequete = query.getResultList();
		for (Object[] tableauObjets : retourRequete) {
			dico.put((Prestation) tableauObjets[0], (BigDecimal)tableauObjets[1]);
		}
		return dico;
	}

	/**
	 * Vérifie qu'une presta existe déjà mais en fonction de son nom
	 */
	@Override
	public boolean existByName(Prestation t) {
		TypedQuery<Prestation> m = getEntityManager().createQuery("SELECT p FROM Prestation p WHERE p.libelle = :libelle", Prestation.class);
		m.setParameter("libelle", t.getLibelle());
		return m.getResultList().size() == 1;
	}

}
