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
import se.iths.cecilia.postrgreszoo.exception.monkeyexceptions.*;
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
        Mockito.when(monkeyRepository.findAll()).thenReturn(monkeys);
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
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        Monkey monkeyReturned = monkeyService.getMonkey(1L);
        Assertions.assertEquals(monkey, monkeyReturned);
    }

    @Test
    @DisplayName("Throws MonkeyNotFoundException if monkey does not exist")
    void getMonkeyThrowsMonkeyNotFoundExceptionIfMonkeyDoesNotExist() {
        Mockito.when(monkeyRepository.findById(3L)).thenReturn(Optional.empty());
        Assertions.assertThrows(MonkeyNotFoundException.class, () -> monkeyService.getMonkey(3L));
    }


    @Test
    @DisplayName("save-method is called when we create a monkey")
    void createMonkey() {
        monkeyService.createMonkey(monkey);
        Mockito.verify(monkeyRepository).save(monkey);
    }

    @Test
    @DisplayName("Throws exception if origin country is empty")
    void createMonkeyThrowsExceptionIfNoOriginCountryIsGiven() {
        monkey.setOriginCountry("");
        Mockito.doThrow(MonkeyHasNoCountryOfOriginException.class).when(monkeyValidator).verifyThatMonkeyHasOriginCountry(monkey);
        Assertions.assertThrows(MonkeyHasNoCountryOfOriginException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no name")
    void createMonkeyThrowsExceptionIfMonkeyHasNoName() {
        monkey.setName("");
        Mockito.doThrow(MonkeyNameIsEmptyException.class).when(monkeyValidator).verifyThatNameIsNotEmpty(monkey);
        Assertions.assertThrows(MonkeyNameIsEmptyException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey name is null")
    void createMonkeyThrowsExceptionIfMonkeyNameIsNull() {
        monkey.setName(null);
        Mockito.doThrow(MonkeyNameIsNullException.class).when(monkeyValidator).verifyThatNameIsNotNull(monkey);
        Assertions.assertThrows(MonkeyNameIsNullException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no type")
    void createMonkeyThrowsExceptionIfMonkeyHasNoType() {
        monkey.setType("");
        Mockito.doThrow(MonkeyHasNoTypeException.class).when(monkeyValidator).verifyThatMonkeyHasType(monkey);
        Assertions.assertThrows(MonkeyHasNoTypeException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Throws exception if monkey has no habitat")
    void createMonkeyThrowsExceptionIfMonkeyHasNoHabitat() {
        monkey.setCurrentHabitat("");
        Mockito.doThrow(MonkeyHasNoHabitatException.class).when(monkeyValidator).verifyThatMonkeyHasHabitat(monkey);
        Assertions.assertThrows(MonkeyHasNoHabitatException.class, () -> monkeyService.createMonkey(monkey));
    }

    @Test
    @DisplayName("Monkey properties can be updated")
    void updateMonkey() {
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        Mockito.when(monkeyRepository.save(monkey)).thenReturn(monkey);

        monkey.setCurrentHabitat("R77");
        Monkey updatedMonkey = monkeyService.updateMonkey(1L, monkey);

        Assertions.assertEquals("R77", updatedMonkey.getCurrentHabitat());

    }

    @Test
    @DisplayName("Delete-method is called in repository")
    void deleteMethodIsCalledInRepository() {
        Mockito.when(monkeyRepository.findById(1L)).thenReturn(Optional.of(monkey));
        monkeyService.deleteMonkey(1L);
        Mockito.verify(monkeyRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deleteMonkey thows MonkeyNotFoundException if monkey does not exist")
    void deleteMonkeyThrowsMonkeyNotFoundExceptionIfMonkeyDoesNotExist() {
        Mockito.when(monkeyRepository.findById(3L)).thenReturn(Optional.empty());
        Assertions.assertThrows(MonkeyNotFoundException.class, () -> monkeyService.deleteMonkey(3L));
    }
}