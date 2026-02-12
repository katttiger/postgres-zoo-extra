package se.iths.cecilia.postrgreszoo.service;

import org.springframework.stereotype.Service;
import se.iths.cecilia.postrgreszoo.model.Puma;
import se.iths.cecilia.postrgreszoo.repository.PumaRepository;
import se.iths.cecilia.postrgreszoo.validator.PumaValidator;

import java.util.List;

@Service
public class PumaService {

    private final PumaRepository repository;
    private final PumaValidator validator;

    public PumaService(PumaRepository repository, PumaValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Puma> getAll() {
        return repository.findAll();
    }

    public Puma getOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puma not found"));
    }

    public Puma create(Puma puma) {
        validator.validate(puma);
        return repository.save(puma);
    }

    public Puma update(Long id, Puma puma) {
        validator.validate(puma);

        Puma existing = getOne(id);

        existing.setName(puma.getName());
        existing.setAge(puma.getAge());
        existing.setWeight(puma.getWeight());
        existing.setDangerous(puma.isDangerous());

        return repository.save(existing);
    }

    public void delete(Long id) {
        getOne(id);
        repository.deleteById(id);
    }
}
