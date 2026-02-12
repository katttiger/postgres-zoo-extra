package se.iths.cecilia.postrgreszoo.validator;

import org.springframework.stereotype.Component;
import se.iths.cecilia.postrgreszoo.model.Puma;

@Component
public class PumaValidator {

    public void validate(Puma puma) {

        if (puma.getName() == null || puma.getName().isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        if (puma.getAge() < 0) {
            throw new RuntimeException("Age cannot be negative");
        }

        if (puma.getWeight() <= 0) {
            throw new RuntimeException("Weight must be greater than 0");
        }
    }
}
