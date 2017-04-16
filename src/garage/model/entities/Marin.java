package garage.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name = "MARIN")
public class Marin extends AbstractEntity {

	public Marin(String nom) {
		this.nom = nom;
	}

	@Getter
	@Setter
	@Column(name = "NOM")
	String nom;

	@Getter
	@Setter
	// entité maître, multiplicité max
	// c'est la table associée à l'entité marin qui va porter la colonne de
	// jointure BATIMENT_ID
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BATIMENT_ID")
	Batiment sonBatiment;

}