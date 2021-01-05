package ru.project.handwritten.saver;

import lombok.*;

import java.io.Serializable;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDOCX implements Serializable {
    private String path;
    private String fonts;
    private String fontSize;
    private String spaceNum;
    private String mistakePercent;
    private String yplotnenie;
}
