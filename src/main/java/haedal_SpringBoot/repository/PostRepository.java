package haedal_SpringBoot.repository;

import haedal_SpringBoot.domain.Post;
import haedal_SpringBoot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  Long countByUser(User user);
  List<Post> findByUser(User user);
}

