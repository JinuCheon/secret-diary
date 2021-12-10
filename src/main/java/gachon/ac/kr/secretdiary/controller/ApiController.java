package gachon.ac.kr.secretdiary.controller;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.dto.DiaryInfo;
import gachon.ac.kr.secretdiary.service.DiaryService;
import gachon.ac.kr.secretdiary.service.DiaryServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class ApiController {
    //web application 과의 통신을 위한 rest api 컨트롤러입니다.

    DiaryService diaryService = new DiaryServiceImpl();
    DiaryInfo diaryInfo = new DiaryInfo();

//    @RequestMapping(method = RequestMethod.POST, path = "/test")
//    public int getRequest(@RequestParam(value = "name") int name) {
//        return name;
//    }

    //다이어리 리스트를 렌더링
    @RequestMapping(method = RequestMethod.GET, path = "/DiaryList")
    public Object diaryList(){
        return diaryService.diaryList();
    }

    //새로운 다이어리 생성
    @RequestMapping(method = RequestMethod.POST, path = "/NewDiary")
    public Object newDiary(NewDiaryForm newDiaryForm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        diaryService.newDiary(newDiaryForm);
        return null;
    }

    // 열람 버튼 시, 다이어리 정보
    @RequestMapping(method = RequestMethod.GET, path = "/DiaryInfo")
    public Object diaryInfo(@RequestParam String id){

        DiaryInfo diaryInfo = diaryService.diaryInfo(Long.parseLong(id));
        return diaryInfo;
    }

    //해독 시, 로직 호출
    @RequestMapping(method = RequestMethod.POST, path = "/DecodeDiary")
    public Object decodeDiary(@RequestParam String id, @RequestParam String password){
        String originalText = diaryService.decodeDinary(Long.parseLong(id), password);
        return originalText;
    }
}
