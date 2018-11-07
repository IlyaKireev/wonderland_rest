package com.coursework.controller.dashboard;

import com.coursework.model.User;
import com.coursework.service.Impl.DemoTrackServiceImpl;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private DemoTrackServiceImpl demoTrackService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (user != null) {
                if (user.hasRole("USER")) {
                    modelAndView.addObject("role", "USER");

                }
                if (user.hasRole("ADMIN")) {
                    modelAndView.addObject("role", "ADMIN");
                }
            modelAndView.addObject("currentUser", user);
        }

        modelAndView.setViewName("/dashboard");
        return modelAndView;
    }

}