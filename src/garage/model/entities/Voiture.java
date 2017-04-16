package garage.model.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import garage.model.entities.validation.PastDate;
import garage.model.refs.Couleur;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name = "VOITURE")
public class Voiture extends AbstractEntity {

	@Getter
	@Setter
	@PastDate(message = "La date est forcément passée.")
	LocalDate miseEnCirculation;
	
	@Getter
	@Setter
	String modele;
	
	@Getter
	@Setter
	@Min(value = 0, message = "La puissance est forcement positive.")
	int puissance;
	
	@Getter
	@Setter
	@Pattern(regexp = "[A-Z]{2}-\\d{3}-[A-Z]{2}", message = "L'immat doit être de la forme AA-123-BB.")
	String immatriculation;

	@Getter
	@Setter
	@Enumerated(value = EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(50)", length = 50)
	Couleur couleur;
	
	// LBK
	@Getter
	@Setter
	// JPA
	// @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToOne
	Marque marque;

}
