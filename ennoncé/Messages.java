package monappli.commons.exceptions;

/**
 * Classe centralisant tous les messages d'exceptions
 * @author nicolas.magniez
 *
 */
public final class Messages {
	private Messages() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String DAO_EXC = "Probleme avec la persistence";
	public static final String INITIALISATION_IMPOSSIBLE_EXC = "Initialisation de prestations impossible";	
	
	public static final String DEJA_ABONNE_EXC = "%s est déja abonné à la prestation %s";
	public static final String NON_ABONNE_EXC = "%s n'est pas abonné à la prestation %s";
	
	public static final String LISTING_ABONNES_IMPOSSIBLE_EXC = "Impossible de lister les abonnés";
	public static final String LISTING_PRESTATIONS_IMPOSSIBLE_EXC = "Impossible de lister les prestations";
	public static final String CREATION_ABONNE_IMPOSSIBLE_EXC = "Impossible de créer l'abonné %s";
	public static final String MISEAJOUR_ABONNE_IMPOSSIBLE_EXC = "La mise à jour de  l'abonné %s n'est pas possible";
	
}
