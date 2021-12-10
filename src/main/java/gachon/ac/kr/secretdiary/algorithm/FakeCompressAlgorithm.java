package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;

import java.util.HashMap;

public class FakeCompressAlgorithm implements CompressionAlgorithm{
    //huffman 알고리즘 구현 전에, 실행 테스트를 위해 임시로 사용되었던 클래스입니다.
    //어떠한 로직 없이, 값을 그대로 리턴합니다.

    @Override
    public ReturnCompressResult compression(String originalString) {
        ReturnCompressResult returnCompressResult = new ReturnCompressResult();
        returnCompressResult.setResult(null);
        returnCompressResult.setHeader(null); //테스트라서 절반값.
        return null;
    }

    @Override
    public String decompression(HashMap<Character, String> header, String compressedString) {
        return "decompression: " + compressedString;
    }
}
