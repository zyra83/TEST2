package garage.model.dao.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import garage.model.entities.Marque;
import garage.model.entities.MarqueFactory;

/**
 * Classe utilitaire permettant de lire le fichier CSV contenant les
 * informations sur les marques: NomMarque;X;X
 * 
 * @author nicolas.magniez
 *
 */
public final class CsvTools {

	private CsvTools() {

	}

	/**
	 * Lit le fichier CSV de Vaccins et instancie pour chaque ligne du ficghier
	 * CSV un vaccin, l'ajoute dans une liste. La liste de vaccins est renvoyée
	 * ensuite.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Marque> lireFichierCsv(String fileName) {
		List<Marque> listeRetour = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String chaine;

			while ((chaine = br.readLine()) != null) {
				String[] tabChaine = chaine.split(";");
				// Integer.parseInt(tabChaine[1]),
				// Integer.parseInt(tabChaine[2]));
				Marque v = MarqueFactory.fabriquerMarque(tabChaine[0]);
				listeRetour.add(v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listeRetour;
	}

	/**
	 * Ecrit un fichier CSV nommé fileName ( 2eme paramètre) depuis une liste de
	 * vaccins( 1er paramètre). Non utile dans le cadre de ce TP.
	 * 
	 * @param listeVaccins
	 * @param fileName
	 */
	public static void ecrireFichierCsv(List<Marque> listeMarques, String fileName) {

		try (PrintWriter writer = new PrintWriter(fileName)) {
			for (Marque m : listeMarques) {
				writer.printf("%s%n", m.getNom());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Erreur pendant l'écriture du fichier CSV : " + e);
		}
	}
}
