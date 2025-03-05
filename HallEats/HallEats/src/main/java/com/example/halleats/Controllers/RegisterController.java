package com.example.halleats.Controllers;

import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {
@GetMapping("register")

    public  ModelAndView register(HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists

        if (session != null && session.getAttribute("user") != null) {
            // User is already logged in, redirect to the home page
            mv.setViewName("Home");
            mv.addObject("msg", token.getName());
        }
        else
        {
            mv.setViewName("register");
        }
        return mv;


    }

}
