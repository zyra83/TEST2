package garage.model.dao.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe utilitaire offrant les méthodes de serialisation et deserialisation d'objets
 * 
 * @author nicolas.magniez
 *
 */
public final class SerDeser {
	
	private SerDeser() {
	}
	
	/**
	 * Méthode de classe qui sérialise un objet de type T dans un fichier file
	 * @param <T>
	 * @param file
	 * nom du fichier accueillant la classe sérialisée
	 * @param T
	 * @throws IOException
	 */
	public static <T> void save(String file,T obj) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
		fos.close();

	}

	
	/**
	 * Méthode de classe qui désérialise un objet depuis un fichier file
	 * @param <T>
	 * @param file
	 * nom du fichier correspondant à la classe sérialisée
	 * @return
	 * objet désérialisé
	 * @throws IOException
	 */
	public static <T> T load(String file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		T obj = null;
		try {
			obj = (T) ois.readObject() ;
			ois.close();
			fis.close();
			return obj;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}finally{
			ois.close();
			fis.close();
		}	
		return obj;
	}

	
}
