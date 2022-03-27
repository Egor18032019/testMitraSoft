package service.user.micro.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service.user.micro.store.entities.UserEntitty;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntitty, Long> {
  Optional<UserEntitty> findByUsername (String username);

}
