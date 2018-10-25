package com.mirc.serverauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrl {


    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
