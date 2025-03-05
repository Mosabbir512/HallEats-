package com.example.halleats.Controllers;

import com.example.halleats.Database.Registerrepo;
import com.example.halleats.Database.Tokenrepo;
import com.example.halleats.Model.Login;
import com.example.halleats.Model.Register;
import com.example.halleats.Model.Token;
import com.example.halleats.Service.CreatePdfFileService;
import com.example.halleats.Service.Viewtokenservices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class LoginController {
    @Autowired
    Registerrepo rrepo;
    @Autowired
    Token token;
    @Autowired
    Tokenrepo trepo;

    @ModelAttribute("potato")
    public Token info(Model model)
    {
        return token;
    }

    @RequestMapping("/")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists
        if (session != null && session.getAttribute("user") != null) {
            // User is already logged in, redirect to the home page
            mv.setViewName("Home");
            mv.addObject("msg", token.getName());
        } else {
            mv.setViewName("login");
        }
        return mv;
    }
    @Autowired
    Viewtokenservices viewtokenservices;
   @RequestMapping("checklogin")
    public  ModelAndView checklogin(Login login,HttpServletRequest request)
    {

        ModelAndView mv= new ModelAndView();

        HttpSession session = request.getSession(false); // Check if a session already exists

        if (session != null && session.getAttribute("user") != null) {
            mv.setViewName("Home");
            mv.addObject("msg", token.getName());


        }
        else {
             if(login.getPass()==null&&login.getIdentity()==null) {
                 mv.addObject("pc","You must log in to use HallEats services");
                 mv.setViewName("login");
             }
             else
             {

                 Register reg = rrepo.findpermission(login.getIdentity(), login.getPass());
                 if (reg != null) {
                    session = request.getSession();

                     session.setAttribute("user", reg);

                    mv.setViewName("Home");
//
//                     {
//                       Optional<List<Token> > list= viewtokenservices.searchmytokens(reg.getIdentity2());
//                     }

                     token.setName(reg.getName());
                     token.setIid(reg.getIid());
                     mv.addObject("msg",token.getName());

                 } else {
                     mv.setViewName("login");
                     mv.addObject("objfail", "Incorrect Phone/Email/Password.Try again");
                 }
             }



        }
return  mv;
    }

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
    @RequestMapping ("checkregister")
    public ModelAndView checkregister(Register register,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists

        if (session != null && session.getAttribute("user") != null) {

            mv.setViewName("Home");
            mv.addObject("msg", token.getName());
        }
        else {

         if(register.getName()==null||register.getIid()==null||register.getIdentity2()==null||register.getPass2()==null)
         {
             mv.setViewName("login");
         }
         else
         {
              rrepo.save(register);
             mv.addObject("obj", "Thanks for the registration.Kindly login to Continue");
             mv.setViewName("login");
         }
        }



        return mv;
    }

    @RequestMapping("purchase")
    public  ModelAndView purchase( @RequestParam(name = "hallName",defaultValue = "") String hallName,@RequestParam (name = "lunch", defaultValue = "0") int lunch,@RequestParam (name = "dinner", defaultValue = "0") int dinner,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists
        if (session != null && session.getAttribute("user") != null&& (lunch==0&&dinner==0))
        {
            mv.setViewName("Home");
            mv.addObject("msg2","You must select atleast one token.");
            mv.addObject("msg", token.getName());
        }

      else  if (session != null && session.getAttribute("user") != null) {
            LocalTime currentTime = LocalTime.now();


            LocalTime thresholdTime = LocalTime.of(7, 0);


            LocalDate currentDate = LocalDate.now();


            if (currentTime.isBefore(thresholdTime)|| currentTime.equals(thresholdTime)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy", Locale.ENGLISH);
                String formattedDate = currentDate.format(formatter);
                token.setDate(formattedDate);
            } else {

                LocalDate nextDay = currentDate.plusDays(0);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy", Locale.ENGLISH);
                String formattedDate = nextDay.format(formatter);
                token.setDate(formattedDate);
            }
            token.setDinner(dinner);
            token.setLunch(lunch);
            token.setHallName(hallName);
            mv.addObject("token",token);
            int cost=(lunch+dinner)*30;
            token.setCost(cost);


            mv.addObject("totalcost",cost);
            mv.setViewName("purchase");
        }
        else
        {
            mv.addObject("pc","You must log in to use HallEats services");
            mv.setViewName("login");
        }

        return  mv;
    }
    @GetMapping("about")
    public  ModelAndView about(HttpServletRequest request)
    {
        ModelAndView mv=new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists
        if (session != null && session.getAttribute("user") !=null) {
            mv.setViewName("about");
        }
        else
        {
            mv.addObject("pc","You must log in to visit HallEats");
            mv.setViewName("login");
        }
        return mv;
    }

    @RequestMapping("pay")
    public ModelAndView pay(@RequestParam(value = "paymentMethod",defaultValue = "default") String paymentMethod,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false); // Check if a session already exists
        if (session != null && session.getAttribute("user") !=null&&paymentMethod.equals("default"))
        {
            mv.addObject("msg",token.getName());
            mv.setViewName("Home");
        }
       else if (session != null && session.getAttribute("user") !=null) {


            mv.setViewName("payment");
            if(paymentMethod.equals("bkash"))
            {
                mv.addObject("paymentmethod","Bkash");
            }
            else if (paymentMethod.equals("dbbl"))
            {
                mv.addObject("paymentmethod","DBBL NEXUS");
            }
            else if (paymentMethod.equals("debit-card"))
            {
                mv.addObject("paymentmethod","Debit Card");
            }
            else
            {
                mv.addObject("paymentmethod","Nagad");
            }
            mv.addObject("cost",token.getCost());
        }
        else
        {
            mv.addObject("pc","You must log in to visit HallEats");
            mv.setViewName("login");
        }
        return mv;
    }
    private CreatePdfFileService createPdfFileService;

    @Autowired
    public LoginController(CreatePdfFileService createPdfFileService) {
        this.createPdfFileService = createPdfFileService;
    }
    Boolean c=false;
    @PostMapping("confirmpayment")
    public  ModelAndView confirmpayment(@RequestParam(value ="accountNumber",defaultValue = "default") String accountNumber,@RequestParam(value = "pin",defaultValue = "default") String pin,HttpServletRequest request)
    {
        ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") !=null&&(accountNumber.equals("default")||pin.equals("default")))
        {
            mv.addObject("msg",token.getName());
            mv.setViewName("Home");
        }
       else if (session != null && session.getAttribute("user") !=null)
        {
            String r = generateRandomString(2);
            String r2 = generateRandomString(2);
            String a=token.getIid();

            String four = a.substring(a.length() - 4);
           token.setTid(r+four+r2);


            trepo.save(token);
            c=true;


            mv.setViewName("confirmpayment");
        }
        else
        {
            mv.addObject("pc","You must log in to visit HallEats");
            mv.setViewName("login");
        }


        return mv;
    }

    @GetMapping("/createPdf")
    public ModelAndView pdfFile(HttpServletRequest request){
         ModelAndView mv= new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") !=null&&c==true) {
            mv.addObject("done", "PDF File Download Complete");

            createPdfFileService.createPdf(token);
            mv.setViewName("confirmpayment");
        }
        else if(session != null && session.getAttribute("user")!=null)
        {

            mv.setViewName("redirect:/confirmpayment");
        }
        else
        {
            mv.addObject("pc","You must log in to visit HallEats");
            mv.setViewName("login");
        }
        return mv;
    }

    private String generateRandomString(int length) {
        String characters = "1234567891011121314151617181920";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }


}
