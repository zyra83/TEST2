package monappli.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import monappli.model.entities.Abonne;
import monappli.model.entities.Prestation;
import monappli.model.entities.exceptions.AbonneInvalideException;

public interface Ihm {
	final static String[] MENU_PRINCIPAL ={
			"######################\n ## Keepfit ##\n #####################",
			"1- Ajouter un abonne",
			"2- Faire souscrire une prestation a un abonne",
			"3- Lister les  abonnés",
			"4- Detailler les recettes par prestation",
			"0- Quitter"};

	int afficherMenu();

	void afficher(String message);

	String saisirChaine();	

	Abonne saisirNouvelAbonne() throws AbonneInvalideException;

	boolean choisirAutrePrestation();

	void afficherAbonnes(List<Abonne> plstAbonnes);

	void afficherPrestations(List<Prestation> plstPrestations);

	Abonne choisirAbonne(List<Abonne> plstAbonnes);

	Prestation choisirPrestation(List<Prestation> plstPrestations);

	void afficherDetailsRecettePrestation(Map<Prestation, BigDecimal> dico);	

}
