package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;

public class HuffmanCompressionAlgorithm implements CompressionAlgorithm{

    @Override
    public String compression(Diary diary, String originalString) {
        String result = "";
        //형래님 잘 부탁드립니다.
        //압축 할 문자열입니다 -> originalString;


        //setIncodHeader 생성한 헤더를 넣어주세요,
        diary.setIncodHeader(null);
        diary.setLengthOfCompressed(result.length());
        return null; // 함수에 압축된 문자가 담긴 변수를 넣어주세요.
    }

    @Override
    public String decompression(String header, String compressedString) {
        //가은님 잘 부탁드립니다.


        //압축 해제된 원본 텍스트를 return 해주세요.
        return null;
    }
}
