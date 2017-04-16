package garage.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import garage.model.refs.StubEnum;
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
// TODO : LBK - MEF ArrayLists !
// @NoArgsConstructor
// JPA
@Entity
@Table(name = "BATIMENT")
@NamedQuery(name = "READALL_BATIMENT", query = "SELECT b FROM Batiment b")
@NamedQueries(value = { 
		@NamedQuery(name = "READALL_BATIMENT", query = "SELECT b FROM Batiment b"),
		@NamedQuery(name = "READ_BY_NOM", query = "SELECT b FROM Batiment b WHERE b.nom = :param")
})
public class MaBelleEntite extends AbstractEntity {

	// Un nom

	// LBK
	@Getter
	@Setter
	// JPA
	@Column(name="NOM",length=30)
	String nom;
	
	// LBK
	@Getter
	@Setter
	// JPA : Une énum
	@Enumerated(value = EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(50)", length = 50)
	StubEnum stubEnum;

	// JPA
	// @ManyToMany
	// @JoinTable(name="MUSICIEN_INSTRUMENT",joinColumns=@JoinColumn(name="MUSICIEN_ID"),
	// inverseJoinColumns=@JoinColumn(name="INSTRUMENT_ID"))
	Collection<Object> lstObjets;

	public MaBelleEntite() {
		this.lstObjets = new ArrayList<Object>();
	}

	public MaBelleEntite(String nom) {
		this();
		setNom(nom);
	}

	public Collection<Object> getLstObjets() {
		return Collections.unmodifiableCollection(this.lstObjets);
	}

	public void ajouterObjet(Object... os) {
		for (Object o : os) {
			if (!this.lstObjets.contains(o))
				this.lstObjets.add(o);
		}
	}

}