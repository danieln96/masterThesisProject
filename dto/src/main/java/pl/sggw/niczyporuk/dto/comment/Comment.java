package pl.sggw.niczyporuk.dto.comment;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String id;

    private String postId;
    private String signature;
    private String content;
    private LocalDateTime createDate;

}
