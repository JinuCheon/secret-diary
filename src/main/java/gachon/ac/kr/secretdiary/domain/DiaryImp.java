package gachon.ac.kr.secretdiary.domain;

public class DiaryImp implements Diary{
    private Long id;
    private String name;
    private String incodHeader;
    private String incodedText;
    private String cryptoText;

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

    public String getIncodedText() {
        return incodedText;
    }

    public void setIncodedText(String incodedText) {
        this.incodedText = incodedText;
    }

    public String getCryptoText() {
        return cryptoText;
    }

    public void setCryptoText(String cryptoText) {
        this.cryptoText = cryptoText;
    }
}
