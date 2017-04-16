package commons.exceptions;

/**
 * Liste des messages des exceptions.
 * 
 * @author mickael
 *
 */
public final class Messages {
	
	private Messages() {
		// final class avec statics
	}
	
	public static final String DAO_EXC = "Problème avec la persistance.";
	public static final String SAIT_DEJA_JOUER_EXC = "%s sait déjà jouer de %s.";

}
