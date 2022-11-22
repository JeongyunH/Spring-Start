package com.example.controllerTest.controller;

import com.example.controllerTest.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")

public class GetApiController {

    @GetMapping(path="/hello")
    public String getHello(){
        return "get hello";
    }

    @RequestMapping(path="/hi", method= RequestMethod.GET)
    public String hi(){
        return "get hi";
    }

    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name="id") String pathName){
        System.out.println("PathVariable "+pathName);
        return pathName;
    }

    // http://localhost:9090/api/get/query-param?user=streve&email=test@gmail.com&age=30
    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    @GetMapping("query-param02")
    public String queryParam02(@RequestParam String user, @RequestParam String email, @RequestParam int age){
        System.out.println(user);
        System.out.println(email);
        System.out.println(age);

        return user+", "+email+", "+age;
    }

    // ?user=streve&email=test@gmail.com&age=30
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getUser());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
