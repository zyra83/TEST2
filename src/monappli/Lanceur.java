package monappli;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Point d'entrée/lancement de l'application.
 * 
 * @author mickael
 *
 */
public class Lanceur extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("TITRE DE MON APPLICATION");
		VBox vueAccueil = (VBox) FXMLLoader.load(getClass().getResource("/monappli/view/fx/Accueil.fxml"));
		primaryStage.setScene(new Scene(vueAccueil));
		primaryStage.show();
	}

	/**
	 * Lance l'application FX.
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

}
