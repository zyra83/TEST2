package garage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import garage.model.entities.Marque;
import garage.model.entities.MarqueFactory;
import garage.model.entities.Voiture;
import garage.model.refs.Couleur;
import view.utils.LectureConsole;

public class IhmPrincipalConsole implements Ihm {

	@Override
	public int afficherMenu() {
		for (String string : MENU_PRINCIPAL) {
			System.out.println(string);
		}
		return LectureConsole.saisirEntier(0, MENU_PRINCIPAL.length);
	}

	@Override
	public void afficher(String message) {
		System.out.println(message);
	}

	@Override
	public Voiture saisirNouvelleVoiture() {
		Voiture retour = new Voiture();

		LectureConsole.afficherMessageAvecSautLigne("Saisir l'immatriculation:");
		String newImmat = LectureConsole.saisirChaine();

		LectureConsole.afficherMessageAvecSautLigne("Saisir le modele:");
		String newModele = LectureConsole.saisirChaine();

		LectureConsole.afficherMessageAvecSautLigne("Saisir la  puissance:");
		int newPuissance = LectureConsole.saisirEntier();

		LectureConsole.afficherMessageAvecSautLigne("Saisir la date de mise en circulation(dd/MM/yyyy):");
		LocalDate newDateMec = LectureConsole.saisirLocalDate("dd/MM/yyyy");

		retour.setImmatriculation(newImmat);
		retour.setModele(newModele);
		retour.setPuissance(newPuissance);
		retour.setMiseEnCirculation(newDateMec);

		return retour;
	}

	@Override
	public void afficherVoitures(List<Voiture> plstVoitures) {
		for (Voiture voiture : plstVoitures) {
			afficherUneVoiture(voiture);
		}
	}

	@Override
	public void afficherUneVoiture(Voiture v) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.printf("%s %s immatriculée %s de %d ch mise en circu. le %s.%n", v.getMarque().getNom(),
				v.getModele(), v.getImmatriculation(), v.getPuissance(), dtf.format(v.getMiseEnCirculation()));
	}

	@Override
	public Voiture choisirVoiture(List<Voiture> plstVoitures) {
		for (Voiture voiture : plstVoitures) {
			System.out.printf("%d - %s %s;%n", plstVoitures.indexOf(voiture), voiture.getMarque(), voiture.getModele());

		}
		LectureConsole.afficherMessageAvecSautLigne("Choisissez un numéro de voiture :");
		return plstVoitures.get(LectureConsole.saisirEntier(0, plstVoitures.size() - 1));
	}

	@Override
	public String rechercherVoitureModele() {
		LectureConsole.afficherMessageAvecSautLigne("Tapez le modele de la voiture (meme partiel) :");
		return LectureConsole.saisirChaine();
	}

	@Override
	public Voiture modifierVoiture(Voiture vmodif) {
		LectureConsole.afficherMessageAvecSautLigne("Saisir la nouvelle immatriculation :");
		vmodif.setImmatriculation(LectureConsole.saisirChaine());

		LectureConsole.afficherMessageAvecSautLigne("Saisir le nouveau modèle :");
		vmodif.setModele(LectureConsole.saisirChaine());

		LectureConsole.afficherMessageAvecSautLigne("Saisir la nouvelle puissance :");
		vmodif.setPuissance(LectureConsole.saisirEntier());

		LectureConsole.afficherMessageAvecSautLigne("Saisir nouvelle date de mise en circulation(dd/MM/yyyy):");
		vmodif.setMiseEnCirculation(LectureConsole.saisirLocalDate("dd/MM/yyyy"));

		vmodif.setCouleur(choisirCouleur(Couleur.values()));

		return vmodif;
	}

	@Override
	public String saisirChaine() {
		return LectureConsole.saisirChaine();
	}

	@Override
	public Marque saisirNouvelleMarque() {
		LectureConsole.afficherMessageAvecSautLigne("Saisir le libelle de la nouvelle marque:");
		String newLibelleMarque = LectureConsole.saisirChaine();

		Marque m = MarqueFactory.fabriquerMarque(newLibelleMarque);
		return m;

	}

	@Override
	public boolean choisirAutreMarque() {
		LectureConsole.afficherMessageAvecSautLigne("Saisir une autre marque?(y/n)");
		String autre = LectureConsole.saisirChaine();

		return autre.equals("y");
	}

	@Override
	public Marque choisirMarque(List<Marque> plstMarques) {
		int i = 1;
		for (Marque m : plstMarques) {
			System.out.printf("%d - %s\n", i, m.getNom());
			i++;
		}
		int choix = LectureConsole.saisirEntier(1, plstMarques.size());
		return plstMarques.get(choix - 1);
	}

	@Override
	public Couleur choisirCouleur(Couleur[] values) {
		LectureConsole.afficherMessageAvecSautLigne("Choisir la nationalite:");
		for (int i = 0; i < Couleur.values().length; i++) {
			LectureConsole.afficherMessageAvecSautLigne(String.format("%d - %s", i + 1, Couleur.values()[i]));
		}
		int choix = LectureConsole.saisirEntier(1, Couleur.values().length);
		return Couleur.values()[choix - 1];
	}

	@Override
	public void afficherNombreVoituresParMarque(Map<Marque, Long> dico) {
		for (Marque m : dico.keySet()) {
			System.out.printf("%s : %s", m.getNom(), dico.get(m));
		}
	}

	@Override
	public void afficherMarques(List<Marque> plstmarques) {
		for (Marque marque : plstmarques) {
			System.out.printf("%s%n", marque.getNom());
		}

	}

}
