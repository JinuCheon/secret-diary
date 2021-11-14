package gachon.ac.kr.secretdiary.algorithm;

public class FakeAesAlgorithm implements AesAlgorithm{
    @Override
    public String encryption(String originalString, String key) {
        return "encryption :" + originalString;
    }

    @Override
    public String decryption(String encryptedString, String key) {
        return "decryption :" + encryptedString;
    }
}
