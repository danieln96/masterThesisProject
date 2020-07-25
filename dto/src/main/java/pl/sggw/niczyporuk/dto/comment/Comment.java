package pl.sggw.niczyporuk.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String postId;
    private String signature;
    private String content;
    private LocalDateTime createDate;

}
