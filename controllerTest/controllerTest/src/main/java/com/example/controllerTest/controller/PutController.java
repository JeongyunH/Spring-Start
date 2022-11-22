package com.example.controllerTest.controller;

import com.example.controllerTest.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutController {

    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto, @PathVariable(name = "userId") Long userId) {
        System.out.println(userId);
        return putRequestDto;
    }
}
