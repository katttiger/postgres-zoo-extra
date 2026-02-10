package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.exception.LionNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Lion;
import se.iths.cecilia.postrgreszoo.repository.LionRepository;

import java.util.List;

@Service
public class LionService {

    private final LionRepository lionRepository;

    public LionService(LionRepository lionRepository) {
        this.lionRepository = lionRepository;
    }

    // Create
    public Lion createLion(Lion lion) {
        return lionRepository.save(lion);
    }

    // Get by ID
    public Lion getLionById(Long id) {
        return lionRepository.findById(id).orElseThrow(() ->
                new LionNotFoundException("No lion found with id: " + id));
    }

    // Get all
    public List<Lion> getAllLions() {
        return lionRepository.findAll();
    }

    // Update
    public Lion updateLion(Long id, Lion lion) {
        Lion savedLion = lionRepository.findById(id).orElseThrow(() ->
                new LionNotFoundException("No lion found with id: " + id));
        lion.setId(id);
        return lionRepository.save(savedLion);
    }

    // Delete
    public void deleteLion(Long id) {
        if (!lionRepository.existsById(id)) {
            throw new LionNotFoundException("No lion found with id: " + id);
        }
        lionRepository.deleteById(id);
    }
}
