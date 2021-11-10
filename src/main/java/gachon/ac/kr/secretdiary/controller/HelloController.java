package gachon.ac.kr.secretdiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("DiaryList")
    @ResponseBody
    public Object diaryList(){
        return null;
    }

    @PostMapping("NewDiary")
    @ResponseBody
    public Object newDiary(){
        return null;
    }

    @GetMapping("DiaryInfo")
    @ResponseBody
    public Object diaryInfo(){
        return null;
    }

    @GetMapping("DecodeDiary")
    @ResponseBody
    public Object decodeDiary(){
        return null;
    }
}
