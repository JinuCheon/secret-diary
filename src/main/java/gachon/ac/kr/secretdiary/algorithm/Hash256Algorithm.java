package gachon.ac.kr.secretdiary.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash256Algorithm {
    //hash256 알고리즘입니다.
    //충분한 시간이 없어서, 라이브러리로 구현하였습니다.

    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); //sha-256 사용.
        md.update(msg.getBytes());
        return makeHalfHash(bytesToHex(md.digest())); //해싱
    }

    public static String makeHalfHash(String str){
        //aes key 규격에 맞추기 위해, 만들어진 해시를 위해 해시를 반으로 만듭니다.
        String halfHash = "";
        for(int i=0;i<str.length();i+=2){ //짝수번째 값만 남기고 홀수번째 값을 삭제하빈다.
            halfHash += str.charAt(i);
        }
        System.out.println("halfHash : " + halfHash);
        return halfHash;
    }

    public static String bytesToHex(byte[] bytes) {
        //hash를 byte 로 저장하기 위한 함수입니다.
        StringBuilder builder = new StringBuilder();
        //byte로 append합니다.
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        System.out.println("Hash : " + builder.toString());
        return builder.toString();
    }

}
