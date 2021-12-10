package gachon.ac.kr.secretdiary.algorithm;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.dto.ReturnCompressResult;
import org.w3c.dom.Node;

import java.util.*;
import java.util.stream.Stream;



public class HuffmanCompressionAlgorithm implements CompressionAlgorithm{


    //실제로 강형래 님이 구현한 huffman compress code 입니다.

    @Override
    public ReturnCompressResult compression(String originalString) {
        HashMap<Character, String> prefixCodeTable = new HashMap<>();

        Map<Character, Integer> charFreq = new HashMap<>(); //get frequency
        for(char c : originalString.toCharArray()) {
            if(!charFreq.containsKey(c)) {
                charFreq.put(c,  1);
            }else {
                int no = charFreq.get(c);
                charFreq.put(c,  ++no);
            }
        }

        //허프만 트리구성
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = charFreq.keySet();
        for(char c : keySet) {
            Node node = new Node(c, charFreq.get(c));
            priorityQueue.offer(node);
        }
        Node rootNode = buildTree(priorityQueue);

        //prefix code 설정
        prefixCodeTable.clear();
        setPrefixCode(rootNode, "", prefixCodeTable);


        //Original data -> Prefix Code
        StringBuilder sb = new StringBuilder();
        for(char c : originalString.toCharArray()) {
            sb.append(prefixCodeTable.get(c));
        }

        ReturnCompressResult returnCompressResult = new ReturnCompressResult();
        returnCompressResult.setHeader(prefixCodeTable);
        returnCompressResult.setResult(sb.toString());


        return returnCompressResult;
    }

    class Node implements Comparable<Node> {
        char cData;
        int frequency;
        Node left, right;

        Node(){}
        Node(char cData, int frequency){
            this.cData = cData;
            this.frequency = frequency;
        }

        public int compareTo(Node node) {
            return frequency - node.frequency;
        }
    }

    public Node buildTree(PriorityQueue<Node> priorityQueue) {
        if(priorityQueue.size()==1) {
            return priorityQueue.poll();
        }else {
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();

            Node sumNode = new Node();
            sumNode.cData = '`';
            sumNode.frequency = leftNode.frequency + rightNode.frequency;
            sumNode.left = leftNode;
            sumNode.right = rightNode;

            priorityQueue.offer(sumNode);
            return buildTree(priorityQueue);
        }
    }

    //prefix Code 설정 함수
    public void setPrefixCode(Node node, String code, HashMap<Character, String> prefixCodeTable) {
        if(node == null) {
            return;
        }
        if(node.cData != '`' && node.left == null && node.right == null) {
            prefixCodeTable.put(node.cData, code);
        }else{
            setPrefixCode(node.left, code + '0', prefixCodeTable);
            setPrefixCode(node.right, code + '1', prefixCodeTable);
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    //실제로 김가은님이 구현한 허프만 복호화 코드입니다.
    @Override
    public String decompression(HashMap<Character, String> header, String compressedString) {

        StringBuilder sb = new StringBuilder();//출력할 문장(복원된 문장, string의 형태로 이어붙임)
        String Temp = "";//일시적으로 하나의 단어를 저장해둘 변수
        for(char originalData : compressedString.toCharArray()) {//압축된 상태의 글을 char의 형태로 잘라서 array에 저장
            Temp = Temp + originalData;//char형태의 단어를 Temp에 일시적으로 저장

            if(header.containsValue(Temp)) {//선언된 prefix에 Temp에 저장된 값이 있을 경우
                Stream<Character> keyStream = getKeysByValue(header, Temp);//key와 value가 일치하면 불러옴 stream의 형태로
                char key = keyStream.findFirst().get();
                sb.append(key);//key값을 출력 문장에 추가
                Temp = "";//Temp 초기화
            }
        }
        //압축 해제된 원본 텍스트를 return 해주세요.
        return sb.toString();
    }
    public <K, V> Stream<K> getKeysByValue(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }

}



