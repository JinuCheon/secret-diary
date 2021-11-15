package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;

public class FakeCompressAlgorithm implements CompressionAlgorithm{
    @Override
    public String compression(Diary diary, String originalString) {
        diary.setIncodHeader("header");
        diary.setLengthOfCompressed(originalString.length()/2); //테스트라서 절반값.
        return "compression: "+originalString;
    }

    @Override
    public String decompression(String header, String compressedString) {
        return "decompression: " + compressedString;
    }
}
