package garage.model.dao;

import java.util.Map;
import java.util.UUID;

import garage.model.entities.Marque;

public interface DaoMarque extends Dao<Marque,UUID>{

	Map<Marque, Long> compterVoituresParMarque();

}
