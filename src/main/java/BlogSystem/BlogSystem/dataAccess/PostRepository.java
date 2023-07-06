package patikaOdev.BlogSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import patikaOdev.BlogSystem.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
