package gachon.ac.kr.secretdiary.algorithm;

public interface CompressionAlgorithm {
    String compression(String originalString);
    String decompression(String header, int compressedString);
}
