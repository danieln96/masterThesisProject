package pl.sggw.niczyporuk.servlet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.servlet.exception.ForbiddenCommentException;
import pl.sggw.niczyporuk.servlet.exception.PostNotFoundException;
import pl.sggw.niczyporuk.servlet.repository.CommentRepository;
import pl.sggw.niczyporuk.servlet.repository.PostRepository;
import pl.sggw.niczyporuk.servlet.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentRepository commentRepository,
                             final PostRepository postRepository,
                             final CommentService commentService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentService = commentService;
    }

    @GetMapping("/{postId}/{orderBy}")
    public ResponseEntity<List<Comment>> getAllCommentsWithPostId(@PathVariable final String orderBy, @PathVariable final String postId) {

        if (postRepository.existsById(postId)) {
            if ("asc".equals(orderBy)) {
                return new ResponseEntity<>(commentRepository.findAllByPostIdOrderByCreateDateAsc(postId), HttpStatus.OK);
            } else if ("desc".equals(orderBy)) {
                return new ResponseEntity<>(commentRepository.findAllByPostIdOrderByCreateDateDesc(postId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(commentRepository.findAllByPostId(postId), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Comment> addCommentToPost(@RequestBody final Comment comment) {

        if (comment != null) {
            if (postRepository.existsById(comment.getPostId())) {
                if (commentService.hasForbiddenWords(comment.getContent())) {
                    throw new ForbiddenCommentException("Comment has forbidden words");
                } else {
                    return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
                }
            } else {
                throw new PostNotFoundException(String.format("Post not found for comment with id: %s", comment.getPostId()));
            }
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

}
