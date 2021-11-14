package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;

import java.util.List;

public interface DiaryService {
    List<Diary> diaryList();
    Object newDiary(NewDiaryForm newDiaryForm);
    String diaryInfo(Long id);
    String decodeDinary(Long id, String password);
}
