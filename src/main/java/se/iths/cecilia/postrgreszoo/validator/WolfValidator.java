package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidAgeException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidNameException;
import se.iths.cecilia.postrgreszoo.model.Wolf;

@Component
public class WolfValidator {

    public void verifyNameIsNotNull(Wolf wolf) {
        if (wolf.getName() == null) {
            throw new WolfInvalidNameException("Wolf name cannot be null!");
        }
    }

    public void verifyNameIsNotEmpty(Wolf wolf) {
        if (wolf.getName().isBlank()) {
            throw new WolfInvalidNameException("Wolf name cannot be empty!");
        }
    }

    public void verifyAgeIsValid(Wolf wolf) {
        if (wolf.getAge() < 0 || wolf.getAge() > 15) {
            throw new WolfInvalidAgeException("Wolf age must be between 0 and 15!");
        }
    }

    
}
