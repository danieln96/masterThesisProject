package pl.sggw.niczyporuk.servlet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.sggw.niczyporuk.dto.comment.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPostId(String postId);

    List<Comment> findAllByPostIdOrderByCreateDateAsc(String postId);

    List<Comment> findAllByPostIdOrderByCreateDateDesc(String postId);
}
