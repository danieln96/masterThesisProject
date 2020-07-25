package pl.sggw.niczyporuk.servlet.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    private static final List<String> forbiddenWords = Arrays.asList("first", "second", "third");

    public boolean hasForbiddenWords(final String content) {

        for (final String word : forbiddenWords) {
            if (content.contains(word)) {
                return true;
            }
        }

        return false;
    }
}
