package test;

import org.junit.Test;

import lombok.extern.apachecommons.CommonsLog;
import monappli.model.entities.exceptions.DejaAbonneException;

/**
 * On sait jamais...
 * 
 * @author mickael
 *
 */
@CommonsLog
public class LoggerTest {

	@Test
	public void testBeanValidation() throws DejaAbonneException {
		log.error("erreur de niveau error");
		log.warn("erreur de niveau warn");
		log.info("erreur de niveau info");
	}
}
