package com.wootechco.Makey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String  hello(){
        return "Hello from Spring!";
    }
    @PostMapping
    public String echo(@RequestBody String message){
     return "Spring received: "+message;
    }
}
