package gachon.ac.kr.secretdiary.dto;

import gachon.ac.kr.secretdiary.domain.Diary;
import java.util.List;

public class NewDiary {
	//새 다이어를 만들때 사용되는 폼을 정의하는 부분입니다.
	//getter setter 패턴을 사용하였으며, 많이 사용되고 반복적인 패턴이라 자세한 설명 생략하겠습니다.
	//getter setter 는, 인스턴스 객체의 값에 직접 접근하는 일이 없도록 설계됩니다.

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
