package gachon.ac.kr.secretdiary.dto;

import gachon.ac.kr.secretdiary.domain.Diary;
import java.util.List;

public class NewDiary {
    private List<Diary> diaryList;
    private int totalTextOriginal;
    private int totalTextCompress;
	public List<Diary> getDiaryList() {
		return diaryList;
	}
	public void setDiaryList(List<Diary> diaryList) {
		this.diaryList = diaryList;
	}
	public int getTotalTextOriginal() {
		return totalTextOriginal;
	}
	public void setTotalTextOriginal(int totalTextOriginal) {
		this.totalTextOriginal = totalTextOriginal;
	}
	public int getTotalTextCompress() {
		return totalTextCompress;
	}
	public void setTotalTextCompress(int totalTextCompress) {
		this.totalTextCompress = totalTextCompress;
	}
    
}
