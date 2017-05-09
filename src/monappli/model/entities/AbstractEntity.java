package monappli.model.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe mère de toutes les entités à persister. Elle génère un UUID.
 * 
 * Elle a son equals et son hashcode basés sur l'id.
 * 
 * Ne créer pas de table spécifique pour abstract entity. Attention à tout de
 * même ajouter AbstractEntity dans le persistence.xml si pas de découverte
 * automatique.
 * 
 * @author mickael
 *
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class AbstractEntity implements Serializable {

	@Id
	@Column(name = "ID", columnDefinition = "VARCHAR(36)")
	private UUID id;

	public AbstractEntity() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
