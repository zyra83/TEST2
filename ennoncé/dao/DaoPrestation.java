package monappli.model.dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import monappli.model.entities.Prestation;

/**
 * Interface Dao pour les Prestations ( herite du CRUD de Dao + méthodes spécifiques aux Prestations)
 * @author nicolas.magniez
 *
 */
public interface DaoPrestation extends Dao<Prestation,UUID> {
	/**
	 * Méthode qui recupere pour chacune des prestations la rentrée d'argent previsionnelle, 
	 * exemple:
	 * Clé     : Valeur
	 * FITNESS : 4500€
	 * AGUAGYM : 6200€
	 * UV      : 3560€
	 * etc...
	 * @return Map<Prestation, BigDecimal>
	 */
	Map<Prestation, BigDecimal> detaillerRecette();
}
