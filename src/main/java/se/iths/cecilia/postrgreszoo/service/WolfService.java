package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.exception.WolfNotFoundException;
import se.iths.cecilia.postrgreszoo.model.Wolf;
import se.iths.cecilia.postrgreszoo.repository.WolfRepository;
import se.iths.cecilia.postrgreszoo.validator.WolfValidator;

import java.util.List;

@Service
public class WolfService {
    private final WolfRepository wolfRepository;
    private final WolfValidator wolfValidator;

    public WolfService(WolfRepository wolfRepository, WolfValidator wolfValidator) {
        this.wolfRepository = wolfRepository;
        this.wolfValidator = wolfValidator;
    }

    public List<Wolf> getAllWolves() {
        return wolfRepository.findAll();
    }

    public Wolf createWolf(Wolf wolf) {
        return wolfRepository.save(wolf);
    }

    public Wolf getWolf(Long id) {
        return wolfRepository.findById(id)
                .orElseThrow(() -> new WolfNotFoundException("No wolf with id " + id + "  found."));
    }

    public Wolf updateWolf(Long id, Wolf wolf) {
        wolf.setId(id);
        return wolfRepository.save(wolf);
    }

    public void deleteWolf(Long id) {
        wolfRepository.deleteById(id);
    }
}
