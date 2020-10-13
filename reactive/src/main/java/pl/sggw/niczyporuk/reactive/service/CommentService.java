package pl.sggw.niczyporuk.reactive.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.reactive.exception.ForbiddenCommentException;
import pl.sggw.niczyporuk.reactive.exception.PostNotFoundException;
import pl.sggw.niczyporuk.reactive.repository.CommentRepository;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {

    private static final List<String> forbiddenWords = Arrays.asList("first", "second", "third");

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Flux<Comment> getAllCommentsWithPostId(final String postId, final String orderBy) {

        if ("asc".equals(orderBy)) {
            return commentRepository.findAllByPostIdOrderByCreateDateAsc(postId);
        } else {
            return commentRepository.findAllByPostIdOrderByCreateDateDesc(postId);
        }
    }

    public Mono<ResponseEntity<Comment>> addCommentToPost(final Comment comment) {

        return postRepository
                .existsById(comment.getPostId())
                .switchIfEmpty(Mono.error(new PostNotFoundException(String.format("Post with id %s not found", comment.getPostId()))))
                .then(hasForbiddenWords(comment.getContent()))
                .switchIfEmpty(Mono.error(new ForbiddenCommentException("Comment has forbidden words")))
                .then(commentRepository.save(comment))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private Mono<Boolean> hasForbiddenWords(final String content) {

        for (final String word : forbiddenWords) {
            if (content.contains(word)) {
                return Mono.just(true);
            }
        }

        return Mono.just(false);
    }
}
