package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
