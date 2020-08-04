package pl.sggw.niczyporuk.servlet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.sggw.niczyporuk.dto.post.Post;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByTitle(String title);
}
