package com.example.halleats.Controllers;

import com.example.halleats.Model.Token;
import com.example.halleats.Service.AuthorityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Controller
public class AuthorityController {

    @GetMapping("authority")
    public ModelAndView authority(HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            mv.setViewName("authorityHome");
        }
        else
        {
            mv.setViewName("authoritylogin");
        }

        return mv;

    }
    @PostMapping("checkauthoritylogin")
    public ModelAndView checkauthoritylogin(@RequestParam String id,@RequestParam String pass,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            mv.setViewName("authorityHome");
        }
         else
         {
             if(id.equals("authority")&&(pass.equals("1234")))
             {
                 session = request.getSession();

                 session.setAttribute("user", id);
                 mv.setViewName("authorityHome");

             }
             else
             {
                 mv.addObject("msg","Wrong UserId/Password");
                 mv.setViewName("authoritylogin");
             }

         }




        return mv;
    }
      @Autowired
    AuthorityService authorityService;
    @RequestMapping("Searchstudent")
    public ModelAndView searchstudnet(@RequestParam(value = "identity",defaultValue = "default") String identity,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            if (identity.equals("default")) {
                mv.addObject("msg", "Student with that identity didn't purchase any token for today");
                mv.setViewName("redirect:/Searchstudent");
                return mv;
            }
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy", Locale.ENGLISH);
            String formattedDate = currentDate.format(formatter);
           // System.out.println(formattedDate);
            Optional<Token> tokenobj = authorityService.searchtoken(identity,formattedDate);

            if (tokenobj.isPresent()) {
                Token token = tokenobj.get();
                mv.addObject("token", token);
                mv.setViewName("tokeninfo");
            } else {
                mv.addObject("msg", "Student with that identity didn't purchase any token for today");
                mv.setViewName("authorityHome");
            }
        }
        else
        {
            mv.setViewName("authoritylogin");
        }



        return mv;
    }

    @RequestMapping("gobacktohome")

    public ModelAndView gobacktohome(HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            mv.setViewName("authorityHome");
        }
        else
        {
            mv.setViewName("authoritylogin");
        }
        return mv;
    }
    @RequestMapping("logout2")
    public ModelAndView logout(HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session exists
        if (session != null) {
          //  System.out.println("hi");
            session.invalidate(); // Invalidate the session
        }


        mv.setViewName("authoritylogin");
        return mv;
    }



}
