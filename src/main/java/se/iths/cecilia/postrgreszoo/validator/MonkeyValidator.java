package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.exception.monkeyexceptions.*;
import se.iths.cecilia.postrgreszoo.model.Monkey;

@Component
public class MonkeyValidator {

    public void verifyThatNameIsNotNull(Monkey monkey) {
        if (monkey.getName() == (null)) {
            throw new MonkeyNameIsNullException("Monkey name cannot be null.");
        }
    }

    public void verifyThatNameIsNotEmpty(Monkey monkey) {
        if (monkey.getName().isBlank()) {
            throw new MonkeyNameIsEmptyException("Monkey name cannot be empty.");
        }
    }

    public void verifyThatMonkeyHasOriginCountry(Monkey monkey) {
        if (monkey.getOriginCountry().isBlank()) {
            throw new MonkeyHasNoCountryOfOriginException("Monkey origin country cannot be empty.");
        } else if (monkey.getOriginCountry().isEmpty()) {
            throw new MonkeyHasNoCountryOfOriginException("Monkey origin country cannot be empty.");
        }
    }

    public void verifyThatMonkeyHasType(Monkey monkey) {
        if (monkey.getType().isBlank()) {
            throw new MonkeyHasNoTypeException("Monkey must have a type.");
        }
    }

    public void verifyThatMonkeyHasHabitat(Monkey monkey) {
        if (monkey.getCurrentHabitat().isBlank()) {
            throw new MonkeyHasNoHabitatException("Monkey must have a habitat or place to live.");
        }
    }

}
