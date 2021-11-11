package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.domain.Diary;

import java.util.List;

public interface DiaryService {
    List<Diary> diaryList();
    Object newDiary(Diary diary);
    String diaryInfo(Long id);
    String decodeDinary(Long id, String password);
}
