package pl.sggw.niczyporuk.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pl.sggw.niczyporuk.dto.comment.Comment;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Flux<Comment> findAllByPostId(String postId);

    Flux<Comment> findAllByPostIdOrderByCreateDateAsc(String postId);

    Flux<Comment> findAllByPostIdOrderByCreateDateDesc(String postId);
}
