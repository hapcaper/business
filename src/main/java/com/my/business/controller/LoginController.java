package com.my.business.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2021/1/17
 */
@Controller
public class LoginController {
    @Value("${login.username}")
    private String name;
    @Value("${login.password}")
    private String pswd;


    @GetMapping("/toLogin")
    public String toLogin(Model model) {
        return "login";
    }
    @PostMapping("/login")
    public String login(HttpSession session, Model model, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (name.equals(userName) && pswd.equals(password)) {
            session.setAttribute("loginId", "abcd");
            return "redirect:/item/list";
        } else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
    }
}
