package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidAgeException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidColorException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidHowlKeyException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidNameException;
import se.iths.cecilia.postrgreszoo.model.Wolf;

@Component
public class WolfValidator {
    
    public void verifyNameIsNotNullOrBlank(Wolf wolf) {
        if (wolf.getName() == null || wolf.getName().isBlank()) {
            throw new WolfInvalidNameException("Wolf name cannot be empty!");
        }
    }

    public void verifyAgeIsValid(Wolf wolf) {
        if (wolf.getAge() < 0 || wolf.getAge() > 20) {
            throw new WolfInvalidAgeException("Wolf age must be between 0 and 20!");
        }
    }

    public void verifyColorIsNotNullOrBlank(Wolf wolf) {
        if (wolf.getFurColor() == null || wolf.getFurColor().isBlank()) {
            throw new WolfInvalidColorException("Wolf color cannot be empty!");
        }
    }

    public void verifyHowlKeyIsValid(Wolf wolf) {
        if (wolf.getHowlKey() == null) {
            throw new WolfInvalidHowlKeyException("Wolf howl key cannot be empty/must be A-G!");
        }
    }


}
