package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;

public class HuffmanCompressionAlgorithm implements CompressionAlgorithm{

    @Override
    public void compression(Diary diary, String originalString) {
        //형래님 잘 부탁드립니다.
        //압축 할 문자열입니다 -> originalString;


        //setIncodedText 함수에 압축된 문자가 담긴 변수를 넣어주시고,
        //setIncodHeader 생성한 헤더를 넣어주세요,
        diary.setIncodedText(null);
        diary.setIncodHeader(null);
    }

    @Override
    public String decompression(String header, String compressedString) {
        //가은님 잘 부탁드립니다.


        //압축 해제된 원본 텍스트를 return 해주세요.
        return null;
    }
}
