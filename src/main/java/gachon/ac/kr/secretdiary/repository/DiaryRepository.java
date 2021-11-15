package gachon.ac.kr.secretdiary.repository;

import gachon.ac.kr.secretdiary.domain.Diary;

import java.util.List;

public interface DiaryRepository {
    Diary save(Diary diary);
    Diary findById(Long id);
    List<Diary> findAll();
    int getTotalLengthOfOriginal();
    int getTotalLengthOfCompressed();
}
