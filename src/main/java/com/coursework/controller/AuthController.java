package com.coursework.controller;

import com.coursework.model.User;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/signup")
    public HttpStatus createNewUser(@RequestParam Map<String, Object> body) {

        System.out.println(body.toString());

        User userExists = userService.findUserByEmail(body.get("email").toString());

        if(userExists == null) {
            System.out.println("User not exists. Creating new user.");
            User user = new User();
            user.setPassword(body.get("password").toString());
            user.setEmail(body.get("email").toString());
            user.setFullname(body.get("fullname").toString());
            userService.saveSimpleUser(user);
            System.out.println("User with email \"" + body.get("email") + "\" has created successful.");
            return HttpStatus.OK;
        }

        System.out.println("User exists.");
        return HttpStatus.CONFLICT;
    }

    @GetMapping("/isAuthorized")
    public HttpStatus isAuthorized() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(user != null) {
            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
    }
}