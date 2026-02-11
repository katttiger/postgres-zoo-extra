package se.iths.cecilia.postrgreszoo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.cecilia.postrgreszoo.exception.*;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;
import se.iths.cecilia.postrgreszoo.validator.MonkeyValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MonkeyServiceTest {

    @Mock
    MonkeyRepository monkeyRepository;

    @Mock
    MonkeyValidator monkeyValidator;

    @InjectMocks
    MonkeyService monkeyService;

    List<Monkey> monkeys;

    Monkey monkey;


    @BeforeEach
    void setUp() {
        monkeyService = new MonkeyService(monkeyRepository, monkeyValidator);

        monkeys = new ArrayList<>();
        monkey = new Monkey();

        monkey.setId(1L);
        monkey.setName("John");
        monkey.setType("Lemur");
        monkey.setOriginCountry("Country4");
        monkey.setCurrentHabitat("C66");
        monkeys.add(monkey);

        monkeys.add(new Monkey(2L, "Test2", "Gorilla", "Country2", "T22"));
    }

    @Test
    @DisplayName("All monkeys in database are returned")
    void getAllMonkeys() {
        Mockito.when(monkeyService.getAllMonkeys()).thenReturn(monkeys);
        List<Monkey> monkeysReturned = monkeyService.getAllMonkeys();
        Assertions.assertEquals(monkeys, monkeysReturned);
    }

    @Test
    @DisplayName("Assert that the method findAll() is called")
    void findAllIsCalled() {
        monkeyService.getAllMonkeys();
        Mockito.verify(monkeyRepository).findAll();
    }

    @Test
    @DisplayName("Assert that the monkey returned is not null")
    void getMonkeyDoesNotReturnNull() {
        Mockito.when(monkeyService.getAllMonkeys()).thenReturn(monkeys);
        List<Monkey> monkeysReturned = monkeyService.getAllMonkeys();
        Assertions.assertNotNull(monkeysReturned);
    }

    @Test
    @DisplayName("Assert that the monkey returned is the one expected")
    void getMonkeyReturnsTheOneExpected() {
        //TODO: Make sure that a direct call to monkeyRepository is not needed to run the test
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        Monkey monkeyReturned = monkeyService.getMonkey(1L);
        Assertions.assertEquals(monkey, monkeyReturned);
    }

    @Test
    @DisplayName("Throws MonkeyNotFoundException if monkey does not exist")
    void getMonkeyThrowsMonkeyNotFoundExceptionIfMonkeyDoesNotExist() {
        Mockito.when(monkeyService.getMonkey(3L)).thenThrow(MonkeyNotFoundException.class);
        Assertions.assertThrows(MonkeyNotFoundException.class, () -> monkeyService.getMonkey(3L));
    }


    @Test
    @DisplayName("save-method is called when we create a monkey")
    void createMonkey() {
        monkeyService.createMonkey(monkey);
        Mockito.verify(monkeyRepository).save(monkey);
    }

    @Test
    @DisplayName("Throws exception if origin country is emptu")
    void createMonkeyThrowsExceptionIfNoOriginCountryIsGiven() {
        monkey.setOriginCountry("");
        Mockito.when(monkeyService.createMonkey(monkey)).thenThrow(MonkeyNotFoundException.class);
        Assertions.assertThrows(MonkeyNotFoundException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no name")
    void createMonkeyThrowsExceptionIfMonkeyHasNoName() {
        monkey.setName("");
        Mockito.when(monkeyService.createMonkey(monkey)).thenThrow(MonkeyNameIsEmptyException.class);
        Assertions.assertThrows(MonkeyNameIsEmptyException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no type")
    void createMonkeyThrowsExceptionIfMonkeyHasNoType() {
        monkey.setType("");
        Mockito.when(monkeyService.createMonkey(monkey)).thenThrow(MonkeyHasNoTypeException.class);
        Assertions.assertThrows(MonkeyHasNoTypeException.class, () -> monkeyService.createMonkey(monkey));

    }

    @Test
    @DisplayName("Throws exception if monkey name is null")
    void createMonkeyThrowsExceptionIfMonkeyNameIsNull() {
        monkey.setName(null);
        Mockito.when(monkeyService.createMonkey(monkey)).thenThrow(MonkeyNameIsNullException.class);
        Assertions.assertThrows(MonkeyNameIsNullException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no habitat")
    void createMonkeyThrowsExceptionIfMonkeyHasNoHabitat() {
        monkey.setCurrentHabitat("");
        Mockito.when(monkeyService.createMonkey(monkey)).thenThrow(MonkeyHasNoHabitatException.class);
        Assertions.assertThrows(MonkeyHasNoHabitatException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Monkey properties can be updated")
    void updateMonkey() {

        //TODO: Make sure that a direct call to monkeyRepository is not needed to run the test
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        String oldHabitat = monkey.getCurrentHabitat();
        monkey.setCurrentHabitat("R77");
        Mockito.when(monkeyService.updateMonkey(1L, monkey)).thenReturn(monkey);
        monkeyService.updateMonkey(1L, monkey);
        Assertions.assertNotEquals(oldHabitat, monkey.getCurrentHabitat());

    }

    @Test
    @DisplayName("Verify deleteById-method is called")
    void deleteMonkey() {

        //TODO: Make sure that a direct call to monkeyRepository is not needed to run the test
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        monkeyService.deleteMonkey(1L);
        Mockito.verify(monkeyRepository).deleteById(1L);


    }
}