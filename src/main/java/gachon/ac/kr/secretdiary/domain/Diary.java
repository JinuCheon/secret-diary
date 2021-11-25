package gachon.ac.kr.secretdiary.domain;

import java.util.HashMap;

public class Diary {
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
