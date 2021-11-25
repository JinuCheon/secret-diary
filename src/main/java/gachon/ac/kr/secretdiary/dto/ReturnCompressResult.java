package gachon.ac.kr.secretdiary.dto;

import java.util.HashMap;
import java.util.List;

public class ReturnCompressResult {
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
