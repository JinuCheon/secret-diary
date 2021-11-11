package gachon.ac.kr.secretdiary.repository;

import gachon.ac.kr.secretdiary.domain.Diary;

import java.text.SimpleDateFormat;
import java.util.*;

public class MemoryDiaryRepository implements DiaryRepository{

    private static Map<Long, Diary> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Diary save(Diary diary) {
        diary.setId(sequence++);

        Date date_now = new Date(System.currentTimeMillis());
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMdd");;

        diary.setTime(fourteen_format.format(date_now));

        store.put(diary.getId(), diary);
        return null;
    }

    @Override
    public Diary findById(long id) {
        return null;
    }

    @Override
    public List<Diary> findAll() {
        return new ArrayList<>(store.values());
    }
}
