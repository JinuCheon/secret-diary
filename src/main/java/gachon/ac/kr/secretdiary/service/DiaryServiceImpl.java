package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.algorithm.AesAlgorithm;
import gachon.ac.kr.secretdiary.algorithm.CompressionAlgorithm;
import gachon.ac.kr.secretdiary.algorithm.FakeAesAlgorithm;
import gachon.ac.kr.secretdiary.algorithm.FakeCompressAlgorithm;
import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.repository.DiaryRepository;
import gachon.ac.kr.secretdiary.repository.MemoryDiaryRepository;

import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    DiaryRepository memoryDiaryRepository = new MemoryDiaryRepository();
    CompressionAlgorithm compressionAlgorithm = new FakeCompressAlgorithm();
    AesAlgorithm aesAlgorithm = new FakeAesAlgorithm();

    @Override
    public List<Diary> diaryList() {
        return memoryDiaryRepository.findAll();
    }

    @Override
    public Object newDiary(NewDiaryForm newDiaryForm) {
        Diary diary = new Diary();
        diary.setName(newDiaryForm.getName());

        //인코딩, 압축알고리즘 호출
        compressionAlgorithm.compression(diary, newDiaryForm.getText());
        diary.setCryptoText(aesAlgorithm.encryption(diary.getIncodedText(), newDiaryForm.getPassword())); //aes 암호화

        memoryDiaryRepository.save(diary);
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
