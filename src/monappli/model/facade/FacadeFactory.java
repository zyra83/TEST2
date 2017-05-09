package monappli.model.facade;

/**
 * Fabrique de métier
 * @author codeur
 *
 */
public final class FacadeFactory {
	private FacadeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static IFacadeMetier fabriquerFacadeMetier(){
		return new FacadeMetier();
	}
	
}

