package pl.sggw.niczyporuk.reactive.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.reactive.service.PostService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/id/{postId}")
    public Mono<ResponseEntity<Post>> findById(@PathVariable final String postId) {
        return postService.findById(postId);
    }

    @GetMapping("/title/{title}")
    public Flux<Post> findByTitle(@PathVariable final String title) {

        return postService.findAllByTitle(title);
    }

    @GetMapping
    public Flux<Post> findAll() {

        return postService.findAll();
    }

    @GetMapping("/page")
    public Flux<Post> findPageById(@RequestParam("page") int pageIndex,
                                   @RequestParam("size") int pageSize) {

        return postService.findPaginated(PageRequest.of(pageIndex, pageSize));
    }

    @PostMapping
    public Mono<ResponseEntity<Post>> createNewPost(@RequestBody final Post post) {

        return postService.savePost(post);
    }
}
