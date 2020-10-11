package pl.sggw.niczyporuk.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String id;

    private String title;
    private String lead;
    private String signature;
    private String photoUrl;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private BodyPost bodyPost;
}
