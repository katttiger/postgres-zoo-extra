package se.iths.cecilia.postrgreszoo.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidAgeException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidColorException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidHowlKeyException;
import se.iths.cecilia.postrgreszoo.exception.WolfInvalidNameException;
import se.iths.cecilia.postrgreszoo.model.Wolf;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WolfValidatorTest {

    WolfValidator wolfValidator;
    Wolf wolf;

    @BeforeEach
    void setUp() {
        wolf = new Wolf();
        wolf.setId(1L);
        wolfValidator = new WolfValidator();
    }

    @Test
    void nullNameShouldThrowException() {
        wolf.setName(null);
        assertThrows(WolfInvalidNameException.class,
                () -> wolfValidator.verifyNameIsNotNullOrBlank(wolf));
    }

    @Test
    void blankNameShouldThrowException() {
        wolf.setName("");
        assertThrows(WolfInvalidNameException.class,
                () -> wolfValidator.verifyNameIsNotNullOrBlank(wolf));
    }

    @Test
    void validNameShouldBeAccepted() {
        wolf.setName("Zeke");
        wolfValidator.verifyNameIsNotNullOrBlank(wolf);
    }

    @Test
    void invalidAgeShouldThrowException() {
        wolf.setAge(-3);
        assertThrows(WolfInvalidAgeException.class,
                () -> wolfValidator.verifyAgeIsValid(wolf));
    }

    @Test
    void validAgeShouldBeAccepted() {
        wolf.setAge(3);
        wolfValidator.verifyAgeIsValid(wolf);
    }

    @Test
    void nullFurColorShouldThrowException() {
        wolf.setFurColor(null);
        assertThrows(WolfInvalidColorException.class,
                () -> wolfValidator.verifyColorIsNotNullOrBlank(wolf));
    }

    @Test
    void blankColorShouldThrowException() {
        wolf.setFurColor("");
        assertThrows(WolfInvalidColorException.class,
                () -> wolfValidator.verifyColorIsNotNullOrBlank(wolf));
    }

    @Test
    void validFurColorShouldBeAccepted() {
        wolf.setFurColor("Gray");
        wolfValidator.verifyColorIsNotNullOrBlank(wolf);
    }

    @Test
    void nullHowlKeyShouldThrowException() {
        wolf.setHowlKey(null);
        assertThrows(WolfInvalidHowlKeyException.class,
                () -> wolfValidator.verifyHowlKeyIsValid(wolf));
    }

    @Test
    void validHowlKeyShouldBeAccepted() {
        wolf.setHowlKey(Wolf.HowlKey.A);
        wolfValidator.verifyHowlKeyIsValid(wolf);
    }
}
