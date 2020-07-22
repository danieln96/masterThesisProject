package pl.sggw.niczyporuk.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pl.sggw.niczyporuk.dto.Post;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findAllByTitle(String title);
}
