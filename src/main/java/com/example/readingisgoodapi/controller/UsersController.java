package com.example.readingisgoodapi.controller;

import com.example.readingisgoodapi.dao.UsersDao;
import com.example.readingisgoodapi.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(@RequestParam String userName, @RequestParam String password, HttpServletRequest request){

        Users u = usersDao.getUser(userName, password);
        HttpSession session = request.getSession();
        if(u != null){
            session.setAttribute("user_id", u.getUid());
            return "User logged in successfully";
        }else{
            return "User not found, please try again";
        }

    }
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user_id") != null){
            session.setAttribute("user_id", null);
        }
        return "User logged out successfully";
    }

}