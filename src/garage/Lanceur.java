package garage;

import garage.model.facade.FacadeFactory;
import garage.model.facade.IFacadeMetier;
import garage.presenter.PresenterPrincipal;
import garage.view.Ihm;
import garage.view.IhmFactory;

/**
 * Point d'entrée/lancement de l'application.
 * 
 * @author mickael
 *
 */
public class Lanceur {

	/**
	 * Lance la magie/ou pas !
	 * 
	 * @param args ici je n'utilise pas ces arguments.
	 */
	public static void main(String[] args) {
		IFacadeMetier fm = FacadeFactory.fabriquerFacadeMetier();
		Ihm ihm = IhmFactory.fabriquerIhmConsole();
		PresenterPrincipal prez = new PresenterPrincipal(fm, ihm);
		prez.executer();
	}

}
