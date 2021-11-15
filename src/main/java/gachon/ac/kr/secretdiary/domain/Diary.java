package gachon.ac.kr.secretdiary.domain;

public class Diary {
    private Long id;
    private int lengthOfOriginal;
    private int lengthOfCompressed;
    private String name;
    private String time;
    private String incodHeader;
    private String cryptoText;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getIncodHeader() {
        return incodHeader;
    }

    public void setIncodHeader(String incodHeader) {
        this.incodHeader = incodHeader;
    }

    public String getCryptoText() {
        return cryptoText;
    }

    public void setCryptoText(String cryptoText) {
        this.cryptoText = cryptoText;
    }
}
