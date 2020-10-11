package pl.sggw.niczyporuk.reactive.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pl.sggw.niczyporuk.dto.comment.Comment;
import pl.sggw.niczyporuk.dto.post.BodyPost;
import pl.sggw.niczyporuk.dto.post.Paragraph;
import pl.sggw.niczyporuk.dto.post.ParagraphType;
import pl.sggw.niczyporuk.dto.post.Post;
import pl.sggw.niczyporuk.reactive.repository.CommentRepository;
import pl.sggw.niczyporuk.reactive.repository.PostRepository;
import reactor.core.publisher.Flux;

@Component
public class InitDataService {

    private static final Faker faker = new Faker(new Locale("pl"));

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public InitDataService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void prepareData() {

        postRepository
                .deleteAll()
                .then(commentRepository.deleteAll())
                .thenMany(Flux.just(generatePosts()))
                .flatMap(postRepository::saveAll)
                .thenMany(postRepository.findAll())
                .map(this::generateComments)
                .flatMap(commentRepository::saveAll)
                .subscribe();
    }

    private List<Post> generatePosts() {
        List<Post> posts = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            posts.add(Post.builder()
                    .lead(faker.lorem().characters(100, 256))
                    .photoUrl(faker.internet().url())
                    .createDate(faker.date().past(800, 365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .signature(faker.cat().name())
                    .title(faker.book().title())
                    .bodyPost(new BodyPost(generateParagraphs()))
                    .build());
        }

        return posts;
    }

    private List<Paragraph> generateParagraphs() {
        List<Paragraph> paragraphs = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            paragraphs.add(Paragraph.builder()
                    .paragraphType(ParagraphType.text)
                    .content(faker.pokemon().name())
                    .build());
        }

        return paragraphs;
    }

    private List<Comment> generateComments(Post post) {
        List<Comment> comments = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            comments.add(Comment.builder()
                    .postId(post.getId())
                    .signature(faker.animal().name())
                    .content(faker.harryPotter().quote())
                    .createDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .build());
        }

        return comments;
    }
}
