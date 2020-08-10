package pl.sggw.niczyporuk.servlet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.servlet.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<Post> findById(@PathVariable final String postId) {

        return postService.findById(postId);
    }

    @GetMapping("/title/{title}")
    public List<Post> findByTitle(@PathVariable final String title) {

        return postService.findAllByTitle(title);
    }

    @GetMapping
    public List<Post> findAll() {

        return postService.findAll();
    }

    @GetMapping("/page")
    public Page<Post> findAllByPage(final Pageable pageable) {

        return postService.findPaginated(pageable);
    }

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody final Post post) {

        return postService.savePost(post);
    }
}
