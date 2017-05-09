package monappli.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import monappli.model.entities.exceptions.DejaAbonneException;

// GROS JAVA
@SuppressWarnings("serial")
// LBK
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
// TODO : LBK - MEF ArrayLists !
// @NoArgsConstructor
// JPA
@Entity
@Table(name = "ABONNE")
public class Abonne extends AbstractEntity {

	/**
	 * Tarif de l'abonnement de base.
	 */
	public final static BigDecimal ABONNEMENT_BASE = new BigDecimal("240");

	/**
	 * Nom de l'abonne
	 */
	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name = "NOM", length = 50)
	// BV
	@Pattern(regexp = "[A-zÀ-ú]+", message = "Le nom doit être composé uniquement de lettres minuscules/majuscules.")
	String nom;

	/**
	 * Prénom de l'abonne
	 */
	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name = "PRENOM", length = 50)
	// BV
	@Pattern(regexp = "[A-zÀ-ú]+", message = "Le prénom doit être composé uniquement de lettres minuscules/majuscules.")
	String prenom;

	/**
	 * Date d'inscription de l'abonné.
	 */
	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name = "DATE_INSCRIPTION")
	LocalDate dateInscription;

	/**
	 * Liste des prestations souscrites par l'abonné
	 */
	// JPA
	@ManyToMany
	@JoinTable(name = "ABONNE_PRESTATION", joinColumns = @JoinColumn(name = "ABONNE_ID"), inverseJoinColumns = @JoinColumn(name = "PRESTATION_ID"))
	Collection<Prestation> lstPrestations;

	public Abonne() {
		super();
		this.lstPrestations = new ArrayList<Prestation>();
	}

	/**
	 * Nouvel abonne, la date d'inscripion par défaut est la date du jour.
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Abonne(String nom, String prenom) {
		this(nom, prenom, LocalDate.now());
	}

	/**
	 * Nouvel abonne.
	 * 
	 * @param nom
	 * @param prenom
	 * @param dateInscription
	 */
	public Abonne(String nom, String prenom, LocalDate dateInscription) {
		this();
		setNom(nom);
		setPrenom(prenom);
		setDateInscription(dateInscription);
	}

	public Collection<Prestation> getLstPrestations() {
		return Collections.unmodifiableCollection(this.lstPrestations);
	}

	/**
	 * ajoute une prestation à la liste des prestations de l’abonné : elle lève
	 * une DejaAbonneException si jamais l’abonne souscrit déjà à la prestation
	 * passée en paramètre Exemple de prestations : musculation, aquabiking,
	 * sauna, fitness...
	 * 
	 * @param p
	 * @throws DejaAbonneException
	 */
	public void souscrire(Prestation p) throws DejaAbonneException {
		if (this.lstPrestations.contains(p)) {
			throw new DejaAbonneException(this, p);
		} else {
			this.lstPrestations.add(p);
		}
	}

	/**
	 * enlève une prestation à la liste des prestations de l’abonné
	 * 
	 * @param p
	 */
	public void resilier(Prestation p) {

		if (this.lstPrestations.contains(p))
			this.lstPrestations.remove(p);

	}

	/**
	 * calcule le montant total de l’abonnement : abonnement de base + montant
	 * de chacune des prestations souscrites par l’abonné.
	 * 
	 * @return
	 */
	public BigDecimal calculerAbonnement() {
		BigDecimal total = new BigDecimal("0");
		for (Prestation prestation : lstPrestations) {
			total = total.add(prestation.getCout());
		}
		return total;
	}

}