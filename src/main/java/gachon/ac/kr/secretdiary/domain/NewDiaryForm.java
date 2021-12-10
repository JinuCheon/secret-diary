package gachon.ac.kr.secretdiary.domain;

public class NewDiaryForm {
    //새로운 클래스를 만들때 폼으로 사용됩니다.
    //getter setter 패턴을 사용하였으며, 많이 사용되고 반복적인 패턴이라 자세한 설명 생략하겠습니다.
    //getter setter 는, 인스턴스 객체의 값에 직접 접근하는 일이 없도록 설계됩니다.

    private String name;
    private String password;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
