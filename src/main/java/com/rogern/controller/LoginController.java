package com.rogern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String hi() {
        return "NewFile";
    }

    @RequestMapping("add")
    public String hello(@RequestParam("t1") int i, @RequestParam("t2") int j) {
        return (i+j)+"";
    }
}
