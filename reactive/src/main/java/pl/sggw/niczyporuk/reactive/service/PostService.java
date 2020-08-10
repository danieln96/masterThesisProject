package pl.sggw.niczyporuk.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<Post> findPaginated(final Pageable pageable) {

        return postRepository.retrieveAllPosts(pageable);
    }

    public Flux<Post> findAllByTitle(final String title) {

        return postRepository.findAllByTitle(title);
    }

    public Flux<Post> findAll() {

        return postRepository.findAll();
    }

    public Mono<ResponseEntity<Post>> findById(final String postId) {

        return postRepository.findById(postId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Post>> savePost(final Post post) {

        return postRepository.save(post)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
