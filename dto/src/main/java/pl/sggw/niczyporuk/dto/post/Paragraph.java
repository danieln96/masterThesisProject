package pl.sggw.niczyporuk.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paragraph {

    private ParagraphType paragraphType;
    private String content;
}
