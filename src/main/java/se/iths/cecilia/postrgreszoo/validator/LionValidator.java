package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.exception.lionExceptions.*;
import se.iths.cecilia.postrgreszoo.model.Lion;

@Component
public class LionValidator {

    public void validate(Lion lion) {
        verifyName(lion.getName());
        verifyAge(lion.getAge());
        verifyWeight(lion.getWeight());
        verifyKills(lion.getKills());
    }

    public void validateUpdatedLion(Lion lion) {
        verifyAge(lion.getAge());
        verifyWeight(lion.getWeight());
        verifyKills(lion.getKills());
    }

    public void verifyName(String name) {
        if (name == null) {
            throw new LionNameIsNullException("Name cant be null");
        }
        if (name.isBlank()) {
            throw new LionNameIsEmptyException("Name cant be empty");
        }
    }

    public void verifyAge(int age) {
        if (age < 0 || age > 110) {
            throw new LionAgeOutOfRangeException("Age must be between 0 - 110");
        }
    }

    public void verifyWeight(double weight) {
        if (weight < 0 || weight > 350) {
            throw new LionWeightOutOfRangeException("Weight must be between 0 - 350");
        }
    }

    public void verifyKills(int kills) {
        if (kills < 0) {
            throw new LionKillsNegativeException("Kills cant be a negative number");
        }
    }
}
