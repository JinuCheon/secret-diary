package gachon.ac.kr.secretdiary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryInfo {
    private String cryptoText;
    private int lengthOfOriginal;
    private int lengthOfCompressed;
}
