package com.coursework.controller.dashboard;

import com.coursework.model.User;
import com.coursework.service.Impl.DemoTrackServiceImpl;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    private DemoTrackServiceImpl demoTrackService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @RequestMapping(value = "/submitteddemos", method = RequestMethod.GET)
    public ModelAndView myTracks(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("tracks", demoTrackService.findAll());
        modelAndView.setViewName("submitteddemos");
        return modelAndView;
    }

    @RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
    public ModelAndView confirm(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

}
