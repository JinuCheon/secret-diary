package gachon.ac.kr.secretdiary.dto;

public class DiaryInfo {
	//다이어리 열람시, 사용되는 폼 정의입니다.
	//getter setter 패턴을 사용하였으며, 많이 사용되고 반복적인 패턴이라 자세한 설명 생략하겠습니다.
	//getter setter 는, 인스턴스 객체의 값에 직접 접근하는 일이 없도록 설계됩니다.
    private String cryptoText;
    private int lengthOfOriginal;
    private int lengthOfCompressed;
	public String getCryptoText() {
		return cryptoText;
	}
	public void setCryptoText(String cryptoText) {
		this.cryptoText = cryptoText;
	}
	public int getLengthOfOriginal() {
		return lengthOfOriginal;
	}
	public void setLengthOfOriginal(int lengthOfOriginal) {
		this.lengthOfOriginal = lengthOfOriginal;
	}
	public int getLengthOfCompressed() {
		return lengthOfCompressed;
	}
	public void setLengthOfCompressed(int lengthOfCompressed) {
		this.lengthOfCompressed = lengthOfCompressed;
	}
    
}
