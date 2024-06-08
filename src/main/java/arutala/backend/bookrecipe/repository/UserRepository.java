package arutala.backend.bookrecipe.repository;

import arutala.backend.bookrecipe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
