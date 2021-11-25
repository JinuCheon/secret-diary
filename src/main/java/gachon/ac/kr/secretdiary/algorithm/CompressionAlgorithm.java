package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;

import java.util.ArrayList;
import java.util.HashMap;

public interface CompressionAlgorithm {
    ReturnCompressResult compression(String originalString);
    String decompression(HashMap<Character, String> header, String compressedString);
}
