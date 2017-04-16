package utilitaires;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DButils {

	private static String CHAINE_JDBC;
	private static String USER_JDBC;
	private static String PASS_JDBC;
	private static Connection cnx;

	private DButils() {

	}

	public static Connection obtenirConnection(String chaine) throws SQLException, IOException {

		// 1 - chargement de la ressource package
		InputStream resourceAsStream = DButils.class.getResourceAsStream(chaine);
		// DriverManager.getConnection(url, user, password);

		// 2 - Instanciation du preoperties
		Properties prop = new Properties();

		// 3 - chargementd du flux d'octets
		prop.load(resourceAsStream);

		// 4bis - Exploiter le properties
		prop.entrySet().forEach(entry -> {
			System.out.printf("Key : %s -> %s%n", entry.getKey(), entry.getValue());
		});

		// 4bisbis - Exploiter le properties
		String url = (prop.getProperty("CHAIN"));
		String user = (prop.getProperty("USER"));
		String password = (prop.getProperty("PASS"));

		Connection connection = null;
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public static Connection obtenirConnectionMagnez(String cheminProperties) {
		try {
			if (cnx == null || cnx.isClosed()) {
				chargerConfiguration(cheminProperties);
				visualiserConfiguration();
				cnx = DriverManager.getConnection(CHAINE_JDBC, USER_JDBC, PASS_JDBC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnx;
	}

	public static void chargerConfiguration(String cheminProperties) throws IOException {
		Properties prop = new Properties();
		prop.load(DButils.class.getResourceAsStream(cheminProperties));
		
		CHAINE_JDBC = prop.getProperty("CHAIN");
		USER_JDBC = prop.getProperty("USER");
		PASS_JDBC = prop.getProperty("PASS");

	}

	private static void visualiserConfiguration() {
		System.out.printf("Chaine de cnx : %s %s %s", CHAINE_JDBC, USER_JDBC, PASS_JDBC);
	}

}
