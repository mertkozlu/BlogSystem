package BlogSystem.BlogSystem.dataAccess;

import BlogSystem.BlogSystem.dto.GetAllPostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
