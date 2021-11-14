package gachon.ac.kr.secretdiary.algorithm;

public interface AesAlgorithm {
    String encryption(String originalString, String key);
    String decryption(String encryptedString, String key);
}
