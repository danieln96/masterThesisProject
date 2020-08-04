package pl.sggw.niczyporuk.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.reactive.exception.ForbiddenCommentException;
import pl.sggw.niczyporuk.reactive.exception.PostNotFoundException;
import pl.sggw.niczyporuk.reactive.repository.CommentRepository;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import pl.sggw.niczyporuk.reactive.service.CommentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final CommentService commentService;

    @Autowired
    public CommentController(PostRepository postRepository, CommentRepository commentRepository, CommentService commentService) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @GetMapping("/{postId}/{orderBy}")
    public Flux<Comment> getAllCommentsWithPostId(@PathVariable final String orderBy, @PathVariable final String postId) {

        if ("asc".equals(orderBy)) {
            return commentRepository.findAllByPostIdOrderByCreateDateAsc(postId);
        } else if ("desc".equals(orderBy)) {
            return commentRepository.findAllByPostIdOrderByCreateDateDesc(postId);
        } else {
            return commentRepository.findAllByPostId(postId);
        }

    }

    @PostMapping
    public Mono<ResponseEntity<Comment>> addComment(@RequestBody final Comment comment) {

        return postRepository
                .existsById(comment.getPostId())
                .switchIfEmpty(Mono.error(new PostNotFoundException(String.format("Post not found for comment with id: %s", comment.getPostId()))))
                .then(commentService.hasForbiddenWords(comment.getContent()))
                .switchIfEmpty(Mono.error(new ForbiddenCommentException("Comment has forbidden words")))
                .then(commentRepository.save(comment))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
