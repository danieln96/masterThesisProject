package pl.sggw.niczyporuk.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    public PostController(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/id/{postId}")
    public Mono<Post> findById(@PathVariable final String postId) {
        return postRepository.findById(postId);
    }

    @GetMapping("/title/{title}")
    public Flux<Post> findByTitle(@PathVariable final String title) {
        return postRepository.findAllByTitle(title);
    }

    @GetMapping
    public Flux<Post> findAll() {
        return postRepository.findAll();
    }

    @PostMapping
    public Mono<Post> createNewPost(@RequestBody final Post post) {
        return post != null ? postRepository.save(post) : null;
    }
}
