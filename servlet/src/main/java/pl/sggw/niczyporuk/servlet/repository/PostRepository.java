package pl.sggw.niczyporuk.servlet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.sggw.niczyporuk.dto.Post;

public interface PostRepository extends MongoRepository<Post, String> {
}
