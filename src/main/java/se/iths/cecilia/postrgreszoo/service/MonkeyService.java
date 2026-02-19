package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.exception.MonkeyNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;
import se.iths.cecilia.postrgreszoo.validator.MonkeyValidator;

import java.util.List;

@Service
public class MonkeyService {

    private final MonkeyRepository monkeyRepository;
    private final MonkeyValidator monkeyValidator;

    public MonkeyService(MonkeyRepository monkeyRepository, MonkeyValidator monkeyValidator) {
        this.monkeyRepository = monkeyRepository;
        this.monkeyValidator = monkeyValidator;
    }

    public List<Monkey> getAllMonkeys() {
        return monkeyRepository.findAll();
    }

    public Monkey getMonkey(long id) {
        return monkeyRepository.findById(id)
                .orElseThrow(() -> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
    }

    public Monkey createMonkey(Monkey monkey) {
        verifyMonkey(monkey);
        return monkeyRepository.save(monkey);
    }

    public Monkey updateMonkey(long id, Monkey monkey) {
        monkeyRepository.findById(id).orElseThrow(
                () -> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
        verifyMonkey(monkey);
        monkey.setId(id);
        return monkeyRepository.save(monkey);
    }

    public void deleteMonkey(long id) {
        monkeyRepository.findById(id).orElseThrow(() -> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
        monkeyRepository.deleteById(id);
    }

    private void verifyMonkey(Monkey monkey) {
        monkeyValidator.verifyThatNameIsNotEmpty(monkey);
        monkeyValidator.verifyThatNameIsNotNull(monkey);
        monkeyValidator.verifyThatMonkeyHasHabitat(monkey);
        monkeyValidator.verifyThatMonkeyHasType(monkey);
        monkeyValidator.verifyThatMonkeyHasOriginCountry(monkey);
    }
}
