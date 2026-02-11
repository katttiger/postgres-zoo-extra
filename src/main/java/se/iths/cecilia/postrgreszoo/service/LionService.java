package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.exception.lionExceptions.LionNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Lion;
import se.iths.cecilia.postrgreszoo.repository.LionRepository;
import se.iths.cecilia.postrgreszoo.validator.LionValidator;

import java.util.List;

@Service
public class LionService {

    private final LionRepository lionRepository;
    private final LionValidator lionValidator;

    public LionService(LionRepository lionRepository, LionValidator lionValidator) {
        this.lionRepository = lionRepository;
        this.lionValidator = lionValidator;
    }

    public Lion createLion(Lion lion) {

        lionValidator.validate(lion);

        return lionRepository.save(lion);
    }

    public Lion getLionById(Long id) {
        return lionRepository.findById(id).orElseThrow(() ->
                new LionNotFoundException("No lion found with id: " + id));
    }

    public List<Lion> getAllLions() {
        return lionRepository.findAll();
    }

    public Lion updateLion(Long id, Lion updatedLion) {
        Lion savedLion = lionRepository.findById(id).orElseThrow(() ->
                new LionNotFoundException("No lion found with id: " + id));

        lionValidator.validateUpdatedLion(updatedLion);

        savedLion.setAge(updatedLion.getAge());
        savedLion.setWeight(updatedLion.getWeight());
        savedLion.setKills(updatedLion.getKills());

        return lionRepository.save(savedLion);
    }

    public void deleteLion(Long id) {
        if (!lionRepository.existsById(id)) {
            throw new LionNotFoundException("No lion found with id: " + id);
        }
        lionRepository.deleteById(id);
    }
}
