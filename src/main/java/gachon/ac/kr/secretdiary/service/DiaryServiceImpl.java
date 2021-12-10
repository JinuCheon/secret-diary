package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.algorithm.*;
import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.dto.DiaryInfo;
import gachon.ac.kr.secretdiary.dto.NewDiary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;
import gachon.ac.kr.secretdiary.repository.DiaryRepository;
import gachon.ac.kr.secretdiary.repository.MemoryDiaryRepository;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    //핵심 비즈니스 로직의 전체적인 그림이 담겨있습니다.

    DiaryRepository memoryDiaryRepository = new MemoryDiaryRepository();
    CompressionAlgorithm compressionAlgorithm = new HuffmanCompressionAlgorithm();
    AesAlgorithm aesAlgorithm = new LibraryAesAlgorithm();

    @Override
    public NewDiary diaryList() {
        //모든 다이어리 리스트를 불러옵니다.
        NewDiary newDiary = new NewDiary();
        newDiary.setDiaryList(memoryDiaryRepository.findAll());
        newDiary.setTotalTextOriginal(memoryDiaryRepository.getTotalLengthOfOriginal());
        newDiary.setTotalTextCompress(memoryDiaryRepository.getTotalLengthOfCompressed());
        return newDiary;
    }

    @Override
    public Object newDiary(NewDiaryForm newDiaryForm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //새로운 다이어리 생성
        Diary diary = new Diary();
        try {
            //password hash.
            String tempHashedPassword = Hash256Algorithm.sha256(newDiaryForm.getPassword());
            //인코딩, 압축알고리즘 호출
            ReturnCompressResult returnCompressResult = compressionAlgorithm.compression(newDiaryForm.getText());
            diary.setLengthOfCompressed(returnCompressResult.getResult().length());
            diary.setIncodHeader(returnCompressResult.getHeader());


            diary.setCryptoText(aesAlgorithm.encryption(returnCompressResult.getResult(), tempHashedPassword)); //aes 암호화(텍스트, 해시)
        } catch (Exception e){
            System.out.println(e);
        }

        diary.setName(newDiaryForm.getName());
        diary.setLengthOfOriginal(newDiaryForm.getText().getBytes("euc-kr").length * 8); //1byte = 8bit
        memoryDiaryRepository.save(diary);
        return null;
    }

    @Override
    public DiaryInfo diaryInfo(Long id) {
        //선택된 다이어리의 정보 호출
        DiaryInfo diaryInfo = new DiaryInfo();
        Diary diary = memoryDiaryRepository.findById(id);

        diaryInfo.setCryptoText(diary.getCryptoText());
        diaryInfo.setLengthOfOriginal(diary.getLengthOfOriginal());
        diaryInfo.setLengthOfCompressed(diary.getLengthOfCompressed());

        return diaryInfo;
    }

    @Override
    public String decodeDinary(Long id, String password) {
        //다이어리 복호화 시도
        Diary diary = memoryDiaryRepository.findById(id);
        String result = "";
        try{
            String tempHashedPassword = Hash256Algorithm.sha256(password);
            System.out.println(diary.getCryptoText() + "//" + tempHashedPassword);
            result = aesAlgorithm.decryption(diary.getCryptoText(), tempHashedPassword); //aes 암호화(텍스트, 해시)
            result = compressionAlgorithm.decompression(diary.getIncodHeader(), result); //압축해제
        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}
