package BlogSystem.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import BlogSystem.BlogSystem.entities.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser_UserId(Long userId);
}
