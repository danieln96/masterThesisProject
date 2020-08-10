package pl.sggw.niczyporuk.reactive.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pl.sggw.niczyporuk.dto.post.Post;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    Flux<Post> findAllByTitle(String title);

    @Query("{ id: { $exists: true }}")
    Flux<Post> retrieveAllPosts(Pageable page);
}
