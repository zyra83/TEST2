package garage.model.entities.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * L'annotation pour PastDate, elle utilise {@link CheckPastDateValidator} pour
 * la vérification.
 * 
 * @author mickael
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckPastDateValidator.class)
@Documented
public @interface PastDate {

	String message() default "{PasteDate validator perso." + "message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
