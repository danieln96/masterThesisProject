package pl.sggw.niczyporuk.dto;

import lombok.Data;

@Data
public class Paragraph {

    private ParagraphType paragraphType;
    private String content;
}