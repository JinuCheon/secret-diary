package gachon.ac.kr.secretdiary.controller;

import gachon.ac.kr.secretdiary.domain.Diary;
import gachon.ac.kr.secretdiary.domain.NewDiaryForm;
import gachon.ac.kr.secretdiary.service.DiaryService;
import gachon.ac.kr.secretdiary.service.DiaryServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {
    DiaryService diaryService = new DiaryServiceImpl();

    @RequestMapping(method = RequestMethod.POST, path = "/test")
    public int getRequest(@RequestParam(value = "name") int name) {
        return name;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/DiaryList")
    public Object diaryList(){
        return diaryService.diaryList();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NewDiary")
    public Object newDiary(NewDiaryForm newDiaryForm){
        diaryService.newDiary(newDiaryForm);
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/DiaryInfo")
    public Object diaryInfo(@RequestParam String id){
        String cryptoText = diaryService.diaryInfo(Long.parseLong(id));
        return cryptoText;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/DecodeDiary")
    public Object decodeDiary(@RequestParam String id, @RequestParam String password){
        String originalText = diaryService.decodeDinary(Long.parseLong(id), password);
        return originalText;
    }
}
