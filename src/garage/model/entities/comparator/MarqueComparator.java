package garage.model.entities.comparator;

import java.util.Comparator;

import garage.model.entities.Marque;

public class MarqueComparator implements Comparator<Marque > {

	@Override
	public int compare(Marque o1, Marque o2) {
		return o1.getNom().compareTo(o2.getNom());
	}

}
