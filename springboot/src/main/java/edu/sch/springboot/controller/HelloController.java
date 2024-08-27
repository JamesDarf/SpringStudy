package edu.sch.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //json : javascript object notation: {name : "james", age: 21}

    /** Person 객체 정의 */
    static class Person{ //static을 사용해서 템플릿 엔진을 거치지 않고 바로 접속
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


    @ResponseBody // json 데이터로 변환해서 받기 위해 사용.
    @GetMapping("spring-person")
    public Person springPerson(@RequestParam("name") String name,
                               @RequestParam("age") int age,
                               Model model) { //타입을 Person으로 object 타입이라서
        Person person = new Person(); // 새로운 객체 생성
        person.setName(name);
        person.setAge(age);
        return person; // 객체 리턴.
    }


    @ResponseBody // return하는 값을 템플릿 엔진이 아닌 문자열로 받는다는 것
    @GetMapping("spring-api")
    public String springApi(@RequestParam("name") String name,
                            @RequestParam("age") int age, Model model) {
        // return "spring-api, " + "name : " + name + ", age : "  + age; // nomal type
        // return "{name :'" + name + "', age :'" + age + "}"; // json type, but it hard
        return "spring, " + name + "," + age;
    }


    @GetMapping("spring-mvc")
    public String springMvc(@RequestParam("name") String name,
                            @RequestParam("age") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "spring-mvc";
    }


    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello~Spring~!!");
        return "hello";
    }


}
