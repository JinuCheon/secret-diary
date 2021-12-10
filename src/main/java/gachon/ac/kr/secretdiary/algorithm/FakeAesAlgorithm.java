package gachon.ac.kr.secretdiary.algorithm;

public class FakeAesAlgorithm implements AesAlgorithm{
    //aes 알고리즘 구현 전에, 실행 테스트를 위해 임시로 사용되었던 클래스입니다.
    //어떠한 로직 없이, 값을 그대로 리턴합니다.
    @Override
    public String encryption(String originalString, String key) {
        return "encryption :" + originalString;
    }

    @Override
    public String decryption(String encryptedString, String key) {
        return "decryption :" + encryptedString;
    }
}
