package com.portrade.www.portradespringboot.controller;

import com.portrade.www.portradespringboot.config.auth.LoginUser;
import com.portrade.www.portradespringboot.config.auth.dto.SessionUser;
import com.portrade.www.portradespringboot.service.PostsService;
import com.portrade.www.portradespringboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
//---------------------------------------------------------------------------------------------

    @GetMapping("/signin")
    public @ResponseBody String signIn() {
        return "signIn";
    }

    @GetMapping("/signUp")
    public @ResponseBody String signUp() {
        return "signUp";
    }



}
