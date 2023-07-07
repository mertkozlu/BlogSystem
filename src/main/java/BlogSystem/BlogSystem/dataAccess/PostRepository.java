package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
