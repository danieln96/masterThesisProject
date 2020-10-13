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
import pl.sggw.niczyporuk.reactive.service.CommentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}/{orderBy}")
    public Flux<Comment> getAllCommentsWithPostId(@PathVariable final String orderBy, @PathVariable final String postId) {

        return commentService.getAllCommentsWithPostId(postId, orderBy);
    }

    @PostMapping
    public Mono<ResponseEntity<Comment>> addComment(@RequestBody final Comment comment) {

        return commentService.addCommentToPost(comment);
    }
}
