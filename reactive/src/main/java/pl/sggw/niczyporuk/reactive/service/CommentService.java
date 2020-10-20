package pl.sggw.niczyporuk.reactive.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.reactive.exception.ForbiddenCommentException;
import pl.sggw.niczyporuk.reactive.exception.PostNotFoundException;
import pl.sggw.niczyporuk.reactive.repository.CommentRepository;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import io.micrometer.core.annotation.Timed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {

    private static final List<String> forbiddenWords = Arrays.asList("first", "eleventh", "second", "twelfth", "third", "thirteenth", "fourth", "fourteenth", "fifth", "fifteenth", "sixth", "sixteenth", "seventh", "seventeenth", "eighth", "eighteenth", "ninth", "nineteenth", "tenth", "twentieth");

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Timed
    public Flux<Comment> getAllCommentsWithPostId(final String postId, final String orderBy) {

        if ("asc".equals(orderBy)) {
            return commentRepository.findAllByPostIdOrderByCreateDateAsc(postId);
        } else {
            return commentRepository.findAllByPostIdOrderByCreateDateDesc(postId);
        }
    }

    @Timed
    public Mono<ResponseEntity<Comment>> addCommentToPost(final Comment comment) {

        return postRepository
                .existsById(comment.getPostId())
                .flatMap(existsById ->
                        BooleanUtils.isFalse(existsById) ?
                                Mono.error(new PostNotFoundException(String.format("Post with id %s not found", comment.getPostId()))) : Mono.just(true)
                )
                .then(hasForbiddenWords(comment.getContent()))
                .flatMap(isForbiddenComment ->
                        isForbiddenComment ? Mono.error(new ForbiddenCommentException("Comment has forbidden words")) : Mono.just(false))
                .then(commentRepository.save(comment))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private Mono<Boolean> hasForbiddenWords(final String content) {

        return Mono.just(forbiddenWords)
                .flatMapMany(Flux::fromIterable)
                .any(content::contains);
    }
}
