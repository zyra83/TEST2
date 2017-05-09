package monappli.model.dao.jpa.utils;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.persistence.Entity;

/**
 * @Converter Convertisseur pour que JPA sache sérialiser les {@link LocalDate}.
 *            À attacher à un attribut de type {@link LocalDate} sur une
 *            {@link Entity}. Ici il est configuré en autoApply donc rien à
 *            faire.
 * 
 * @author mickael
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate paramX) {
		return Date.valueOf(paramX);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date paramY) {
		return paramY.toLocalDate();
	}

}
