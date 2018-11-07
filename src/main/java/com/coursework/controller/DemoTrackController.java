package com.coursework.controller;

import com.coursework.model.DemoTrack;
import com.coursework.service.DemoTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DemoTrackController {

    @Autowired
    private DemoTrackService demoTrackService;

    @RequestMapping(value = "/trackList", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<DemoTrack> result = demoTrackService.findAll();
        modelAndView.addObject("trackList", result);
        modelAndView.setViewName("/trackList");
        return modelAndView;
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getByUserId(@PathVariable("userId") String userId) {
//        DemoTrack result = demoTrackService.findByUserId(userId);
//
//        if(result == null) {
//            return null;
//        }
//
//        return new ResponseEntity(result, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addDemoTrack(@RequestBody DemoTrack demoTrack) {
//        demoTrackService.addDemoTrack(demoTrack);
//        return new ResponseEntity("Track added successfully", HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public void deleteExpense(@RequestParam("id") String id) {
//        demoTrackService.deleteDemoTrack(id);
//    }

}