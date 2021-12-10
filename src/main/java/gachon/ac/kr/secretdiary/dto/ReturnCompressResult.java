package gachon.ac.kr.secretdiary.dto;

import java.util.HashMap;
import java.util.List;

public class ReturnCompressResult {
    //압축 로직에서 COMPRESS RESULT를 돌려죽디 위해 만들어진 클래스입니다.
    //getter setter 패턴을 사용하였으며, 많이 사용되고 반복적인 패턴이라 자세한 설명 생략하겠습니다.
    //getter setter 는, 인스턴스 객체의 값에 직접 접근하는 일이 없도록 설계됩니다.

    private HashMap<Character, String> header;
    private String result;

    public HashMap<Character, String> getHeader() {
        return header;
    }

    public void setHeader(HashMap<Character, String> header) {
            this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
