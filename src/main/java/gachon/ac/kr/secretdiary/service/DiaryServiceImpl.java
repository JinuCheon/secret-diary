package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.repository.DiaryRepository;
import gachon.ac.kr.secretdiary.repository.MemoryDiaryRepository;

import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    DiaryRepository memoryDiaryRepository = new MemoryDiaryRepository();

    @Override
    public List<Diary> diaryList() {
        return memoryDiaryRepository.findAll();
    }

    @Override
    public Object newDiary(Diary diary) {
        memoryDiaryRepository.save(diary);
        return null;
    }

    @Override
    public Diary diaryInfo() {
        return null;
    }

    @Override
    public String decodeDinary() {
        return null;
    }
}
