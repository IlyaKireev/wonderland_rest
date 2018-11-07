package com.coursework.controller;

import com.coursework.service.Impl.DemoTrackServiceImpl;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

        @Autowired
        private UserDetailsServiceImpl userService;

    @Autowired
    private DemoTrackServiceImpl demoTrackService;

    @GetMapping
    public String home() {
        return "Home page";
    }

}