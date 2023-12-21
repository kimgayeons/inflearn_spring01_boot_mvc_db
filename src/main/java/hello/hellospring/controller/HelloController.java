package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello4!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody
    //이방법으로 쓸일이 암ㅎ진 않다
    //ResponseBody는 html에 body태그가 아닌 http에서 header와 body가 있는데 body부에 데이터를 직접 넣어주겠다는 의미
    //ResponseBody를 사용하면 viewResolver를 사용하지 않고, HTTP의 BODY에 문자 내용을 직접 반환한다.
    public String helloString(@RequestParam(name = "name") String name, Model model){
        return "hello " + name;//이렇게 하게될경우 데이터를 그대로 화면에 보여주게 된다
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(name = "name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        /*해당 방식은 json 방식
        * 출력 시 {"name":"spring!!!"} 으로 노출
        * json : key value로 이루어진 구조
        * */
    }
    static class Hello{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
