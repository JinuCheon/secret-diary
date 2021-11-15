package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.algorithm.*;
import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.dto.DiaryInfo;
import gachon.ac.kr.secretdiary.repository.DiaryRepository;
import gachon.ac.kr.secretdiary.repository.MemoryDiaryRepository;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    DiaryRepository memoryDiaryRepository = new MemoryDiaryRepository();
    CompressionAlgorithm compressionAlgorithm = new FakeCompressAlgorithm();
    AesAlgorithm aesAlgorithm = new LibraryAesAlgorithm();

    @Override
    public List<Diary> diaryList() {
        return memoryDiaryRepository.findAll();
    }

    @Override
    public Object newDiary(NewDiaryForm newDiaryForm) throws NoSuchAlgorithmException {
        Diary diary = new Diary();
        diary.setName(newDiaryForm.getName());
        diary.setLengthOfOriginal(newDiaryForm.getText().length());

        try {
            //password hash.
            String tempHashedPassword = Hash256Algorithm.sha256(newDiaryForm.getPassword());
            System.out.println("hash : " + tempHashedPassword);

            //인코딩, 압축알고리즘 호출
            String incodedText = compressionAlgorithm.compression(diary, newDiaryForm.getText());

            diary.setCryptoText(aesAlgorithm.encryption(incodedText, tempHashedPassword)); //aes 암호화(텍스트, 해시)
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("AES 암호화 : " + diary.getCryptoText());
        memoryDiaryRepository.save(diary);
        return null;
    }

    @Override
    public DiaryInfo diaryInfo(Long id) {
        DiaryInfo diaryInfo = new DiaryInfo();
        Diary diary = memoryDiaryRepository.findById(id);

        diaryInfo.setCryptoText(diary.getCryptoText());
        diaryInfo.setLengthOfOriginal(diary.getLengthOfOriginal());
        diaryInfo.setLengthOfCompressed(diary.getLengthOfCompressed());
        return diaryInfo;
    }

    @Override
    public String decodeDinary(Long id, String password) {
        Diary diary = memoryDiaryRepository.findById(id);
        String result = "";
        //디코딩, 압축해제 거쳐야함
        try{
            String tempHashedPassword = Hash256Algorithm.sha256(password);
            System.out.println(tempHashedPassword);
            result = aesAlgorithm.decryption(diary.getCryptoText(), tempHashedPassword); //aes 암호화(텍스트, 해시)
            result = compressionAlgorithm.decompression(diary.getIncodHeader(), result); //압축해제

        } catch (Exception e){
            System.out.println(e);
        }

        return result;
    }
}
