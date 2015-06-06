package in.javawithgaurav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    public HelloWorld() {
    }
    
    @RequestMapping(value="/hello")
    public String hello() {
        return "helloworld";
    }

}
