package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;

public interface CompressionAlgorithm {
    String compression(Diary diary, String originalString);
    String decompression(String header, String compressedString);
}
