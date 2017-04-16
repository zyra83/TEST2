package garage.presenter;

import java.util.List;

import garage.model.dao.exceptions.DaoException;
import garage.model.entities.Marque;
import garage.model.entities.Voiture;
import garage.model.facade.IFacadeMetier;
import garage.view.Ihm;

/**
 * Présenteur principal de l'appli, en charge de coordonner IHM et métier.
 * 
 * @author mickael
 *
 */
public class PresenterPrincipal {

	private IFacadeMetier fm;
	private Ihm ihm;

	public PresenterPrincipal(IFacadeMetier fm, Ihm ihm) {
		this.fm = fm;
		this.ihm = ihm;
	}

	/**
	 * Lance l'appli.
	 */
	public void executer() {
		boolean fini = false;

		while (!fini) {

			int choix = ihm.afficherMenu();

			switch (choix) {

			case 1: // Ajouter une voiture
				try {
					Voiture v = ihm.saisirNouvelleVoiture();
					ihm.afficherMarques(fm.listerLesMarques());
					Marque marqueChoisie;
					if (ihm.choisirAutreMarque()) {
						marqueChoisie = ihm.saisirNouvelleMarque();
					} else {
						marqueChoisie = ihm.choisirMarque(fm.listerLesMarques());
					}
					v.setMarque(marqueChoisie);
					fm.creerVoiture(v);
				} catch (DaoException e) {
					ihm.afficher(e.getMessage());
				}

				break;

			case 2: // Supprimer une voiture
				try {
					fm.supprimerVoiture(ihm.choisirVoiture(fm.listerLesVoitures()));
				} catch (Exception e) {
					ihm.afficher(e.getMessage());
				}
				break;

			case 3: // Lister les voitures
				try {
					ihm.afficherVoitures(fm.listerLesVoitures());
				} catch (Exception e) {
					ihm.afficher(e.getMessage());
				}
				break;

			case 4: // Lister les voitures par puissance
				try {
					ihm.afficherVoitures(fm.listerLesVoituresParPuissance());
				} catch (Exception e) {
					ihm.afficher(e.getMessage());
				}
				break;

			case 5: // Mettre a jour une voiture
				try {
					ihm.afficher("mettre à jour une voiture par son numéro immat partiel :");
					Voiture v = ihm.choisirVoiture(fm.listerLesVoitures());
					ihm.modifierVoiture(v);
					fm.mettreAjourUneVoiture(v);
				} catch (Exception e) {
					ihm.afficher(e.getMessage());
				}
				break;

			case 6: // Rechercher une voiture (par saisie du modèle)
				try {
					ihm.afficher("Saisir le modèle à rechercher :");
					String recherche = ihm.rechercherVoitureModele();
					List<Voiture> resultats = fm.rechercherVoiture(recherche);
					ihm.afficher("Résultats de la recherche :");
					ihm.afficherVoitures(resultats);
				} catch (Exception e) {
					ihm.afficher(e.getMessage());
				}
				break;

			case 7:// Compter les modeles d'une marque
				ihm.afficherNombreVoituresParMarque(fm.compterVoituresParMarque());
				break;

			case 0:
				fini = true;
				break;

			default:
				System.err.println("Choix invalide");

			}
		}
	}

}
