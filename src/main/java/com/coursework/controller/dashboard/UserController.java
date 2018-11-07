package com.coursework.controller.dashboard;

import com.coursework.model.DemoTrack;
import com.coursework.model.User;
import com.coursework.service.Impl.DemoTrackServiceImpl;
import com.coursework.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private DemoTrackServiceImpl demoTrackService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/myTracks", method = RequestMethod.GET)
    public ModelAndView myTracks(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("tracks", demoTrackService.findAllByUser(user));
        modelAndView.setViewName("myTracks");
        return modelAndView;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView submit(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.setViewName("submit");
        return modelAndView;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ModelAndView createNewUser(
            @RequestParam("title") String titleP,
            @RequestParam("genre") String genreP,
            @RequestParam("description") String descriptionP
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        DemoTrack demoTrack = new DemoTrack(user, titleP, genreP, descriptionP);
        demoTrackService.saveDemoTrack(demoTrack);
        modelAndView.addObject("currentUser", user);
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/changeAccount", method = RequestMethod.POST)
    public ModelAndView changeAccount(
            @RequestParam("displayName") String displayNameP,
            @RequestParam("fullName") String fullNameP,
            @RequestParam("password") String passwordP
    ) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(displayNameP != null && !displayNameP.equals("")) user.setUsername(displayNameP);
        if(fullNameP != null && !fullNameP.equals("")) user.setFullname(fullNameP);
        if(passwordP != null && !passwordP.equals("")) user.setPassword(bCryptPasswordEncoder.encode(passwordP));
        userService.updateSimpleUser(user);
        modelAndView.addObject("currentUser", user);
        modelAndView.setViewName("redirect:/dashboard");
        return modelAndView;
    }

}
