package gachon.ac.kr.secretdiary.repository;

import gachon.ac.kr.secretdiary.domain.Diary;

import java.text.SimpleDateFormat;
import java.util.*;

public class MemoryDiaryRepository implements DiaryRepository{
    // 데이터베이스 사용을 위해 MEMORY 기반으로 HASHMAP 형태의 데이터베이스를 만들었습니다.

    private static Map<Long, Diary> store = new HashMap<>();
    private static long sequence = 0L; //회원 번호를 카운트
    private static int lengthOfOriginal = 0;
    private static int lengthOfCompressed = 0;

    @Override
    public Diary save(Diary diary) {
        diary.setId(sequence++); //회원번호 세팅 및 회원 번호 증가

        Date date_now = new Date(System.currentTimeMillis());
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        diary.setTime(fourteen_format.format(date_now));
        lengthOfOriginal += diary.getLengthOfOriginal();
        lengthOfCompressed += diary.getLengthOfCompressed();
        store.put(diary.getId(), diary);
        return null;
    }

    @Override
    public Diary findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Diary> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public int getTotalLengthOfOriginal() {
        return lengthOfOriginal;
    }

    @Override
    public int getTotalLengthOfCompressed() {
        return lengthOfCompressed;
    }
}
