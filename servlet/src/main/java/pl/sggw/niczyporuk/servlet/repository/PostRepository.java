package pl.sggw.niczyporuk.servlet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.sggw.niczyporuk.dto.Post;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByTitle(String title);
}
