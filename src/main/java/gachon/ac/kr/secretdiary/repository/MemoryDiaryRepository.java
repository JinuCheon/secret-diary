package gachon.ac.kr.secretdiary.repository;

import gachon.ac.kr.secretdiary.domain.Diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryDiaryRepository implements DiaryRepository{

    private static Map<Long, Diary> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Diary save(Diary diary) {
        diary.setId(++sequence);
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
