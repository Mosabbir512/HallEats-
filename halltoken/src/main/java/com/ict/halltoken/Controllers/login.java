package com.ict.halltoken.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class login {

    @RequestMapping("/")
    public ModelAndView login()
    {
       ModelAndView mv= new ModelAndView() ;
       mv.setViewName("login");
       //mv.addObject();
       return mv;
    }

    @RequestMapping("verification")
    public ModelAndView verificationbymosa(@RequestParam String phone,@RequestParam String password)
    {
        ModelAndView mv= new ModelAndView();

         if(phone.length()==11)
         {
             mv.setViewName("Home");
         }
         else
             mv.setViewName("login");
         return mv;
    }



    @RequestMapping("loginsuccess")
    public ModelAndView suc(@RequestParam String fname,@RequestParam String mname)
    {
            ModelAndView mv= new ModelAndView();
            mv.setViewName("loginsuccess");
            return mv;
    }

}
