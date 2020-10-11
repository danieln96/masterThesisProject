package pl.sggw.niczyporuk.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paragraph {

    private ParagraphType paragraphType;
    private String content;
}
