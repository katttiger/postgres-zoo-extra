package se.iths.cecilia.postrgreszoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.cecilia.postrgreszoo.model.Puma;

public interface PumaRepository extends JpaRepository<Puma, Long> {
}
