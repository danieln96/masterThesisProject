package pl.sggw.niczyporuk.servlet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.sggw.niczyporuk.dto.comment.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPostId(String postId);

    List<Comment> findAllByPostIdOrderByCreateDateAsc(String postId);

    List<Comment> findAllByPostIdOrderByCreateDateDesc(String postId);
}
