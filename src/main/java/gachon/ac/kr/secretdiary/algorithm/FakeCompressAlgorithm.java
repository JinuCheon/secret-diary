package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;

public class FakeCompressAlgorithm implements CompressionAlgorithm{
    @Override
    public void compression(Diary diary, String originalString) {
        diary.setIncodedText("compression: "+originalString);
        diary.setIncodHeader("header");
    }

    @Override
    public String decompression(String header, String compressedString) {
        return "decompression: " + compressedString;
    }
}
