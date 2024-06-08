package arutala.backend.bookrecipe.repository;

import arutala.backend.bookrecipe.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
}
