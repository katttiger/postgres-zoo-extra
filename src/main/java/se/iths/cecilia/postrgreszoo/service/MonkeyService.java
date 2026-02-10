package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.exception.MonkeyNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Monkey;
import se.iths.cecilia.postrgreszoo.repository.MonkeyRepository;

import java.util.List;

@Service
public class MonkeyService {

    private MonkeyRepository monkeyRepository;

    private MonkeyValidator monkeyValidator;

    public MonkeyService(MonkeyRepository monkeyRepository) {
        this.monkeyRepository = monkeyRepository;
    }

    public List<Monkey> getMonkeys(){
        return monkeyRepository.findAll();
    }

    public Monkey getMonkey(long id) {
        return monkeyRepository.findById(id)
                .orElseThrow(()-> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
    }

    public Monkey createMonkey(Monkey monkey) {
        return monkeyRepository.save(monkey);
    }

    public Monkey updateMonkey(long id, Monkey monkey) {
        Monkey currentMonkey = monkeyRepository.findById(id).orElseThrow(()-> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
        monkey.setId(id);
        currentMonkey.setName(monkey.getName());
        currentMonkey.setCurrentHabitat(monkey.getCurrentHabitat());
        currentMonkey.setType(monkey.getType());
        currentMonkey.setOriginCountry(monkey.getOriginCountry());
        return monkeyRepository.save(currentMonkey);
    }

    public void deleteMonkey(long id) {
        monkeyRepository.findById(id).orElseThrow(()-> new MonkeyNotFoundException("Monkey with id " + id + " not found"));
        monkeyRepository.deleteById(id);
    }



}
