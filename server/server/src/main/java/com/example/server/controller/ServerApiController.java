package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //https://openapi.naver.com/v1/search/blog.xml
    // ?query=%EB%A6%AC%EB%B7%B0
    // &display=10
    // &start=1
    // &sort=sim
    @GetMapping("/naver")
    public String naverTest(){

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/blog.xml")
                .queryParam("query", "갈비집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        log.info("uri : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "PDJqSGzs0OEYwHzGN2hD")
                .header("X-Naver-Client-Secret", "F8pKYaYqNz")
                .build();



        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return "hello server";
    }

//    @PostMapping("/user/{userId}/name/{userName}")
//    public User post(@RequestBody User user,
//                     @PathVariable int userId,
//                     @PathVariable String userName,
//                     @RequestHeader("authkey") String authkey,
//                     @RequestHeader("custom-header") String custom
//    ){
//        log.info("userId : {}, userName : {}", userId, userName);
//        log.info("authkey : {}, custom-header : {}", authkey, custom);
//        log.info("client req : {}", user);
//        return user;
//    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(@RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("authkey") String authkey,
                     @RequestHeader("custom-header") String custom
    ){
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authkey : {}, custom-header : {}", authkey, custom);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header("OK")
        );
        response.setResBody(user.getResBody());

        return response;
    }
}
