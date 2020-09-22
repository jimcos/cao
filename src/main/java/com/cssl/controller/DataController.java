package com.cssl.controller;

import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private UsersService service;


    @ResponseBody
    @GetMapping("/find")
    public List<Users> find(){
        return service.findUsers();
    }


}
