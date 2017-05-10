package monappli.presenter;

import monappli.model.facade.FacadeMetier;

public abstract class AbstractPresenter {

	// La facade metier
	private FacadeMetier leMetier = new FacadeMetier();

	protected FacadeMetier getLeMetier() {
		return leMetier;
	}

}
