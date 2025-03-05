package com.example.halleats.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class logoutController {

        @GetMapping ("logout")
    public ModelAndView logout(HttpServletRequest request)
    {

        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session exists
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        mv.setViewName("login");
        return mv;
    }

}
