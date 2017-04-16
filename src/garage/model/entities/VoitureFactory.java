package garage.model.entities;

import java.time.LocalDate;

public final class VoitureFactory {
	private VoitureFactory() {
		// TODO Auto-generated constructor stub
	}

	public static Voiture fabriquerVoiture(String modele, String immatriculation, int puissance, LocalDate mec,
			Marque marque) {
		
		Voiture v = new Voiture();
		v.setModele(modele);
		v.setImmatriculation(immatriculation);
		v.setPuissance(puissance);
		v.setMiseEnCirculation(mec);
		v.setMarque(marque);
		return v;
	}

}
