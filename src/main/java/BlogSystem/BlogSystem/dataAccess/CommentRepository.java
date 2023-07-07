package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
