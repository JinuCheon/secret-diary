package gachon.ac.kr.secretdiary.dto;

import gachon.ac.kr.secretdiary.domain.Diary;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewDiary {
    private List<Diary> diaryList;
    private int totalTextOriginal;
    private int totalTextCompress;
}
