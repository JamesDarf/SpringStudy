package edu.sch.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    static class Hello {
        String name;
        String message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    @ResponseBody
    @GetMapping("/hello-api")
    public Hello helloApi(@RequestParam("name") String name,
                          @RequestParam("msg") String message,
                          Model model) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setMessage(message);
        return hello;
    }


    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name,
                        @RequestParam("msg") String message,
                        Model model) {
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        return "hello-spring"; //템플릿 엔진에서 호출!!!
    }
}
