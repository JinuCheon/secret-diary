package gachon.ac.kr.secretdiary.service;

import gachon.ac.kr.secretdiary.algorithm.*;
import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.dto.DiaryInfo;
import gachon.ac.kr.secretdiary.dto.NewDiary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;
import gachon.ac.kr.secretdiary.repository.DiaryRepository;
import gachon.ac.kr.secretdiary.repository.MemoryDiaryRepository;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DiaryServiceImpl implements DiaryService{
    DiaryRepository memoryDiaryRepository = new MemoryDiaryRepository();
    CompressionAlgorithm compressionAlgorithm = new HuffmanCompressionAlgorithm();
    AesAlgorithm aesAlgorithm = new LibraryAesAlgorithm();

    @Override
    public NewDiary diaryList() {
        NewDiary newDiary = new NewDiary();
        newDiary.setDiaryList(memoryDiaryRepository.findAll());
        newDiary.setTotalTextOriginal(memoryDiaryRepository.getTotalLengthOfOriginal()); //갔다와서 클래스 두 개 만들고 자.
        newDiary.setTotalTextCompress(memoryDiaryRepository.getTotalLengthOfCompressed());
//        System.out.println("diaryList오리지날 :"+newDiary.getTotalTextOriginal());
//        System.out.println("diaryList압축 :"+newDiary.getTotalTextCompress());


        return newDiary;
    }

    @Override
    public Object newDiary(NewDiaryForm newDiaryForm) throws NoSuchAlgorithmException {
        Diary diary = new Diary();
        try {
            //password hash.
            String tempHashedPassword = Hash256Algorithm.sha256(newDiaryForm.getPassword());
            //인코딩, 압축알고리즘 호출
            ReturnCompressResult returnCompressResult = compressionAlgorithm.compression(newDiaryForm.getText());
            diary.setIncodHeader(returnCompressResult.getHeader());


            diary.setCryptoText(aesAlgorithm.encryption(returnCompressResult.getResult(), tempHashedPassword)); //aes 암호화(텍스트, 해시)
        } catch (Exception e){
            System.out.println(e);
        }

        diary.setName(newDiaryForm.getName());
        diary.setLengthOfOriginal(newDiaryForm.getText().length());
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

        System.out.println("here is info");
        System.out.println(diary.getId());
        System.out.println(diary.getLengthOfOriginal());
        System.out.println(diary.getLengthOfCompressed());
        System.out.println(diary.getTime());
        System.out.println(diary.getIncodHeader());
        System.out.println(diary.getCryptoText());
        System.out.println("end of info");

        return diaryInfo;
    }

    @Override
    public String decodeDinary(Long id, String password) {
        System.out.println("check point 1");
        Diary diary = memoryDiaryRepository.findById(id);



        String result = "";
        //디코딩, 압축해제 거쳐야함
        try{
            String tempHashedPassword = Hash256Algorithm.sha256(password);
            System.out.println("check point 2 : "+tempHashedPassword);
            System.out.println(diary.getCryptoText() + "//" + tempHashedPassword);
            result = aesAlgorithm.decryption(diary.getCryptoText(), tempHashedPassword); //aes 암호화(텍스트, 해시)
            System.out.println("check point 3 : "+result);
            result = compressionAlgorithm.decompression(diary.getIncodHeader(), result); //압축해제
            System.out.println("check point 4 : "+result);

        } catch (Exception e){
            System.out.println(e);
        }

        return result;
    }
}
