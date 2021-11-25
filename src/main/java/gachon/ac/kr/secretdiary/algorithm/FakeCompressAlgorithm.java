package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;

import java.util.HashMap;

public class FakeCompressAlgorithm implements CompressionAlgorithm{
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
