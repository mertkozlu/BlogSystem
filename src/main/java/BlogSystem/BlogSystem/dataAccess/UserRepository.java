package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
