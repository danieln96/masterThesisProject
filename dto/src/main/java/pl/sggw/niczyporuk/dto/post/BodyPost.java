package pl.sggw.niczyporuk.dto.post;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyPost {

    private List<Paragraph> paragraphs;

}
