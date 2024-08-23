package com.apredendo.first_spring_app.controller;

import com.apredendo.first_spring_app.domain.User;
import com.apredendo.first_spring_app.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWordController {

    @Autowired
    private HelloWorldService helloWorldService;

//    public HelloWordController(HelloWorldService helloWorldService) {
//        this.helloWorldService = helloWorldService;
//    }


    @GetMapping
    public String helloWorld() {
        return helloWorldService.helloWorld("Ricardo");
    }

    @PostMapping("/{id}")
    public String helloWorldPost(@PathVariable("id") String id,@RequestParam(value="filter", defaultValue = "nenhum") String filter,@RequestBody User body) {
        return "Hello World!\n"+body.getName()+"!!\n" + id + filter;
    }
}
