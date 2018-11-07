package com.coursework.controller;

import com.coursework.model.DemoTrack;
import com.coursework.model.User;
import com.coursework.service.Impl.DemoTrackServiceImpl;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private DemoTrackServiceImpl demoTrackService;

    @GetMapping("/alldemos")
    public ResponseEntity<?> getAllDemos() {
        List<DemoTrack> result = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if(user != null) {
            if (user.hasRole("ADMIN")) {
                result = demoTrackService.findAll();
            }
            if (user.hasRole("USER")) {
                result = demoTrackService.findAllByUser(user);
            }
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteDemoById(@RequestParam("id") String id) {
        demoTrackService.deleteDemoTrack(new ObjectId(id));
    }

}