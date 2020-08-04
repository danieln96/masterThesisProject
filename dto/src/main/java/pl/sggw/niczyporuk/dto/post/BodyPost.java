package pl.sggw.niczyporuk.dto.post;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BodyPost {

    private List<Paragraph> paragraphs;

}
