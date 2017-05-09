package monappli.model.entities.exceptions;

import monappli.commons.exceptions.Messages;
import monappli.model.entities.Abonne;
import monappli.model.entities.Prestation;

@SuppressWarnings("serial")
public class DejaAbonneException extends Exception {

	/**
	 * Exception perso au cas ou l'abonné à déjà souscrit une prestation;
	 * 
	 * @param i
	 * @param m
	 */
	public DejaAbonneException(Abonne a, Prestation p) {
		// TODO changer signature, compléter la Javadoc tout ça...
		super(String.format(Messages.DEJA_ABONNE_EXC, a.getNom(), p.getLibelle()));
	}
}
