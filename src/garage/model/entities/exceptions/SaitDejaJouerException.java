package garage.model.entities.exceptions;

import commons.exceptions.Messages;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;

@SuppressWarnings("serial")
public class SaitDejaJouerException extends Exception {

	/**
	 * Exception perso au cas ou XXXX;
	 * @param i
	 * @param m
	 */
	public SaitDejaJouerException(Voiture i, Marque m) {
		// TODO changer signature, compléter la Javadoc tout ça...
		super(String.format(Messages.SAIT_DEJA_JOUER_EXC, m.getNom(), i.getMarque()));
	}
}
