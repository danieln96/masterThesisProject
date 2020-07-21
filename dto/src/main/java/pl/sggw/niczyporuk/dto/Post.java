package pl.sggw.niczyporuk.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
