package gachon.ac.kr.secretdiary.domain;

import java.util.HashMap;

public class Diary {
    //다이어리 클래스를 정의하는 부분입니다.
    //getter setter 패턴을 사용하였으며, 많이 사용되고 반복적인 패턴이라 자세한 설명 생략하겠습니다.
    //getter setter 는, 인스턴스 객체의 값에 직접 접근하는 일이 없도록 설계됩니다.

    private Long id;
    private int lengthOfOriginal;
    private int lengthOfCompressed;
    private String name;
    private String time;
    private HashMap<Character, String> incodHeader;
    private HashMap<Character, String> incodeHeader;
    private String cryptoText;

    public HashMap<Character, String> getIncodeHeader() {
        return incodeHeader;
    }

    public void setIncodeHeader(HashMap<Character, String> incodeHeader) {
        this.incodeHeader = incodeHeader;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public HashMap<Character, String> getIncodHeader() {
        return incodHeader;
    }

    public void setIncodHeader(HashMap<Character, String> incodHeader) {
        this.incodHeader = incodHeader;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCryptoText() {
        return cryptoText;
    }

    public void setCryptoText(String cryptoText) {
        this.cryptoText = cryptoText;
    }
}
