package monappli.view;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import monappli.model.entities.Abonne;
import monappli.model.entities.AbonneFactory;
import monappli.model.entities.Prestation;
import monappli.model.entities.exceptions.AbonneInvalideException;

public class IhmPrincipalGraphique implements Ihm{

	@Override
	public int afficherMenu() {		
		String choix =  JOptionPane.showInputDialog(MENU_PRINCIPAL);
		return Integer.parseInt(choix);
	}

	@Override
	public void afficher(String msg) {
		JOptionPane.showMessageDialog(null, msg);		
	}
	
	@Override
	public String saisirChaine() {	
		return JOptionPane.showInputDialog(null,"Saisir" );
	}

	@Override
	public Abonne saisirNouvelAbonne() throws AbonneInvalideException{			
		String nom = JOptionPane.showInputDialog("Saisir le nom du nouvel abonné:");		
		String prenom = JOptionPane.showInputDialog("Saisir le prénom du nouvel abonné:");
				
		return AbonneFactory.fabriquerAbonne(nom,prenom);
		
			
	}

	@Override
	public void afficherAbonnes(List<Abonne> plstAbonnes) {
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Abonne a : plstAbonnes) {			
			sb.append(String.format("%s %s inscrit depuis le %s%n",a.getNom(),a.getPrenom(),dtf.format(a.getDateInscription())));
		}
		JOptionPane.showMessageDialog(null,sb.toString());

	}

	@Override
	public Abonne choisirAbonne(List<Abonne> plstAbonnes) {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Abonne a : plstAbonnes) {
			sb.append(String.format("%d-%s %s%n",i,a.getNom(),a.getPrenom()));
			i++;
		}
		int choix = Integer.parseInt(JOptionPane.showInputDialog(null,sb.toString()));
		return plstAbonnes.get(choix-1);
	}	

	@Override
	public Prestation choisirPrestation(List<Prestation> plstPrestations){
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (Prestation p   : plstPrestations) {
			sb.append(String.format("%d-%s%n",i,p.getLibelle()));
			i++;
		}
		int choix = Integer.parseInt(JOptionPane.showInputDialog(null,sb.toString()));
		return plstPrestations.get(choix-1);
	}

	@Override
	public boolean choisirAutrePrestation() {
		String autre = JOptionPane.showInputDialog(null,"Souscrire une nouvelle prestation? (y/n)");
		return autre.equals("y");
	}
	
	@Override
	public void afficherPrestations(List<Prestation> plstPrestations){
		StringBuilder sb = new StringBuilder();
		for (Prestation p : plstPrestations) {
			sb.append(String.format("%s%n",p.getLibelle()));
		}
		JOptionPane.showMessageDialog(null,sb.toString());		
	}

	@Override
	public void afficherDetailsRecettePrestation(Map<Prestation, BigDecimal> dico) {
		StringBuilder sb = new StringBuilder();
		for (Prestation p : dico.keySet()) {
			sb.append(String.format("%s %s €%n", p.getLibelle(),dico.get(p)));
		}
		JOptionPane.showMessageDialog(null,sb.toString());		
	}
}
