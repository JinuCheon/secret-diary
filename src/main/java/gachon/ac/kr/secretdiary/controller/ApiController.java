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
        System.out.println(diaryService.diaryList());
        return diaryService.diaryList();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/NewDiary")
    public Object newDiary(NewDiaryForm newDiaryForm){
        Diary diary = new Diary();
        diary.setName(newDiaryForm.getName());
        diary.setIncodedText(newDiaryForm.getText());
        diary.setCryptoText(newDiaryForm.getText());
        diaryService.newDiary(diary);
        System.out.println(diary.getName());
        return diary.getName();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/DiaryInfo")
    public Object diaryInfo(@RequestParam String id){
        return id;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/DecodeDiary")
    public Object decodeDiary(@RequestParam Long id){
        return null;
    }
}
