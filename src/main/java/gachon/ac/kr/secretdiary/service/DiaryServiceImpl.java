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
        //인코딩, 압축알고리즘 호출
        return null;
    }

    @Override
    public String diaryInfo(Long id) {
        Diary diary = memoryDiaryRepository.findById(id);
        return diary.getCryptoText();
    }

    @Override
    public String decodeDinary(Long id, String password) {
        Diary diary = memoryDiaryRepository.findById(id);
        //디코딩, 압축해제 거쳐야함
        return diary.getCryptoText();
    }
}
