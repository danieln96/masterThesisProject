package pl.sggw.niczyporuk.servlet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.servlet.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> findPaginated(final Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    public List<Post> findAllByTitle(final String title) {
        return postRepository.findAllByTitle(title);
    }

    public List<Post> findAll() {
        return  postRepository.findAll();
    }

    public ResponseEntity<Post> findById(final String postId) {
        return postRepository.findById(postId)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    public ResponseEntity<Post> savePost(final Post post) {
        return post != null ?
                new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
