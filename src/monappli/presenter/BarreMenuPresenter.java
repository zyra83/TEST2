package monappli.presenter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.apachecommons.CommonsLog;
import monappli.model.facade.exceptions.FacadeMetierException;
import monappli.model.facade.exceptions.InitialisationImpossibleException;

/**
 * Présenteur de la barre de menu de l'application.
 * 
 * @author mickael
 *
 */
@CommonsLog
public class BarreMenuPresenter extends AbstractPresenter {

	@FXML
	private HBox racine;

	/**
	 * utilisé dans le event handler pour fermer la fenêtre.
	 */
	private Stage primaryStage;

	/**
	 * Gère les demandes de fermeture de la fenêtre, par la croix et par le menu
	 * quitter.
	 */
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
		Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir quitter ?");
		Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
		exitButton.setText("Quitter");
		closeConfirmation.setHeaderText("Quitter l'application");
		closeConfirmation.initModality(Modality.APPLICATION_MODAL);
		closeConfirmation.initOwner(primaryStage);

		Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
		if (!ButtonType.OK.equals(closeResponse.get())) {
			event.consume();
		}
	};

	/**
	 * Active l'écoute du close event de l'application. J'ai mis ça
	 * arbitrairement dans le Presenter de menu (la plupart des exemples font ça
	 * dans le lanceur).
	 *
	 * La getScene est null lors de l'initialisation donc je l'observe pour
	 * activer l'écoute du onClose.
	 */
	@FXML
	private void initialize() {
		racine.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
			if (oldScene == null && newScene != null) {
				// scene is set for the first time. Now its the time to listen
				// stage changes.
				newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
					if (oldWindow == null && newWindow != null) {
						primaryStage = (Stage) newWindow;
						primaryStage.setOnCloseRequest(confirmCloseEventHandler);
					}
				});
			}
		});
	}

	@FXML
	private void importer() {
		// Demander le chargement du FXML de la vue Lister
		try {
			String userprofile = System.getProperty("user.home"); 
			FileChooser fc = new FileChooser();
			fc.setInitialDirectory(new File(userprofile));
			 
			// Filtre d'exstension, ici, uniquement les CSV, JPEG, PNG.
			fc.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf", "*.PDF"));
			fc.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv", "*.CSV"));
			fc.getExtensionFilters().add(new ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.JPG", "*.JPEG", "*.PNG"));
			
			File selectedFile = fc.showOpenDialog(racine.getScene().getWindow()); 
			
			if(selectedFile != null){
				getLeMetier().init(selectedFile.toPath());
			}

		} catch (InitialisationImpossibleException e) {
			if (log.isErrorEnabled())
				log.error(e.getMessage());
		}

	}

	@FXML
	private void lister() {
		// Demander le chargement du FXML de la vue Lister
		try {
			VBox vueLister = (VBox) FXMLLoader.load(getClass().getResource("/monappli/view/fx/Liste.fxml"));

			// Recuperer une reference de la scene et changer le graphe de scene
			Scene scene = racine.getScene();
			scene.setRoot(vueLister);

		} catch (IOException e) {
			if (log.isErrorEnabled())
				log.error(e.getMessage());
		}

	}

	@FXML
	private void ajouter() {
		// Demander le chargement du FXML de la vue Ajouter
		try {

			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/monappli/view/fx/AjouterModifier.fxml"));
			// TODO Passer une voiture en parametre pour modif
			loader.setController(new CreationPresenter());
			VBox vueAjouter = (VBox) loader.load();

			// Recuperer une reference de la scene et changer le graphe de scene
			Scene scene = racine.getScene();
			scene.setRoot(vueAjouter);
		} catch (IOException e) {
			if (log.isErrorEnabled())
				log.error(e.getMessage());
		}

	}

	@FXML
	private void afficherStats() {
		VBox vueStats;
		try {
			vueStats = (VBox) FXMLLoader.load(getClass().getResource("/monappli/view/fx/Statistiques.fxml"));
			// Recuperer une reference de la scene et changer le graphe de scene
			Scene scene = racine.getScene();
			scene.setRoot(vueStats);
		} catch (IOException e) {
			if (log.isErrorEnabled())
				log.error(e.getMessage());
		}

	}

	@FXML
	private void afficherDialAPropos() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Copyright");
		alert.setContentText("Made by Jason");
		alert.showAndWait();
	}

	@FXML
	private void quitter() {
		primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
