package pl.sggw.niczyporuk.reactive.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CommentService {

    private static final List<String> forbiddenWords = Arrays.asList("first", "second", "third");

    public Mono<Boolean> hasForbiddenWords(final String content) {

        for (final String word : forbiddenWords) {
            if (content.contains(word)) {
                return Mono.just(true);
            }
        }

        return Mono.just(false);
    }
}
