package org.team2project.camealone.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Java!";
    }

    @PostMapping("/data")
    public String receiveData(@RequestBody MyData data) {

        return "Received data: " + data.getMessage();
    }
}

class MyData {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}