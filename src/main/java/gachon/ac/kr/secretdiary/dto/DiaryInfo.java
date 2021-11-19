package gachon.ac.kr.secretdiary.dto;

public class DiaryInfo {
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
