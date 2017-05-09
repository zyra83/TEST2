package monappli.model.entities;

public final class PrestationFactory {
	private PrestationFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Prestation fabriquerPrestation(String libelle, String cout) {
		return new Prestation(libelle, cout);
	}

}
