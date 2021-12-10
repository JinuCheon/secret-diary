package gachon.ac.kr.secretdiary.algorithm;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class LibraryAesAlgorithm implements AesAlgorithm{

//    @Override
//    public String encryption(String originalString, String key) { return null; }

//    @Override
//    public String decryption(String encryptedString, String key) { return null; }


    //aes256 알고리즘입니다.
    //충분한 시간이 없어서, 라이브러리로 구현하였습니다.

    public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
    //aes 암호화를 위해서는, 키값 말고도, 비슷한 역할을 하는 iv 값이 필요합니다.

    @Override
    public String encryption(String originalString, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        //AES 암호화를 하는 부분입니다.
        //HASH256으로 패스워드를 해싱해서 KEY값으로 가져옵니다.
        byte[] textBytes = originalString.getBytes("UTF-8"); //한글 지원을 위해 utp-8 규격을 사용하였습니다.
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //PKCS5Padding 방식입니다.
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        //암호화된 코드를 RETURN 해줍니다.
        return Base64.encodeBase64String(cipher.doFinal(textBytes));
    }

    @Override
    public String decryption(String encryptedString, String key) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        //해시된 키값을 가져와서 복호화를 진행합니다.
        byte[] textBytes = Base64.decodeBase64(encryptedString);
        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        //원문파일을 RETURN 해줍니다.
        return new String(cipher.doFinal(textBytes), "UTF-8");
    }
}
