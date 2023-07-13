package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select count (*) from users", nativeQuery = true)
    Integer countUser();

}
