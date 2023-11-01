package com.ict.halltoken.Controllers;

import com.ict.halltoken.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Register {

    @Autowired
    User user;
    @RequestMapping ("/register")
    public ModelAndView reg()
    {
        ModelAndView mv= new ModelAndView();
        mv.addObject("userTH",user);
        mv.setViewName("register");
        return mv;
    }
    @PostMapping("/save")
    public String getUser(@ModelAttribute User user){
        //save to database;
        System.out.println(user.getFname()+" "+user.getMname());
        return "login";
    }
}
