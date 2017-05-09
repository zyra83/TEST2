package test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import monappli.model.entities.Abonne;
import monappli.model.entities.Prestation;
import monappli.model.entities.exceptions.DejaAbonneException;
import monappli.model.facade.FacadeFactory;
import monappli.model.facade.exceptions.CreationAbonneImpossible;

public class AbonneTest {

	/**
	 * Vérifie qu'il est impossible d'ajouter 2 fois la même prestations à
	 * l'utilisateur.
	 * 
	 * @throws DejaAbonneException
	 */
	@Test(expected = DejaAbonneException.class)
	public void testerSouscrire() throws DejaAbonneException {
		Abonne a = new Abonne("benoit", "mickael");
		Prestation p1 = new Prestation("Aquaponey", "10");

		a.souscrire(p1);
		a.souscrire(p1);
	}

	/**
	 * Vérifie le bon fonctionnement du calcul de tarif des souscriptions.
	 * 
	 * @throws DejaAbonneException
	 */
	@Test
	public void testCalculerAbonnement() throws DejaAbonneException {
		Abonne a = new Abonne("benoit", "mickael");
		Prestation p1 = new Prestation("Aquaponey", "10");
		Prestation p2 = new Prestation("Aqualicorne", "30");

		a.souscrire(p1);
		a.souscrire(p2);

		Assert.assertTrue(new BigDecimal("40").equals(a.calculerAbonnement()));
	}

	
	
	
	@Test
	public void testPersist() throws DejaAbonneException, CreationAbonneImpossible {
		Abonne a = new Abonne("b1111enoit", "mickael");
		
		
		try {
			
			FacadeFactory.fabriquerFacadeMetier().creerUnAbonne(a);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

	
	
	
}
