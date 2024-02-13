package com.example.keycloackdemo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class AppController {

    @GetMapping
    public String hello(){return "Hello from everyone";}
    @GetMapping("admin")
    @PreAuthorize("hasRole('client_admin')")
    public String hello2(){return "Hello from only admin";}
}
