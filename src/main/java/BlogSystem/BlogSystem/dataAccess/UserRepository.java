package patikaOdev.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import patikaOdev.BlogSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
