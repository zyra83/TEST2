package monappli.model.entities;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import monappli.model.entities.exceptions.AbonneInvalideException;

public final class AbonneFactory {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();;

	private AbonneFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Abonne fabriquerAbonne(String nom, String prenom) throws AbonneInvalideException {
		String erreur = "";
		Abonne a = new Abonne(nom, prenom);
		Set<ConstraintViolation<Abonne>> constraintViolations = validator.validate(a);
		if (constraintViolations.size() > 0) {
			Iterator<ConstraintViolation<Abonne>> iterator = constraintViolations.iterator();
			while (iterator.hasNext()) {
				ConstraintViolation<Abonne> cv = iterator.next();
				erreur += cv.getMessage() + "\n";
			}
			throw new AbonneInvalideException(erreur);
		}
		return a;
	}

}
