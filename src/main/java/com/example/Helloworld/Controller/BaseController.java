package com.example.Helloworld.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {
    @RequestMapping("/home")
    @ResponseBody
    public ResponseEntity<String> homeBody(){
        return ResponseEntity.ok("index");
    }
}
