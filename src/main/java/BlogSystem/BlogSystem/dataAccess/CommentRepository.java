package patikaOdev.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import patikaOdev.BlogSystem.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
