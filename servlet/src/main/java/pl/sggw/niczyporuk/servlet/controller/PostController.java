package pl.sggw.niczyporuk.servlet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sggw.niczyporuk.dto.Post;
import pl.sggw.niczyporuk.servlet.repository.PostRepository;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<Post> findById(@PathVariable final String postId) {

        return postRepository.findById(postId)
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @GetMapping("/title/{title}")
    public List<Post> findByTitle(@PathVariable final String title) {
        return postRepository.findAllByTitle(title);
    }

    @GetMapping
    public List<Post> findAll() {

        return postRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody final Post post) {
        return post != null ?
                new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
