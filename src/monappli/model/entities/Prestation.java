package monappli.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

// GROS JAVA
@SuppressWarnings("serial")
// LBK
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
// JPA
@Entity
@Table(name = "PRESTATION")
public class Prestation extends AbstractEntity {

	/**
	 * Libelle de la prestation.
	 */
	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name = "LIBELLE", length = 50)
	String libelle;

	/**
	 * Cout de la prestation.
	 */
	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name = "COUT")
	BigDecimal cout;

	/**
	 * Constructeur prenant le cout sous forme de String.
	 * 
	 * @param libelle
	 * @param cout
	 */
	public Prestation(String libelle, String cout) {
		this(libelle, new BigDecimal(cout));
	}

}