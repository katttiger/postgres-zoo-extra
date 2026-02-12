package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidAgeException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidColorException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidHowlKeyException;
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

    public void verifyColorIsNotBlank(Wolf wolf) {
        if (wolf.getFurColor().isBlank()) {
            throw new WolfInvalidColorException("Wolf color cannot be blank!");
        }
    }

    public void verifyColorIsNotNull(Wolf wolf) {
        if (wolf.getFurColor() == null) {
            throw new WolfInvalidColorException("Wolf color cannot be null!");
        }
    }

    public void verifyHowlKeyIsValid(Wolf wolf) {
        if (wolf.getHowlKey() == null) {
            throw new WolfInvalidHowlKeyException("Wolf howl key cannot be null/must be A-G!");
        }
    }


}
