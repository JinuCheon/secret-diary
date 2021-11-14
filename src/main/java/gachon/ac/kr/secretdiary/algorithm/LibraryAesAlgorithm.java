package gachon.ac.kr.secretdiary.algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class LibraryAesAlgorithm implements AesAlgorithm{

    @Override
    public String encryption(String originalString, String key) {
        return null;
    }

    @Override
    public String decryption(String encryptedString, String key) {
        return null;
    }

}
