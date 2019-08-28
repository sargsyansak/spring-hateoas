package am.itspace.springhateoas.repository;

import am.itspace.springhateoas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
