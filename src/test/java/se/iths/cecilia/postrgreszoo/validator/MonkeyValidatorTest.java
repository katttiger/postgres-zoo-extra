package se.iths.cecilia.postrgreszoo.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.cecilia.postrgreszoo.exception.monkeyexceptions.*;
import se.iths.cecilia.postrgreszoo.model.Monkey;

class MonkeyValidatorTest {

    Monkey monkey;
    MonkeyValidator monkeyValidator;

    @BeforeEach
    void setUp() {
        monkey = new Monkey();
        monkey.setId(1L);
        monkeyValidator = new MonkeyValidator();
    }


    @Test
    @DisplayName("Assert exception is thrown due to name being empty")
    void verifyThatNameIsNotEmpty() {
        monkey.setName("");
        Assertions.assertThrows(MonkeyNameIsEmptyException.class, () -> monkeyValidator.verifyThatNameIsNotEmpty(monkey));
    }

    @Test
    @DisplayName("Assert exception is thrown due to name being null")
    void verifyThrowsExceptionWhenNameIsNull() {
        monkey.setName(null);
        Assertions.assertThrows(MonkeyNameIsNullException.class, () -> monkeyValidator.verifyThatNameIsNotNull(monkey));
    }

    @Test
    @DisplayName("Assert exception is not thrown due to name not being null")
    void namePassesNameCheck() {
        monkey.setName("John");
        Assertions.assertDoesNotThrow(() -> monkeyValidator.verifyThatNameIsNotNull(monkey));
    }

    @Test
    @DisplayName("Assert exception is thrown due to monkey having no origin country")
    void verifyThatMonkeyHasOriginCountryThrowsException() {
        monkey.setOriginCountry("");
        Assertions.assertThrows(MonkeyHasNoCountryOfOriginException.class, () -> monkeyValidator.verifyThatMonkeyHasOriginCountry(monkey));
    }


    @Test
    @DisplayName("Assert exception is not thrown due to monkey having a country of origin")
    void verifyThatMonkeyHasCountryOfOrigin() {
        monkey.setOriginCountry("Canada");
        Assertions.assertDoesNotThrow(() -> monkeyValidator.verifyThatMonkeyHasOriginCountry(monkey));
    }

    @Test
    @DisplayName("Assert exception is thrown due to monkey having no type")
    void verifyThatMonkeyHasTypeThrowsException() {
        monkey.setType("");
        Assertions.assertThrows(MonkeyHasNoTypeException.class, () -> monkeyValidator.verifyThatMonkeyHasType(monkey));
    }

    @Test
    @DisplayName("Assert exception is not thrown due to monkey having a type")
    void verifyThatMonkeyHasTypeOf() {
        monkey.setType("Gorilla");
        Assertions.assertDoesNotThrow(() -> monkeyValidator.verifyThatMonkeyHasType(monkey));
    }

    @Test
    @DisplayName("Assert exception is thrown due to monkey having no habitat")
    void verifyThatMonkeyHasHabitatThrowsException() {
        monkey.setCurrentHabitat("");
        Assertions.assertThrows(MonkeyHasNoHabitatException.class, () -> monkeyValidator.verifyThatMonkeyHasHabitat(monkey));
    }

    @Test
    @DisplayName("Assert exception is not thrown due to monkey having a habitat")
    void verifyThatMonkeyHasHabitatOf() {
        monkey.setCurrentHabitat("E44");
        Assertions.assertDoesNotThrow(() -> monkeyValidator.verifyThatMonkeyHasHabitat(monkey));
    }
}