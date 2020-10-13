package pl.sggw.niczyporuk.servlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.servlet.exception.ForbiddenCommentException;
import pl.sggw.niczyporuk.servlet.exception.PostNotFoundException;
import pl.sggw.niczyporuk.servlet.repository.CommentRepository;
import pl.sggw.niczyporuk.servlet.repository.PostRepository;

@Service
public class CommentService {

    private static final List<String> forbiddenWords = Arrays.asList("first", "second", "third");

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(final PostRepository postRepository, final CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public ResponseEntity<Comment> addCommentToPost(final Comment comment) {
        if (comment != null) {
            if (postRepository.existsById(comment.getPostId())) {
                if (hasForbiddenWords(comment.getContent())) {
                    throw new ForbiddenCommentException("Comment has forbidden words");
                } else {
                    return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
                }
            } else {
                throw new PostNotFoundException(String.format("Post with id %s not found", comment.getPostId()));
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Comment>> getAllCommentsWithPostId(final String postId, final String orderBy) {
        if (postRepository.existsById(postId)) {
            if ("asc".equals(orderBy)) {
                return new ResponseEntity<>(commentRepository.findAllByPostIdOrderByCreateDateAsc(postId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(commentRepository.findAllByPostIdOrderByCreateDateDesc(postId), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private boolean hasForbiddenWords(final String content) {

        for (final String word : forbiddenWords) {
            if (content.contains(word)) {
                return true;
            }
        }

        return false;
    }
}
