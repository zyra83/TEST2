package test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import utilitaires.DButils;

public class DButilsTest {

	@Test(expected = NullPointerException.class)
	public void testChargerConfigInexistante() throws IOException {
		DButils.chargerConfiguration("unfichierquinexistepas.properties");
	}

	@Test
	public void testObtenirConnectionMagnez() throws SQLException, IOException {
		Connection cnx = DButils.obtenirConnection("/conf/bdd.properties");
		Assert.assertNotNull(cnx);
	}

	@Test
	public void testProperties() throws IOException {

		// 1 - chargement de la ressource package
		InputStream resourceAsStream = DButilsTest.class.getResourceAsStream("/conf/bdd.properties");

		// 2 - Instanciation du preoperties
		Properties prop = new Properties();

		// 3 - chargementd du flux d'octets
		prop.load(resourceAsStream);

		// 4bisbis - Exploiter le properties
		Assert.assertEquals(prop.get("PASS"), "rootroot");
		Assert.assertEquals(prop.get("USER"), "root");
		Assert.assertEquals(prop.get("CHAIN"), "jdbc:mysql://localhost:3306/CARS?autoReconnect=true&useSSL=false");

	}

}
