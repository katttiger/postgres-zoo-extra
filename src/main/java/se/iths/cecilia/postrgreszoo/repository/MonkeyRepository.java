package se.iths.cecilia.postrgreszoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.cecilia.postrgreszoo.model.Monkey;

@Repository
public interface MonkeyRepository extends JpaRepository<Monkey, Long> {
}
