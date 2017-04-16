package garage.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

// GROS JAVA
@SuppressWarnings("serial")

// LBK
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity
@Table(name = "BATIMENT")
@NamedQuery(name = "READALL_BATIMENT", query = "SELECT b FROM Batiment b")
@NamedQueries(value = { 
		@NamedQuery(name = "READALL_BATIMENT", query = "SELECT b FROM Batiment b"),
		@NamedQuery(name = "READ_BY_NOM", query = "SELECT b FROM Batiment b WHERE b.nom = :param")
})
public class Batiment extends AbstractEntity {

	@Getter
	@Setter
	@Column(name = "NOM")
	String nom;

	// Entité esclave porte le mappedBy
	// fetch type eager récupere les marins en meme temps que le batiment
	// par défaut LAZY : à la demande qui du coup va charger si besoin.
	@OneToMany(mappedBy = "sonBatiment", fetch=FetchType.EAGER)
	Collection<Marin> colMarins;

	public Collection<Marin> getColMarins() {
		return Collections.unmodifiableCollection(colMarins);
	}

	/**
	 * Ajouter un/plusieurs marins à l'équipage
	 * 
	 * @param m
	 */
	public void embarquer(Marin... m) {
		for (Marin marin : m) {
			if (colMarins.contains(marin))
				this.colMarins.add(marin);
		}
	}

	/**
	 * Enlève un/plusieurs marins à l'équipage
	 * 
	 * @param m
	 */
	public void debarquer(Marin... m) {
		for (Marin marin : m) {
			if (!colMarins.contains(marin))
				this.colMarins.remove(marin);
		}
	}

	public Batiment() {
		this.colMarins = new ArrayList<Marin>();
	}

	public Batiment(String nom) {
		this();
		this.setNom(nom);
	}

}