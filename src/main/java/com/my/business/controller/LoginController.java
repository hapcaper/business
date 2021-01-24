package com.my.business.controller;

import com.my.business.dao.ItemDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Resource
    private ItemDao itemDao;


    @GetMapping("/toLogin")
    public String toLogin(Model model) {
        return "login";
    }
    @PostMapping("/login")
    public String login(HttpSession session, Model model, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (name.equals(userName) && pswd.equals(password)) {
            List<String> allType = itemDao.findAllType();
            session.setAttribute("loginId", "abcd");
            session.setAttribute("allType", allType);
            return "redirect:/item/list";
        } else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
    }
}
