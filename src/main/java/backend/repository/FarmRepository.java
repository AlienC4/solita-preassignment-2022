package backend.repository;

import backend.domain.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}