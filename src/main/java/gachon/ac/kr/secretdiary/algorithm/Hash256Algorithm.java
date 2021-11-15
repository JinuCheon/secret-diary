package gachon.ac.kr.secretdiary.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash256Algorithm {

    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return makeHalfHash(bytesToHex(md.digest()));
    }

    public static String makeHalfHash(String str){
        String halfHash = "";
        for(int i=0;i<str.length();i+=2){
            halfHash += str.charAt(i);
        }
        System.out.println("halfHash : " + halfHash);
        return halfHash;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        System.out.println("Hash : " + builder.toString());
        return builder.toString();
    }

}
